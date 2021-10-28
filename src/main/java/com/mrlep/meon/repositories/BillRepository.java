package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface BillRepository {
    DetailBillResponse getDetailBill(Integer billId);
    List<BillMembersItem> getBillMembers(Integer billId);
    List<BillTablesItem> getBillTables(Integer billId);
    ResultSelectEntity getBillOfShop(Integer shopId, Integer offset, Integer pageSize);
    DetailBillResponse getBillActiveByUser(Integer userId);


    SearchBillResponse getBillOfUser(SearchBillRequest request);

}
