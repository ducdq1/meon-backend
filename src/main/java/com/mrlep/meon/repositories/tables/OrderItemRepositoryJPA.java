package com.mrlep.meon.repositories.tables;

import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Doctors
 *
 * @author ToolGen
 * @date Thu Sep 23 08:19:38 ICT 2021
 */
@Repository
public interface OrderItemRepositoryJPA extends JpaRepository<OrderItemEntity, Integer> {
    List<OrderItemEntity> getAllByBillIdAndIsActive(Integer billId, Integer isActive);

    OrderItemEntity findByIdIsAndIsActive(Integer id, Integer isActive);

    @Query(value = "SELECT count(*) FROM ORDER_ITEM o WHERE o.is_active =1 AND o.bill_id = :billId AND o.status <> 3 AND o.status <> 5", nativeQuery = true)
    Integer countActiveOrderItemOfBill(Integer billId);

    @Query(value = "SELECT count(*) FROM ORDER_ITEM o WHERE o.is_active =1 AND o.bill_id = :billId AND o.status <> 5", nativeQuery = true)
    Integer countOrderItemOfBill(Integer billId);


    @Query(value = "UPDATE ORDER_ITEM set status =:status WHERE  bill_id = :billId AND status <> 5 ", nativeQuery = true)
    void updateOrderItemStatus(Integer billId, Integer status);
}