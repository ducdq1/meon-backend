package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchShopResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.StringUtils;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public class BillRepositoryImpl extends CommonDataBaseRepository implements BillRepository {

    @Override
    public DetailBillResponse getDetailBill(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.totalMoney,u.full_name userName ");
        sql.append(" u.phone, u.avatar ");
        sql.append("  FROM BILL b. JOIN USERS u on u.id = create_user_id  ");
        sql.append("  WHERE b.id=:billId AND b.is_active = 1 ");
        params.put("billId", billId);

        return (DetailBillResponse) getFirstData(sql, params, DetailBillResponse.class);
    }
}
