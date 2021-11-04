package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.repositories.BillTableRepository;
import com.mrlep.meon.repositories.ShopTableRepository;
import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class ShopTableRepositoryImpl extends CommonDataBaseRepository implements ShopTableRepository {

    @Override
    public List<BillTablesItem> getTableOfBill(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct(st.id), bt.table_name tableName ");
        sql.append("  FROM SHOP_TABLE st ");
        sql.append("  JOIN BILL_TABLES bt ON bt.table_id = st.id AND bt.is_active = 1  ");
        sql.append("   JOIN BILL b ON b.id = bt.bill_id AND b.is_active = 1  ");
        sql.append("  WHERE b.id=:billId AND b.is_active = 1 ");
        params.put("billId", billId);
        return (List<BillTablesItem>) getListData(sql, params, 0, null, BillTablesItem.class);
    }

    @Override
    public ResultSelectEntity getTableOfShopAndStatus(Integer shopId, Integer status, Integer startRecord, Integer pageSize) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT st.id, st.name name,st.UNIQUE_NUMBER uniqueNumber,st.CAPABILITY capability,st.status,st.shop_id shopId,st.image_url imageUrl  ");
        sql.append("  FROM SHOP_TABLE st ");
        sql.append("  JOIN SHOP s ON s.id = st.id AND s.is_active = 1  ");
        sql.append("  WHERE st.shop_id=:shopId  AND st.is_active = 1 ");
        if (status >= 0) {
            sql.append("    AND st.status = :status  ");
            params.put("status", status);
        }

        params.put("shopId", shopId);
        return getListDataAndCount(sql, params, startRecord, pageSize, ShopTableEntity.class);
    }
}