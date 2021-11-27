package com.mrlep.meon.firebase;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.firebase.model.Data;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.ShopTableRepository;
import com.mrlep.meon.repositories.impl.OrderItemRepositoryImpl;
import com.mrlep.meon.repositories.tables.BillMembersRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenusOptionRepositoryJPA;
import com.mrlep.meon.repositories.tables.OrderItemRepositoryJPA;
import com.mrlep.meon.repositories.tables.UsersRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;
import com.mrlep.meon.repositories.tables.entities.UsersEntity;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.utils.*;
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

    @Autowired
    private UsersRepositoryJPA usersRepositoryJPA;

    public static String ORDERS = "ORDERS";

    public void updateBill(Integer billId) {
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
                    if (response.getBillStatus() == Constants.BILL_STATUS_DONE
                            || response.getBillStatus() == Constants.BILL_STATUS_CANCEL) {
                        docRef.delete();
                        deleteAllBillOrderItem(billId);
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
        System.out.println("delete orderItem " + orderItemId);
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
        System.out.println("delete bill  " + billId);
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

    public void updateOrderItem(Integer orderItemId, Integer updateUserId) {
        OrderItem orderItem = orderItemRepository.getOrderItem(orderItemId);
        System.out.println("Update orderItem " + orderItem.getMenuName() + " status " + orderItem.getStatus());
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

                    sendOrderStatusNotification(orderItem, updateUserId);
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

    public void sendNotificationToShop(Data data, Integer shopId, String messsage) {
        notification.sendNotificationToShop(data, shopId, messsage);
    }

    public void sendBillStatusNotification(BillEntity billEntity) {
        String description = null;
        String message = String.format("Hóa đơn %s vừa được %s", billEntity.getName(), FnCommon.getBillStatusString(billEntity.getStatus()));
        UsersEntity usersEntity = usersRepositoryJPA.getByIdAndIsActive(billEntity.getUpdateUserId(), Constants.IS_ACTIVE);
        if (billEntity.getStatus() == Constants.BILL_STATUS_CANCEL) {
            description = billEntity.getCancelMessage();
        } else if (billEntity.getStatus() == Constants.BILL_STATUS_RECONFIRM) {
            description = billEntity.getReconfirmMessage();
        }

        if (description != null) {
            message += ": " + description;
        }

        String createDate = DateUtility.getDateStringFormat(billEntity.getCreateDate(),DateUtility.DATE_TIME_FORMAT_STR);
        sendNotificationToShop(new Data(billEntity.getId(), "BILL", usersEntity == null ? "" : usersEntity.getPhone(),billEntity.getStatus(),billEntity.getName(),createDate), billEntity.getShopId(), message);
    }

    private void sendOrderStatusNotification(OrderItem orderItem, Integer updateUserId) {
        String type = null;
        String description = null;
        switch (orderItem.getStatus()) {
            case Constants.ORDER_ITEM_STATUS_CANCEL:
                type = Data.ORDER_STATUS_CANCEL;
                description = orderItem.getCancelMessage();
                break;
            case Constants.ORDER_ITEM_STATUS_REJECT:
                type = Data.ORDER_STATUS_REJECT;
                description = orderItem.getCancelMessage();
                break;
            case Constants.ORDER_ITEM_STATUS_RECONFIRM:
                type = Data.ORDER_STATUS_RECONFIRM;
                description = orderItem.getReconfirms();
                break;
            case Constants.ORDER_ITEM_STATUS_DONE:
                type = Data.ORDER_STATUS_DONE;
                break;
        }

        if (type != null) {
            String message = String.format("Món %s của hóa đơn %s vừa được %s", orderItem.getMenuName(), orderItem.getBillName(), FnCommon.getOrderStatusString(orderItem.getStatus()));
            if (description != null) {
                message += ": " + description;
            }
            UsersEntity usersEntity = usersRepositoryJPA.getByIdAndIsActive(updateUserId, Constants.IS_ACTIVE);
            sendNotificationToShop(new Data(orderItem.getId(), "ORDER", usersEntity == null ? "" : usersEntity.getPhone(),null,null,null), orderItem.getShopId(), message);
        }
    }


}
