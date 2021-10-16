package com.viettel.etc.services;

import com.viettel.etc.dto.request.*;
import com.viettel.etc.utils.TeleCareException;

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
    Object getRecommendShops(SearchShopsRequest request) throws TeleCareException;
    Object getAllShops() throws TeleCareException;
}