package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateShopRequest;
import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.utils.TeleCareException;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface ShopService {
    Object createShop(CreateShopRequest request) throws TeleCareException;
    Object updateShop(CreateShopRequest request) throws TeleCareException;
    Object getShopsByUserId(SearchShopsRequest request) throws TeleCareException;
    Object getShopsById(Integer shopId) throws TeleCareException;
    Object getRecommendShops(SearchShopsRequest request) throws TeleCareException;
    Object getAllShops() throws TeleCareException;

    Object getShopsByStaff(Integer userId) throws TeleCareException;
}