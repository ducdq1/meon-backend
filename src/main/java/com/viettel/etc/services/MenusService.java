package com.viettel.etc.services;

import com.viettel.etc.dto.request.CreateMenuGroupRequest;
import com.viettel.etc.dto.request.CreateMenuRequest;
import com.viettel.etc.dto.request.CreateShopRequest;
import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.utils.TeleCareException;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface MenusService {
    Object getMenuGroups(Integer shopId) throws TeleCareException;
    Object createMenuGroup(Integer menuGroupId,CreateMenuGroupRequest request) throws TeleCareException;

    Object getMenus(Integer shopId) throws TeleCareException;
    Object createMenu(Integer menuId, CreateMenuRequest request) throws TeleCareException;

}