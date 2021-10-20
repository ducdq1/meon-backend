package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.AddOrderItemRequest;
import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.utils.TeleCareException;

public interface OrderItemService {
    Object getOrderItemsByBill(Integer billId) throws TeleCareException;
    Object addOrderItem(AddOrderItemRequest request) throws TeleCareException;
    Object updateOrderItem(AddOrderItemRequest request) throws TeleCareException;
    Object updateOrderItemStatus(Integer userId, Integer orderItemId, Integer status) throws TeleCareException;
    Object deleteOrderItem(Integer orderItemId, Integer userId) throws TeleCareException;
}