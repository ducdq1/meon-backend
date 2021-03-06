package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateMenuGroupRequest;
import com.mrlep.meon.dto.request.CreateMenuRequest;
import com.mrlep.meon.dto.request.SearchMenusRequest;
import com.mrlep.meon.utils.TeleCareException;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface MenusService {
    Object getMenuGroups(Integer shopId) throws TeleCareException;
    Object createMenuGroup(CreateMenuGroupRequest request) throws TeleCareException;
    Object updateMenuGroup(Integer menuGroupId,CreateMenuGroupRequest request) throws TeleCareException;
    Object deleteMenuGroup(Integer menuGroupId) throws TeleCareException;

    Object getMenus(Integer shopId,Integer menuGroupId) throws TeleCareException;
    Object getMenus(Integer shopId) throws TeleCareException;
    Object createMenu(CreateMenuRequest request) throws TeleCareException;
    Object updateMenu(Integer menuId, CreateMenuRequest request) throws TeleCareException;
    Object deleteMenu(Integer menuId) throws TeleCareException;
    Object getMenuDetail(Integer menuId) throws TeleCareException;

    Object getRecommendMenus(SearchMenusRequest request) throws TeleCareException;
}