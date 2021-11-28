package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.repositories.BillRepository;
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

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar,b.shop_id shopId,s.name shopName, s.image_url shopAvatar, ");
        sql.append(" b.create_user_id createUserId , DATE_FORMAT(b.create_date, '%d-%m-%Y %H:%i') createDate ,b.reconfirm_message reconfirmMessage" +
                " ,b.cancel_message cancelMessage,s.vat,b.vat_money vatMoney,b.sub_money subMoney,b.sub_money_description subMoneyDescription,s.phone shopPhone,s.address shopAddress, ");
        sql.append(" b.pre_money preMoney ");
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

        sql.append(" SELECT b.id billId, bm.id id, CASE WHEN bm.is_blacklist is not null THEN bm.is_blacklist ELSE 0 END isBlackList, u.id userId, u.full_name userName, u.phone ,u.avatar, DATE_FORMAT(bm.create_date, '%H:%i') joinTime ");
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

        sql.append(" SELECT b.id billId,bt.table_id tableId, bt.table_name tableName ");
        sql.append("  FROM BILL b JOIN BILL_TABLES bt on b.id = bt.bill_id AND bt.is_active = 1  ");

        sql.append("  WHERE b.id=:billId AND b.is_active = 1 ");
        params.put("billId", billId);

        return (List<BillTablesItem>) getListData(sql, params, 0, null, BillTablesItem.class);
    }

    @Override
    public ResultSelectEntity getBillOfShop(Integer shopId, SearchBillRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar,s.vat,b.vat_money vatMoney,b.sub_money subMoney,b.sub_money_description subMoneyDescription,DATE_FORMAT(b.create_date, '%d-%m-%Y %H:%i') createDate");
        sql.append("  FROM BILL b JOIN USERS u on u.id = b.create_user_id  ");
        sql.append(" JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE b.shop_id=:shopId AND b.is_active = 1  ");
        if (request.getStatus() != null) {
            sql.append("   AND b.status =:status ");
            params.put("status", request.getStatus());
        }

        if (!StringUtils.isNullOrEmpty(request.getKeySearch())) {
            sql.append("   AND b.name LIKE :name escape '#' ");
            params.put("name", FnCommon.getSearchLikeValue(request.getKeySearch()));
        }

        if (request.getTableId() != null) {
            sql.append("   AND b.id IN (SELECT bt.bill_id FROM BILL_TABLES bt WHERE bt.table_id =:tableId AND bt.is_active = 1  ) ");
            params.put("tableId", request.getTableId());
        }

        sql.append("  ORDER BY b.create_date DESC ");

        params.put("shopId", shopId);

        return getListDataAndCount(sql, params, request.getStartRecord(), request.getPageSize(), BillItem.class);
    }

    @Override
    public DetailBillResponse getBillActiveByUser(Integer userId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar,b.shop_id shopId,s.name shopName,s.image_url shopAvatar,s.vat,b.vat_money vatMoney,b.sub_money subMoney,b.sub_money_description subMoneyDescription   ");
        sql.append("  FROM BILL b JOIN USERS u on u.id = b.create_user_id  ");
        sql.append(" JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE u.id=:userId AND b.is_active = 1 AND b.status <> :statusDone AND b.status <> :statusCancel AND u.is_active = 1");
        params.put("userId", userId);
        params.put("statusCancel", Constants.BILL_STATUS_CANCEL);
        params.put("statusDone", Constants.BILL_STATUS_DONE);

        return (DetailBillResponse) getFirstData(sql, params, DetailBillResponse.class);
    }

    @Override
    public SearchBillResponse getBillOfUser(SearchBillRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        SearchBillResponse response = new SearchBillResponse();
        sql.append(" SELECT distinct(b.id) billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar, DATE_FORMAT(b.create_date, '%d-%m-%Y %H:%i') createDate, ");
        sql.append(" s.id shopId, s.name shopName, s.address shopAddress, s.image_url shopAvatar,s.vat,b.vat_money vatMoney,b.sub_money subMoney,b.sub_money_description subMoneyDescription  ");
        sql.append("  FROM BILL b JOIN USERS u ON u.id = b.create_user_id  ");
        sql.append(" JOIN BILL_MEMBERS bm ON u.id = bm.user_id  ");
        sql.append(" LEFT JOIN SHOP s ON s.id = b.shop_id  ");
        sql.append("  WHERE  b.is_active = 1 AND u.is_active = 1 ");

        if (request.getUserId() != null) {
            sql.append(" AND bm.user_id=:userId ");
            params.put("userId", request.getUserId());
        }

        if (request.getShopId() != null) {
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

    @Override
    public DetailBillResponse getBillByTable(Integer shopId, Integer tableId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT b.id billId, b.name billName, b.status billStatus,b.description,b.total_Money totalMoney,u.full_name userName, ");
        sql.append(" u.phone, u.avatar,b.shop_id shopId,s.name shopName, s.image_url shopAvatar, ");
        sql.append(" b.create_user_id createUserId , DATE_FORMAT(b.create_date, '%d-%m-%Y %H:%i') createDate ,b.reconfirm_message reconfirmMessage" +
                " ,b.cancel_message cancelMessage,s.vat,b.vat_money vatMoney,b.sub_money subMoney,b.sub_money_description subMoneyDescription  ");
        sql.append("  FROM BILL b JOIN USERS u on u.id = b.create_user_id  ");
        sql.append(" JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE b.status <> :statusDone AND b.status <> :statusCancel AND b.is_active = 1 AND b.shop_id =:shopId ");
        sql.append("  AND b.id IN (SELECT b.id FROM BILL b JOIN BILL_TABLES bt ON b.id = bt.bill_id WHERE bt.table_id =:tableId ) ");

        params.put("tableId", tableId);
        params.put("shopId", shopId);
        params.put("statusCancel", Constants.BILL_STATUS_CANCEL);
        params.put("statusDone", Constants.BILL_STATUS_DONE);

        return (DetailBillResponse) getFirstData(sql, params, DetailBillResponse.class);
    }
}
