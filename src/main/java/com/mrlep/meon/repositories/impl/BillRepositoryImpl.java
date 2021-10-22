package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillMembersObject;
import com.mrlep.meon.dto.object.BillTablesObject;
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
import java.util.List;


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

    @Override
    public List<BillMembersObject> getBillMembers(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT u.id userId, u.full_name userName, u.phone ,u.avatar ");
        sql.append("  FROM BILL b. JOIN BILL_MEMBERS bm on b.id = bm.bill_id AND bm.is_active = 1  ");
        sql.append("  JOIN USERS u on u.id = bm.user_id AND u.is_active = 1  ");
        sql.append("  WHERE b.id=:billId AND b.is_active = 1 ");
        params.put("billId", billId);

        return (List<BillMembersObject>) getListData(sql, params, 0, null, BillMembersObject.class);
    }

    @Override
    public List<BillTablesObject> getBillTables(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT st.id tableId, st.name tableName, st.status ,st.unique_number tableNumer ");
        sql.append("  FROM BILL b JOIN BILL_TABLES bt on b.id = bt.bill_id AND bt.is_active = 1  ");
        sql.append("  JOIN SHOP_TABLE st on st.id = bt.table_id AND st.is_active = 1  ");
        sql.append("  WHERE b.id=:billId AND b.is_active = 1 ");
        params.put("billId", billId);

        return (List<BillTablesObject>) getListData(sql, params, 0, null, BillTablesObject.class);
    }
}
