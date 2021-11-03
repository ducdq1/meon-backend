package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.request.UpdateStatusRequest;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.utils.TeleCareException;

import java.util.List;

public interface BillService {
    Object getBillByTable(Integer shopId, Integer tableId) throws TeleCareException;

    Object getBillActiveByUser(Integer userId) throws TeleCareException;

    Object getDetailBills(Integer billId) throws TeleCareException;

    Object getBillsByShop(Integer shopId, Integer offset, Integer pageSize) throws TeleCareException;

    Object createBill(CreateBillRequest request) throws TeleCareException;

    Object updateBill(CreateBillRequest request) throws TeleCareException;

    Object updateBillStatus(Integer userId, Integer billId,  List<String> permission, UpdateStatusRequest request) throws TeleCareException;

    Object deleteBill(Integer billId, Integer userId) throws TeleCareException;

    Object joinBill(Integer billId, Integer userId) throws TeleCareException;

    Object addTableBill(Integer billId, Integer userId, List<Integer> tableIds) throws TeleCareException;

    SearchBillResponse searchBillUser(SearchBillRequest request) throws TeleCareException;

    void updateBillInfo(Integer userId, Integer billId) throws TeleCareException;
}