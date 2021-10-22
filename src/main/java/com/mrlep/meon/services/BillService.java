package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.CreateShopTableRequest;
import com.mrlep.meon.utils.TeleCareException;

public interface BillService {
    Object getDetailBills(Integer billId) throws TeleCareException;
    Object getBillsByShop(Integer shopId) throws TeleCareException;
    Object createBill(CreateBillRequest request) throws TeleCareException;
    Object updateBill(CreateBillRequest request) throws TeleCareException;

    Object updateBillStatus(Integer userId, Integer billId, Integer status) throws TeleCareException;
    Object deleteBill(Integer billId, Integer userId) throws TeleCareException;
    Object joinBill(Integer billId, Integer userId) throws TeleCareException;
    Object addTableBill(Integer billId, Integer userId,Integer tableId) throws TeleCareException;
}