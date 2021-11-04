package com.mrlep.meon.firebase;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.ShopTableRepository;
import com.mrlep.meon.repositories.impl.OrderItemRepositoryImpl;
import com.mrlep.meon.repositories.tables.BillMembersRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenusOptionRepositoryJPA;
import com.mrlep.meon.repositories.tables.OrderItemRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.KThreadPoolExecutor;
import com.mrlep.meon.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FirestoreBillManagement {
    @Autowired
    private OrderItemService orderItemlService;
    @Autowired
    private OrderItemRepositoryImpl orderItemRepository;

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private MenusOptionRepositoryJPA menusOptionRepositoryJPA;

    @Autowired
    private BillMembersRepositoryJPA billMembersRepositoryJPA;
    @Autowired
    private OrderItemRepositoryJPA orderItemRepositoryJPA;
    @Autowired
    private ShopTableRepository shopTableRepository;
    @Autowired
    private NotificationService notification;

    public static String ORDERS = "ORDERS";

    public void updateBill(Integer billId, BillEntity entity) {
        DetailBillResponse response = billRepository.getDetailBill(billId);
        if (response == null) {
            return;
        }
        List<BillTablesItem> billTablesItems = shopTableRepository.getTableOfBill(billId);
        List<String> tablesName = new ArrayList<>();
        for (BillTablesItem billTablesItem : billTablesItems) {
            tablesName.add(billTablesItem.getTableName());
        }
        response.setTablesName(tablesName);
        response.setNumberOrders(orderItemRepositoryJPA.countOrderItemOfBill(billId));
        response.setNumberMembers(billMembersRepositoryJPA.countMembersOfBill(billId));
        System.out.println("Update bill " + billId + " status " + response.getBillStatus());
        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = FirebaseFirestore.getDb();
                    DocumentReference docRef = db.collection("BILLS").document(billId.toString());
                    if (response.getBillStatus() == Constants.BILL_STATUS_DONE) {
                        docRef.delete();
                        deleteAllBillOrderItem(entity.getId());
                    } else {
                        docRef.set(response);
                    }

                    /*List<OrderItem> orderItemEntitiesList = (List<OrderItem>) orderItemlService.getOrderItemsByBill(billId);

                    if (orderItemEntitiesList != null) {
                        for (OrderItem orderItem : orderItemEntitiesList) {
                            DocumentReference orderItemRef = db.collection(ORDERS).document(orderItem.getId().toString());
                            orderItem.setMenuOptions(null);
                            orderItemRef.set(orderItem);

                            String menuOptionIds = orderItem.getMenuOptionIds();
                            if (!StringUtils.isNullOrEmpty(menuOptionIds)) {
                                String[] ids = menuOptionIds.split(";");
                                for (String id : ids) {
                                    if (FnCommon.getIntegerFromString(id) != null) {
                                        MenuOptionEntity menuOptionEntity = menusOptionRepositoryJPA.getByIdAndIsActive(FnCommon.getIntegerFromString(id), Constants.IS_ACTIVE);
                                        if (menuOptionEntity != null) {
                                            DocumentReference menuOptionsRef = orderItemRef.collection("menuOptions").document(menuOptionEntity.getId().toString());
                                            menuOptionsRef.set(menuOptionEntity);
                                        }
                                    }
                                }
                            }
                        }
                    }*/

                   /* List<BillTablesItem> billTablesItems = billRepository.getBillTables(billId);

                    if (billTablesItems != null) {
                        for (BillTablesItem tablesItem : billTablesItems) {
                            DocumentReference orderItemRef = docRef.collection("tables").document(tablesItem.getTableId().toString());
                            orderItemRef.set(tablesItem);
                        }
                    }

                    List<BillMembersItem> billMembersItems = billRepository.getBillMembers(billId);

                    if (billMembersItems != null) {
                        for (BillMembersItem billMembersItem : billMembersItems) {
                            DocumentReference orderItemRef = docRef.collection("members").document(billMembersItem.getId().toString());
                            orderItemRef.set(billMembersItem);
                        }
                    }*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }


    public void deleteBillOrderItem(Integer billId, Integer orderItemId) {
        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();
                    db.collection(ORDERS).document(orderItemId.toString()).delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void deleteBill(Integer billId) {
        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();
                    db.collection("BILLS").document(billId.toString()).delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void deleteBillTable(Integer billId, Integer tableId) {
        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();
                    db.collection("BILLS").document(billId.toString()).collection("tables").document(tableId.toString()).delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void deleteAllBillOrderItem(Integer billId) {
        List<OrderItem> orderItems = orderItemRepository.getOrderItemOfBill(billId);

        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();

                    CollectionReference collectionReference = db.collection("ORDERS");
                    for (OrderItem item : orderItems) {
                        DocumentReference documentReference = collectionReference.document(item.getId().toString());
                        documentReference.delete();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void updateOrderItem(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.getOrderItem(orderItemId);

        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();

                    CollectionReference collectionReference = db.collection("ORDERS");
                    DocumentReference documentReference = collectionReference.document(orderItemId.toString());
                    if (orderItem != null) {
                        documentReference.set(orderItem);
                    } else {
                        documentReference.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void updateOrderItemsStatus(Integer billId) {
        List<OrderItem> orderItems = orderItemRepository.getOrderItemOfBill(billId);
        if (orderItems == null) {
            return;
        }

        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();


                    for (OrderItem orderItem : orderItems) {
                        CollectionReference collectionReference = db.collection("ORDERS");
                        DocumentReference documentReference = collectionReference.document(orderItem.getId().toString());
                        if (orderItem != null) {
                            documentReference.set(orderItem);
                        } else {
                            documentReference.delete();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }


    public void updateAllOrderItem() {
        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();

                    List<OrderItem> orderItems = orderItemRepository.getAllOrderItem();
                    CollectionReference collectionReference = db.collection("ORDERS");
                    for (OrderItem orderItem : orderItems) {
                        DocumentReference documentReference = collectionReference.document(orderItem.getId().toString());
                        documentReference.set(orderItem);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void sendBillStatusNotification(BillEntity entity) {
        notification.sendBillStatusChangeNotificationToCustomer(entity);
    }

}
