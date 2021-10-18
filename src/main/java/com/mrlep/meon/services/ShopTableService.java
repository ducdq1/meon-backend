package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateShopTableRequest;
import com.mrlep.meon.utils.TeleCareException;

public interface ShopTableService {
    Object getShopTables(Integer shopId) throws TeleCareException;
    Object createShopTable(CreateShopTableRequest request) throws TeleCareException;
    Object updateShopTable(CreateShopTableRequest request) throws TeleCareException;
    Object updateShopTableStatus(Integer userId, Integer tableId,Integer status) throws TeleCareException;
    Object deleteShopTable(Integer tableId,Integer userId) throws TeleCareException;
}