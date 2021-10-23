package com.mrlep.meon.repositories.impl;

import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.dto.response.SearchShopResponse;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.StringUtils;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.mrlep.meon.xlibrary.core.repositories.CommonDataBaseRepository;
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

        sql.append(" SELECT s.id ,s.name, s.address, s.phone, s.image_url imageUrl, s.description,s.tags, cs.name shopTypeName,cs.url_icon shopTypeIcon, ");
        sql.append(" s.like_number likeNumber, s.open_time openTime,s.close_time closeTime,s.special_tag, s.is_verify isVerify, ");
        if (request.getLat() != null && request.getLng() != null) {
            sql.append(" concat(ABS(lat - :userLat) + ABS(lng - :userLng),'m')  AS distance ");
            params.put("userLat",request.getLat());
            params.put("userLng",request.getLng());
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
}
