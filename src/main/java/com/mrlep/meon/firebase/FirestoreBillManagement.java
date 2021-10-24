package com.mrlep.meon.firebase;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.services.impl.OrderItemlServiceImpl;
import com.mrlep.meon.utils.KThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestoreBillManagement {
    @Autowired
    private OrderItemService orderItemlService;
    @Autowired
    private BillRepository billRepository;

    public void updateBill(Integer billId, BillEntity entity) {
        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();
                    DocumentReference docRef = db.collection("BILLS").document(billId.toString());
                    docRef.set(entity);
                    List<OrderItem> orderItemEntitiesList = (List<OrderItem>) orderItemlService.getOrderItemsByBill(billId);
                    if (orderItemEntitiesList != null) {
                        for (OrderItem orderItem : orderItemEntitiesList) {
                            DocumentReference orderItemRef = docRef.collection("orderItems").document(orderItem.getId().toString());
                            orderItemRef.set(orderItem);
                        }
                    }

                    List<BillTablesItem> billTablesItems = billRepository.getBillTables(billId);

                    if (billTablesItems != null) {
                        for (BillTablesItem tablesItem : billTablesItems) {
                            DocumentReference orderItemRef = docRef.collection("tables").document(tablesItem.getTableId().toString());
                            orderItemRef.set(tablesItem);
                        }
                    }

                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }


    public void updateBillOrderItem(Integer billId, Integer orderItemId, OrderItemEntity entity) {
        KThreadPoolExecutor.executeAccessLog((new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = new FirebaseFirestore().getDb();
                    DocumentReference docRef = db.collection("BILLS").document(billId.toString()).collection("orderItems").document(orderItemId.toString());
                    docRef.set(entity);
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}