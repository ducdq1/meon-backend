package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

import java.util.List;

public interface BillTableRepository {
    List<String> getBillTableName(Integer billId);

}
