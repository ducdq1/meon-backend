package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.BillMembersObject;
import com.mrlep.meon.dto.object.BillTablesObject;
import com.mrlep.meon.dto.request.SearchMenusRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface BillRepository {
    DetailBillResponse getDetailBill(Integer billId);
    List<BillMembersObject> getBillMembers(Integer billId);
    List<BillTablesObject> getBillTables(Integer billId);
}
