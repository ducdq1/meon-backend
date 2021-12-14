package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.AddOrderItemRequest;
import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.UpdateStatusRequest;
import com.mrlep.meon.utils.TeleCareException;

import java.util.List;

public interface OrderItemService {
    Object getOrderItemsByBill(Integer billId) throws TeleCareException;
    Object addOrderItem(AddOrderItemRequest request) throws TeleCareException;
    Object updateOrderItem(AddOrderItemRequest request) throws TeleCareException;
    Object updateOrderItemAmount(AddOrderItemRequest request) throws TeleCareException;
    Object updateOrderItemStatus(Integer userId, Integer orderItemId, UpdateStatusRequest request) throws TeleCareException;
    Object deleteOrderItem(Integer orderItemId, Integer userId) throws TeleCareException;
    Object getDetailOrderItems(Integer orderItemId) throws TeleCareException;
    Object getOrderItemsTopList(Integer shopId, Integer processType,String filter,Integer limit) throws TeleCareException;
}