package com.viettel.etc.services.impl;

import com.viettel.etc.dto.request.CreateMenuGroupRequest;
import com.viettel.etc.dto.request.CreateMenuRequest;
import com.viettel.etc.dto.request.CreateShopRequest;
import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.repositories.tables.ShopRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ShopEntity;
import com.viettel.etc.services.MenusService;
import com.viettel.etc.services.ShopService;
import com.viettel.etc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Autogen class: Lop danh muc chi so sp02
 *
 * @author ToolGen
 * @date Thu Sep 23 08:29:44 ICT 2021
 */
@Service
public class MenusServiceImpl implements MenusService {

    @Override
    public Object getMenuGroups(Integer shopId) throws TeleCareException {
        return null;
    }

    @Override
    public Object createMenuGroup(Integer menuGroupId, CreateMenuGroupRequest request) throws TeleCareException {
        return null;
    }

    @Override
    public Object getMenus(Integer shopId) throws TeleCareException {
        return null;
    }

    @Override
    public Object createMenu(Integer menuId, CreateMenuRequest request) throws TeleCareException {
        return null;
    }
}