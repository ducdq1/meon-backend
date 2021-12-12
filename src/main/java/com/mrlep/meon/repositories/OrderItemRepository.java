package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.CountOrderItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface OrderItemRepository {
    OrderItem getOrderItem(Integer id);
    List<OrderItem> getOrderItemOfBill(Integer billId);
    List<OrderItem> getOrderItemDeliveredOfBill(Integer billId);
    List<OrderItem> getAllOrderItem();
    List<CountOrderItem> countOrderItems(Integer shopId,Integer processType, String filter);
}
