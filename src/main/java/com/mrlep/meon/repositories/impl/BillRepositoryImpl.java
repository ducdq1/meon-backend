package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.repositories.BillRepository;
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

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar,b.shop_id shopId,s.name shopName, s.image_url shopAvatar ");
        sql.append("  FROM BILL b JOIN USERS u on u.id = b.create_user_id  ");
        sql.append(" JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE b.id=:billId AND b.is_active = 1 ");
        params.put("billId", billId);

        return (DetailBillResponse) getFirstData(sql, params, DetailBillResponse.class);
    }

    @Override
    public List<BillMembersItem> getBillMembers(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT bm.id id, CASE WHEN bm.is_blacklist is not null THEN bm.is_blacklist ELSE 0 END isBlackList, u.id userId, u.full_name userName, u.phone ,u.avatar, DATE_FORMAT(bm.create_date, '%H:%i') joinTime ");
        sql.append("  FROM BILL b JOIN BILL_MEMBERS bm on b.id = bm.bill_id AND bm.is_active = 1  ");
        sql.append("  JOIN USERS u on u.id = bm.user_id AND u.is_active = 1  ");
        sql.append("  WHERE b.id=:billId AND b.is_active = 1 AND bm.is_active = 1 ");
        params.put("billId", billId);

        return (List<BillMembersItem>) getListData(sql, params, 0, null, BillMembersItem.class);
    }

    @Override
    public List<BillTablesItem> getBillTables(Integer billId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT bt.table_id tableId, bt.table_name tableName ");
        sql.append("  FROM BILL b JOIN BILL_TABLES bt on b.id = bt.bill_id AND bt.is_active = 1  ");
        sql.append("  WHERE b.id=:billId AND b.is_active = 1 ");
        params.put("billId", billId);

        return (List<BillTablesItem>) getListData(sql, params, 0, null, BillTablesItem.class);
    }

    @Override
    public ResultSelectEntity getBillOfShop(Integer shopId, Integer offset, Integer pageSize) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar ");
        sql.append("  FROM BILL b JOIN USERS u on u.id = b.create_user_id  ");
        sql.append("  WHERE b.shop_id=:shopId AND b.is_active = 1 ORDER BY b.create_date DESC ");
        params.put("shopId", shopId);

        return getListDataAndCount(sql, params, offset, pageSize, BillItem.class);
    }

    @Override
    public DetailBillResponse getBillActiveByUser(Integer userId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar,b.shop_id shopId,s.name shopName,s.image_url shopAvatar  ");
        sql.append("  FROM BILL b JOIN USERS u on u.id = b.create_user_id  ");
        sql.append(" JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE u.id=:userId AND b.is_active = 1 AND b.status <> 2 AND b.status <> 5 AND u.is_active = 1");
        params.put("userId", userId);

        return (DetailBillResponse) getFirstData(sql, params, DetailBillResponse.class);
    }

    @Override
    public SearchBillResponse getBillOfUser(SearchBillRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        SearchBillResponse response = new SearchBillResponse();
        sql.append(" SELECT distinct(b.id) billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar, DATE_FORMAT(b.create_date, '%d-%m-%Y %H:%i') createDate, ");
        sql.append(" s.id shopId, s.name shopName, s.address shopAddress, s.image_url shopAvatar ");
        sql.append("  FROM BILL b JOIN USERS u ON u.id = b.create_user_id  ");
        sql.append(" JOIN BILL_MEMBERS bm ON u.id = bm.user_id  ");
        sql.append(" LEFT JOIN SHOP s ON s.id = b.shop_id  ");
        sql.append("  WHERE  b.is_active = 1 AND u.is_active = 1 ");

        if(request.getUserId() !=null){
            sql.append(" AND bm.user_id=:userId ");
            params.put("userId", request.getUserId());
        }

        if(request.getShopId() !=null){
            sql.append(" AND b.shop_id=:shopId ");
            params.put("shopId", request.getShopId());
        }


        if (!StringUtils.isNullOrEmpty(request.getKeySearch())) {
            sql.append(" AND (LOWER (b.name) like :keySearch escape '#' OR s.name like :keySearch escape '#' OR s.address like :keySearch escape '#' )");
            params.put("keySearch", FnCommon.getSearchLikeValue(request.getKeySearch().trim().toLowerCase()));
        }

        if (request.getFromDate() != null) {
            sql.append(" AND DATE(b.create_date) >= DATE(:fromDate) ");
            params.put("fromDate", request.getFromDate());
        }

        if (request.getToDate() != null) {
            sql.append(" AND DATE(b.create_date) <= DATE(:toDate) ");
            params.put("toDate", request.getToDate());
        }

        sql.append("ORDER BY b.create_date DESC ");




        ResultSelectEntity selectEntity = getListDataAndCount(sql, params, request.getStartRecord(), request.getPageSize(), BillItem.class);
        response.setBillItems((List<BillItem>) selectEntity.getListData());
        response.setCount((Integer) selectEntity.getCount());

        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append(" SELECT SUM(totalMoney) totalMoney FROM ( ");
        sqlCount.append(sql.toString() + " ) a ");

        SearchBillResponse totalMoney = (SearchBillResponse) getFirstData(sqlCount, params, SearchBillResponse.class);

        response.setTotalMoney(totalMoney.getTotalMoney());

        return response;
    }
}
