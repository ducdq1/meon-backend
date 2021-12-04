package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.object.ShopItem;
import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.dto.response.SearchShopResponse;
import com.mrlep.meon.dto.response.ShopsOfStaffResponse;
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
public class ShopRepositoryImpl extends CommonDataBaseRepository implements ShopRepository {
    @Override
    public ResultSelectEntity getRecommendShop(SearchShopsRequest request) {
        String keySearch = request.getKeySearch();
        String orderBy = request.getOrderBy();
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT s.id ,s.name, s.address, s.phone, s.image_url imageUrl, s.description,s.tags, cs.name shopTypeName,cs.url_icon shopTypeIcon, ");
        sql.append(" s.like_number likeNumber, s.open_time openTime,s.close_time closeTime,s.special_tag, s.is_verify isVerify,s.vat, ");
        if (request.getLat() != null && request.getLng() != null) {
            sql.append(" concat(ABS(lat - :userLat) + ABS(lng - :userLng),'m')  AS distance ");
            params.put("userLat", request.getLat());
            params.put("userLng", request.getLng());
        } else {
            sql.append(" '0m' AS distance ");
        }

        sql.append("FROM SHOP s ");
        sql.append("LEFT JOIN CATS_SHOP cs on cs.id = s.cats_shop_id ");
        sql.append("WHERE s.is_active=1 ");


        if (!StringUtils.isNullOrEmpty(request.getKeySearch())) {
            sql.append(" AND (LOWER (s.name) like :keySearch escape '#'  ");
            sql.append(" OR LOWER (s.phone) like :keySearch escape '#'  ");
            sql.append(" OR LOWER (s.address) like :keySearch escape '#' ) ");
            params.put("keySearch", FnCommon.getSearchLikeValue(keySearch.trim().toLowerCase()));
        }

        if (Constants.ORDER_BY_POPULAR.equals(orderBy)) {
            sql.append(" ORDER BY s.create_date DESC ");
        } else if (Constants.ORDER_BY_NEW.equals(orderBy)) {
            sql.append(" ORDER BY s.create_date DESC ");
        } else if (Constants.ORDER_BY_NEAREST.equals(orderBy)) {
            sql.append(" ORDER BY distance ASC ");
        } else if (Constants.ORDER_BY_TRENDING.equals(orderBy)) {
            sql.append(" ORDER BY s.create_date DESC ");
        } else {
            sql.append(" ORDER BY s.create_date DESC ");
        }

        return getListDataAndCount(sql, params, request.getStartRecord(), request.getPageSize(), SearchShopResponse.class);
    }

    @Override
    public List<StaffItem> getShopOfsStaff(Integer userId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b.id,s.id shopId,u.id userId, b.permission permission, b.IDENTITY_NUMBER  identityNumber,b. certification,");
        sql.append("  b.salary, b.status , u.phone, u.avatar, u.full_name fullName,s.name shopName, s.address shopAddress, s.image_url  shopAvatar ");
        sql.append("  FROM STAFF b JOIN USERS u on u.id = b.user_id  ");
        sql.append("  JOIN SHOP s ON s.id = b.shop_id ");
        sql.append("  WHERE b.status =1 AND u.id=:userId AND b.is_active = 1 AND u.is_active = 1 ORDER BY b.create_date DESC");
        params.put("userId", userId);

        return (List<StaffItem>) getListData(sql, params, null, null, StaffItem.class);
    }

    @Override
    public List<ShopItem> getShopByStaff(Integer userId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" Select  s.id,s.name,s.image_Url avatar  FROM shop s JOIN STAFF st ON st.shop_id = s.id AND st.user_id =:userId  WHERE st.is_active = 1 AND s.is_active = 1  limit 1  ");
        params.put("userId", userId);
        return (List<ShopItem>) getListData(sql, params, null, null, ShopItem.class);
    }

    @Override
    public List<ShopItem> getShopNotByStaff(Integer userId) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" Select  s.id,s.name,s.image_Url avatar FROM shop s WHERE s.id not in (select shop_id FROM STAFF st WHERE st.user_id =:userId)  limit 5 ");
        params.put("userId", userId);
        return (List<ShopItem>) getListData(sql, params, null, null, ShopItem.class);
    }
}
