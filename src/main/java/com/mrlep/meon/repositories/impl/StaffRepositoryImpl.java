package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.BillMembersItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.StaffRepository;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class StaffRepositoryImpl extends CommonDataBaseRepository implements StaffRepository {


    @Override
    public List<StaffItem> getStaffsByShop(Integer shopId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b.id,s.id shopId,u.id userId, b.permission permission, b.IDENTITY_NUMBER  identityNumber,b. certification,");
        sql.append("  b.salary, b.status , u.phone, u.avatar, u.full_name fullName ");
        sql.append("  FROM STAFF b JOIN USERS u on u.id = b.user_id  ");
        sql.append(" JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE s.id=:shopId AND b.is_active = 1 AND u.is_active = 1 ORDER BY b.create_date DESC");
        params.put("shopId", shopId);

        return (List<StaffItem>) getListData(sql, params, null, null, StaffItem.class);
    }


    @Override
    public StaffItem getDetailStaff(Integer staffId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b.id,s.id shopId,u.id userId, b.permission permission, b.IDENTITY_NUMBER  identityNumber,b. certification,");
        sql.append("  b.salary, b.status , u.phone, u.avatar, u.full_name fullName ");
        sql.append("  FROM STAFF b JOIN USERS u on u.id = b.user_id  ");
        sql.append(" JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE b.id=:staffId AND b.is_active = 1 AND u.is_active = 1 ORDER BY b.create_date DESC");
        params.put("staffId", staffId);

        return (StaffItem) getFirstData(sql, params, StaffItem.class);
    }
}
