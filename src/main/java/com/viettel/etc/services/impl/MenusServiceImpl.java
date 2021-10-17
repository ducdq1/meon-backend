package com.viettel.etc.services.impl;

import com.viettel.etc.dto.request.*;
import com.viettel.etc.dto.response.DetailMemuResponse;
import com.viettel.etc.repositories.MenusRepository;
import com.viettel.etc.repositories.tables.MediaRepositoryJPA;
import com.viettel.etc.repositories.tables.MenuGroupsRepositoryJPA;
import com.viettel.etc.repositories.tables.MenusRepositoryJPA;
import com.viettel.etc.repositories.tables.ShopRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MediaEntity;
import com.viettel.etc.repositories.tables.entities.MenuEntity;
import com.viettel.etc.repositories.tables.entities.MenuGroupEntity;
import com.viettel.etc.repositories.tables.entities.ShopEntity;
import com.viettel.etc.services.MediaService;
import com.viettel.etc.services.MenusService;
import com.viettel.etc.services.ShopService;
import com.viettel.etc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    @Autowired
    private MediaRepositoryJPA mediaRepositoryJPA;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MenusRepository menusRepository;

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
        mediaService.saveMedias(request.getMedias(), request.getDeletedMedias(), entity.getId(), Constants.MENU_GROUP_MEDIA_TYPE, request.getCreateUserId());

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
            mediaService.saveMedias(request.getMedias(), request.getDeletedMedias(), entity.getId(), Constants.MENU_GROUP_MEDIA_TYPE, request.getCreateUserId());

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
        if(menuGroupId >=0) {
            return menuRepositoryJPA.getAllByShopIdAndMenuGroupIdAndIsActive(shopId, menuGroupId, Constants.IS_ACTIVE);
        }else{
            return menuRepositoryJPA.getAllByShopIdAndIsActive(shopId,Constants.IS_ACTIVE);
        }
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
        mediaService.saveMedias(request.getMedias(), request.getDeletedMedias(), entity.getId(), Constants.MENU_MEDIA_TYPE, request.getCreateUserId());
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
            mediaService.saveMedias(request.getMedias(), request.getDeletedMedias(), entity.getId(), Constants.MENU_MEDIA_TYPE, request.getCreateUserId());
            return entity;
        }

        return null;
    }

    private void validateMenu(String name) throws TeleCareException {
        if (StringUtils.isNullOrEmpty(name)) {
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

    @Override
    public Object getMenuDetail(Integer menuId) throws TeleCareException {
        DetailMemuResponse response = new DetailMemuResponse();
        MenuEntity menuEntity = menuRepositoryJPA.getByIdAndIsActive(menuId, Constants.IS_ACTIVE);
        response.setMenu(menuEntity);
        List<MediaEntity> mediaEntityList = mediaRepositoryJPA.findAllByIsActiveAndObjectTypeAndObjectId(Constants.IS_ACTIVE, Constants.MENU_MEDIA_TYPE, menuId);
        response.setMedias(mediaEntityList);
        
        return response;
    }

    @Override
    public Object getRecommendMenus(SearchMenusRequest request) throws TeleCareException {
        return menusRepository.getRecommendMenus(request);
    }

}