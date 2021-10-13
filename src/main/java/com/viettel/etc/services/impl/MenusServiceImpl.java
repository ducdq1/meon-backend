package com.viettel.etc.services.impl;

import com.viettel.etc.dto.request.CreateMenuGroupRequest;
import com.viettel.etc.dto.request.CreateMenuRequest;
import com.viettel.etc.dto.request.CreateShopRequest;
import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.repositories.tables.MenuGroupsRepositoryJPA;
import com.viettel.etc.repositories.tables.MenusRepositoryJPA;
import com.viettel.etc.repositories.tables.ShopRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MenuEntity;
import com.viettel.etc.repositories.tables.entities.MenuGroupEntity;
import com.viettel.etc.repositories.tables.entities.ShopEntity;
import com.viettel.etc.services.MenusService;
import com.viettel.etc.services.ShopService;
import com.viettel.etc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Autogen class: Lop danh muc chi so sp02
 *
 * @author ToolGen
 * @date Thu Sep 23 08:29:44 ICT 2021
 */
@Service
public class MenusServiceImpl implements MenusService {
    @Autowired
    private MenuGroupsRepositoryJPA menuGroupsRepositoryJPA;

    @Autowired
    private MenusRepositoryJPA menuRepositoryJPA;


    @Override
    public Object getMenuGroups(Integer shopId) throws TeleCareException {
        return menuGroupsRepositoryJPA.getAllByShopIdAndIsActive(shopId, Constants.IS_ACTIVE);
    }

    @Override
    public Object createMenuGroup(CreateMenuGroupRequest request) throws TeleCareException {
        validateMenu(request.getName());
        MenuGroupEntity entity = new MenuGroupEntity();
        entity.setName(request.getName());
        entity.setShopId(request.getShopId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setCreateDate(new Date());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setTags(request.getTags());
        entity.setImageUrl(request.getImageUrl());

        menuGroupsRepositoryJPA.save(entity);
        return entity;
    }

    @Override
    public Object updateMenuGroup(Integer menuGroupId, CreateMenuGroupRequest request) throws TeleCareException {
        validateMenu(request.getName());

        Optional<MenuGroupEntity> menuGroupEntityOptional = menuGroupsRepositoryJPA.findById(menuGroupId);
        if (menuGroupEntityOptional.isPresent()) {
            MenuGroupEntity entity = menuGroupEntityOptional.get();
            entity.setName(request.getName());
            entity.setUpdateDate(new Date());
            entity.setShopId(request.getShopId());
            entity.setUpdateUserId(request.getCreateUserId());
            entity.setTags(request.getTags());
            entity.setImageUrl(request.getImageUrl());

            menuGroupsRepositoryJPA.save(entity);
            return entity;
        }
        return null;
    }

    @Override
    public Object deleteMenuGroup(Integer menuGroupId) throws TeleCareException {
        Optional<MenuGroupEntity> menuGroupEntityOptional = menuGroupsRepositoryJPA.findById(menuGroupId);
        if (menuGroupEntityOptional.isPresent()) {
            MenuGroupEntity entity = menuGroupEntityOptional.get();
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            entity.setUpdateDate(new Date());
            menuGroupsRepositoryJPA.save(entity);
            return true;
        }

        return null;
    }

    @Override
    public Object getMenus(Integer shopId, Integer menuGroupId) throws TeleCareException {
        return menuRepositoryJPA.getAllByShopIdAndMenuGroupIdAndIsActive(shopId, menuGroupId, Constants.IS_ACTIVE);
    }

    @Override
    public Object getMenus(Integer shopId) throws TeleCareException {
        return menuRepositoryJPA.getAllByShopIdAndIsActive(shopId, Constants.IS_ACTIVE);
    }

    @Override
    public Object createMenu(CreateMenuRequest request) throws TeleCareException {
        validateMenu(request.getName());

        MenuEntity entity = new MenuEntity();
        entity.setName(request.getName());
        entity.setShopId(request.getShopId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setCreateDate(new Date());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setTags(request.getTags());
        entity.setImageUrl(request.getImageUrl());
        entity.setMenuGroupId(request.getMenuGroupId());
        entity.setDescription(request.getDescription());
        entity.setDiscount(request.getDiscount());
        entity.setOrderPriority(request.getOrderPriority());
        entity.setPrice(request.getPrice());

        menuRepositoryJPA.save(entity);

        return entity;
    }

    @Override
    public Object updateMenu(Integer menuId, CreateMenuRequest request) throws TeleCareException {
        validateMenu(request.getName());


        Optional<MenuEntity> menuEntityOptional = menuRepositoryJPA.findById(menuId);
        if (menuEntityOptional.isPresent()) {
            MenuEntity entity = menuEntityOptional.get();
            entity.setName(request.getName());
            entity.setUpdateDate(new Date());
            entity.setShopId(request.getShopId());
            entity.setUpdateUserId(request.getCreateUserId());
            entity.setMenuGroupId(request.getMenuGroupId());
            entity.setTags(request.getTags());
            entity.setImageUrl(request.getImageUrl());
            entity.setMenuGroupId(request.getMenuGroupId());
            entity.setDescription(request.getDescription());
            entity.setDiscount(request.getDiscount());
            entity.setOrderPriority(request.getOrderPriority());
            entity.setPrice(request.getPrice());
            menuRepositoryJPA.save(entity);
            return entity;
        }

        return null;
    }

    private void validateMenu(String name) throws TeleCareException{
        if(StringUtils.isNullOrEmpty(name)){
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.menu.name.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }

    @Override
    public Object deleteMenu(Integer menuId) throws TeleCareException {
        Optional<MenuEntity> menuEntityOptional = menuRepositoryJPA.findById(menuId);
        if (menuEntityOptional.isPresent()) {
            MenuEntity entity = menuEntityOptional.get();
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            entity.setUpdateDate(new Date());
            menuRepositoryJPA.save(entity);
            return true;
        }
        return null;
    }


}