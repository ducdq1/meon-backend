package com.viettel.etc.services;

import com.viettel.etc.dto.request.CreateShopTableRequest;
import com.viettel.etc.utils.TeleCareException;

public interface ShopTableService {
    Object getShopTables(Integer shopId) throws TeleCareException;
    Object createShopTable(CreateShopTableRequest request) throws TeleCareException;
    Object updateShopTable(CreateShopTableRequest request) throws TeleCareException;
    Object updateShopTableStatus(Integer userId, Integer tableId,Integer status) throws TeleCareException;
    Object deleteShopTable(Integer tableId,Integer userId) throws TeleCareException;
}