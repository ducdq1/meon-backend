package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.request.SearchMenusRequest;
import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.dto.response.SearchMenusResponse;
import com.viettel.etc.dto.response.SearchShopResponse;
import com.viettel.etc.repositories.MenusRepository;
import com.viettel.etc.repositories.ShopRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.StringUtils;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public class MenusRepositoryImpl extends CommonDataBaseRepository implements MenusRepository {
    @Override
    public ResultSelectEntity getRecommendMenus(SearchMenusRequest request) {
        String keySearch = request.getKeySearch();
        String orderBy = request.getOrderBy();
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT s.id shopId, s.name shopName, s.address shopAddress, s.phone shopPhone, m.image_url imageUrl, " +
                " m.tags , cs.name shopTypeName,");
        sql.append(" s.open_time openTime,s.close_time closeTime, s.is_verify isVerifyShop, m.name, m.price, m.discount, m.description, 100 AS numberOrder, ");
        sql.append("mg.name menuGroupName, m.id, ");

        if (request.getLat() != null && request.getLng() != null) {
            sql.append(" concat(ABS(lat-$user_lat) + ABS(lng - $user_lng),'m')  AS distance ");
        } else {
            sql.append(" '0m' AS distance ");
        }

        sql.append("FROM MENU m ");
        sql.append("JOIN menu_group mg on m.menu_group_id = mg.id AND mg.is_active=1 ");
        sql.append("JOIN  SHOP s on m.shop_id = s.id AND s.is_active=1 ");
        sql.append("LEFT JOIN CATS_SHOP cs on cs.id = s.cats_shop_id ");
        sql.append("WHERE m.is_active=1 ");


        if (!StringUtils.isNullOrEmpty(request.getKeySearch())) {
            sql.append(" AND (LOWER (m.name) like :keySearch escape '#'  ");
            sql.append(" OR LOWER (mg.name) like :keySearch escape '#' ) ");
            params.put("keySearch", FnCommon.getSearchLikeValue(keySearch.trim().toLowerCase()));
        }

        if (Constants.ORDER_BY_POPULAR.equals(request.getOrderBy())) {
            sql.append(" ORDER BY m.create_date DESC ");
        } else if (Constants.ORDER_BY_NEW.equals(request.getOrderBy())) {
            sql.append(" ORDER BY m.create_date DESC ");
        } else if (Constants.ORDER_BY_NEAREST.equals(request.getOrderBy())) {
            sql.append(" ORDER BY distance ASC ");
        } else if (Constants.ORDER_BY_TRENDING.equals(request.getOrderBy())) {
            sql.append(" ORDER BY m.create_date DESC ");
        } else {
            sql.append(" ORDER BY m.create_date DESC ");
        }


        return getListDataAndCount(sql, params, request.getStartRecord(), request.getPageSize(), SearchMenusResponse.class);
    }
}
