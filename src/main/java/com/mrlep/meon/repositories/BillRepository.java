package com.mrlep.meon.repositories;

import com.mrlep.meon.dto.request.SearchMenusRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;

public interface BillRepository {
    DetailBillResponse getDetailBill(Integer billId);
}
