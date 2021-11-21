package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.CreateMenuGroupRequest;
import com.mrlep.meon.dto.request.CreateMenuRequest;
import com.mrlep.meon.dto.request.SearchMenusRequest;
import com.mrlep.meon.dto.response.DetailMenuResponse;
import com.mrlep.meon.repositories.MenusRepository;
import com.mrlep.meon.repositories.tables.MediaRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenuGroupsRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenusOptionRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenusRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.MediaEntity;
import com.mrlep.meon.repositories.tables.entities.MenuEntity;
import com.mrlep.meon.repositories.tables.entities.MenuGroupEntity;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;
import com.mrlep.meon.services.MediaService;
import com.mrlep.meon.services.MenusService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private MenusOptionRepositoryJPA menusOptionRepositoryJPA;

    @Autowired
    private MenusRepository menusRepository;

    @Override
    public Object getMenuGroups(Integer shopId) throws TeleCareException {
        return menuGroupsRepositoryJPA.getAllByShopIdAndIsActive(shopId, Constants.IS_ACTIVE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
        if (menuGroupId >= 0) {
            return menuRepositoryJPA.getAllByShopIdAndMenuGroupIdAndIsActive(shopId, menuGroupId, Constants.IS_ACTIVE);
        } else {
            return menuRepositoryJPA.getAllByShopIdAndIsActive(shopId, Constants.IS_ACTIVE);
        }
    }

    @Override
    public Object getMenus(Integer shopId) throws TeleCareException {
        List<MenuEntity> menuEntities = menuRepositoryJPA.getAllByShopIdAndIsActive(shopId, Constants.IS_ACTIVE);
        for (MenuEntity menuEntity : menuEntities) {
            menuEntity.setMedias(mediaRepositoryJPA.findAllByIsActiveAndObjectTypeAndObjectId(Constants.IS_ACTIVE,Constants.MENU_MEDIA_TYPE,menuEntity.getId()));
        }

        return menuEntities;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object createMenu(CreateMenuRequest request) throws TeleCareException {
        validateMenu(request.getName());

        MenuEntity entity = new MenuEntity();
        entity = setMenuInfo(request, entity);
        saveMenuOption(request, entity);
        mediaService.saveMedias(request.getMedias(), request.getDeletedMedias(), entity.getId(), Constants.MENU_MEDIA_TYPE, request.getCreateUserId());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateMenu(Integer menuId, CreateMenuRequest request) throws TeleCareException {
        validateMenu(request.getName());
        Optional<MenuEntity> menuEntityOptional = menuRepositoryJPA.findById(menuId);
        if (menuEntityOptional.isPresent()) {
            MenuEntity entity = menuEntityOptional.get();
            entity = setMenuInfo(request, entity);
            mediaService.saveMedias(request.getMedias(), request.getDeletedMedias(), entity.getId(), Constants.MENU_MEDIA_TYPE, request.getCreateUserId());
            saveMenuOption(request, entity);

            return entity;
        }

        return null;
    }

    private void saveMenuOption(CreateMenuRequest request, MenuEntity entity) {
        if (request.getOptions() != null) {
            for (MenuOptionEntity menuOptionEntity : request.getOptions()) {
                MenuOptionEntity updateMenu = null;
                if (menuOptionEntity.getId() != null) {
                    updateMenu = menusOptionRepositoryJPA.getByIdAndIsActive(menuOptionEntity.getId(), Constants.IS_ACTIVE);
                    if (updateMenu == null) {
                        continue;
                    }
                    updateMenu.setUpdateDate(new Date());
                    updateMenu.setUpdateUserId(request.getCreateUserId());
                } else {
                    updateMenu = new MenuOptionEntity();
                    updateMenu.setCreateDate(new Date());
                    updateMenu.setCreateUserId(request.getCreateUserId());
                }

                updateMenu.setName(menuOptionEntity.getName());
                updateMenu.setPrice(menuOptionEntity.getPrice());
                updateMenu.setMenuId(entity.getId());
                updateMenu.setIsActive(Constants.IS_ACTIVE);
                menusOptionRepositoryJPA.save(updateMenu);
            }
        }

        if (request.getDeletedOptions() != null) {
            for (Integer optionId : request.getDeletedOptions()) {
                MenuOptionEntity menuOptionEntity = menusOptionRepositoryJPA.getByIdAndIsActive(optionId, Constants.IS_ACTIVE);
                if (menuOptionEntity != null) {
                    menuOptionEntity.setIsActive(Constants.IS_NOT_ACTIVE);
                    menuOptionEntity.setUpdateDate(new Date());
                    menuOptionEntity.setUpdateUserId(request.getCreateUserId());
                    menusOptionRepositoryJPA.save(menuOptionEntity);
                }
            }
        }
    }

    private MenuEntity setMenuInfo(CreateMenuRequest request, MenuEntity entity) {
        entity.setName(request.getName());
        entity.setShopId(request.getShopId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setCreateDate(new Date());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setTags(request.getTags());
        entity.setImageUrl(request.getImageUrl());
        entity.setMenuGroupId(request.getMenuGroupId());
        entity.setDescription(request.getDescription());
        entity.setDiscountDescription(request.getDiscountDescription());
        entity.setDiscountType(request.getDiscountType());
        entity.setDiscountValue(request.getDiscountValue());
        entity.setOrderPriority(request.getOrderPriority());
        entity.setPrice(request.getPrice());
        entity.setUnit(request.getUnit());
        entity.setIsOddNumber(request.getIsOddNumber());
        entity.setProcessType(request.getProcessType());

        if (entity.getOrderNumber() == null) {
            entity.setOrderNumber(0);
        }

        menuRepositoryJPA.save(entity);

        return entity;
    }

    private void validateMenu(String name) throws TeleCareException {
        if (StringUtils.isNullOrEmpty(name)) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.menu.name.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        DetailMenuResponse response = new DetailMenuResponse();
        MenuEntity menuEntity = menuRepositoryJPA.getByIdAndIsActive(menuId, Constants.IS_ACTIVE);
        response.setMenu(menuEntity);
        List<MediaEntity> mediaEntityList = mediaRepositoryJPA.findAllByIsActiveAndObjectTypeAndObjectId(Constants.IS_ACTIVE, Constants.MENU_MEDIA_TYPE, menuId);
        response.setMedias(mediaEntityList);
        response.setOptions(menusOptionRepositoryJPA.getAllByMenuIdAndIsActive(menuId, Constants.IS_ACTIVE));

        return response;
    }

    @Override
    public Object getRecommendMenus(SearchMenusRequest request) throws TeleCareException {
        return menusRepository.getRecommendMenus(request);
    }

}