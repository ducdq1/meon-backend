package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.dto.response.SearchShopResponse;
import com.viettel.etc.repositories.ShopRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.StringUtils;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import java.util.HashMap;

public class ShopRepositoryImpl extends CommonDataBaseRepository implements ShopRepository {
    @Override
    public ResultSelectEntity search(SearchShopsRequest request) {
        String keySearch = request.getKeySearch();
        String orderBy = request.getOrderBy();
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT s.name, s.address, s.phone, ");
        if(request.getLat() !=null && request.getLng() != null) {
            sql.append(", ABS(lat-$user_lat) + ABS(lng - $user_lng) ASC AS distance ");
        }else{
            sql.append(" null AS distance ");
        }

        sql.append("FROM SHOP s WHERE 1=1 ");

        if(!StringUtils.isNullOrEmpty(request.getKeySearch())){
            sql.append(" AND (LOWER (s.name) like :keySearch escape '#'  ");
            sql.append(" OR LOWER (s.phone) like :keySearch escape '#'  ");
            sql.append(" OR LOWER (s.address) like :keySearch escape '#' ) ");
            params.put("keySearch", FnCommon.getSearchLikeValue(keySearch.trim().toLowerCase()));
        }

        if(request.getLat() !=null && request.getLng() != null){
        }


        sql.append(" ORDER BY q.level_of_risk DESC ");
        sql.append(" ORDER BY q.level_of_risk DESC ");

        return getListDataAndCount(sql, params, request.getStartRecord(), request.getPageSize(), SearchShopResponse.class);
    }
}
