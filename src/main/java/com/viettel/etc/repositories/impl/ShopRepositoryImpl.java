package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.dto.response.SearchShopResponse;
import com.viettel.etc.repositories.ShopRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.StringUtils;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public class ShopRepositoryImpl extends CommonDataBaseRepository implements ShopRepository {
    @Override
    public ResultSelectEntity getRecommendShop(SearchShopsRequest request) {
        String keySearch = request.getKeySearch();
        String orderBy = request.getOrderBy();
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT s.name, s.address, s.phone, s.image_url imageUrl, s.description,s.tags, cs.name shopTypeName,cs.url_icon shopTypeIcon, ");
        sql.append(" s.rating, s.open_time openTime,s.close_time closeTime,s.special_tag, s.is_verify isVerify, ");
        if (request.getLat() != null && request.getLng() != null) {
            sql.append(" ABS(lat-$user_lat) + ABS(lng - $user_lng)  AS distance ");
        } else {
            sql.append(" 0 AS distance ");
        }

        sql.append("FROM SHOP s ");
        sql.append("LEFT JOIN CATS_SHOP cs on cs.id = s.cats_shop_id ");
        sql.append("WHERE 1=1 ");


        if (!StringUtils.isNullOrEmpty(request.getKeySearch())) {
            sql.append(" AND (LOWER (s.name) like :keySearch escape '#'  ");
            sql.append(" OR LOWER (s.phone) like :keySearch escape '#'  ");
            sql.append(" OR LOWER (s.address) like :keySearch escape '#' ) ");
            params.put("keySearch", FnCommon.getSearchLikeValue(keySearch.trim().toLowerCase()));
        }

        if ("popular".equals(request.getOrderBy())) {
            sql.append(" ORDER BY s.create_date DESC ");
        } else if ("new".equals(request.getOrderBy())) {
            sql.append(" ORDER BY s.create_date DESC ");
        } else if ("nearest".equals(request.getOrderBy())) {
            sql.append(" ORDER BY distance ASC ");
        } else if ("trending".equals(request.getOrderBy())) {
            sql.append(" ORDER BY s.create_date DESC ");
        }else{
            sql.append(" ORDER BY s.create_date DESC ");
        }

        return getListDataAndCount(sql, params, request.getStartRecord(), request.getPageSize(), SearchShopResponse.class);
    }
}
