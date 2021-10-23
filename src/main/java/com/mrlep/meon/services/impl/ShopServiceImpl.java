package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.CreateShopRequest;
import com.mrlep.meon.dto.request.SearchShopsRequest;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.ShopRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.ShopEntity;
import com.mrlep.meon.services.ShopService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Autogen class: Lop danh muc chi so sp02
 *
 * @author ToolGen
 * @date Thu Sep 23 08:29:44 ICT 2021
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepositoryJPA shopRepositoryJPA;

    @Autowired
    private ShopRepository shopRepository;

    private void validateCreateShop(CreateShopRequest request) throws TeleCareException {

        if (StringUtils.isNullOrEmpty(request.getName())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.shop.name.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if (StringUtils.isNullOrEmpty(request.getAddress())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.shop.address.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object createShop(CreateShopRequest request) throws TeleCareException {
        validateCreateShop(request);
        ShopEntity entity = new ShopEntity();
        entity = setShopEntityInfo(entity, request);
        entity.setCreateDate(new Date());
        entity.setCreateUserId(request.getCreateUserId());
        shopRepositoryJPA.save(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateShop(CreateShopRequest request) throws TeleCareException {
        validateCreateShop(request);
        ShopEntity entity = shopRepositoryJPA.getByCreateUserIdAndShopId(request.getCreateUserId(), request.getId());
        if (entity != null) {
            entity = setShopEntityInfo(entity, request);
            entity.setUpdateUserId(request.getCreateUserId());
            entity.setUpdateDate(new Date());
            shopRepositoryJPA.save(entity);

            return entity;
        }
        return null;
    }

    private ShopEntity setShopEntityInfo(ShopEntity entity, CreateShopRequest request) {
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setCatsShopId(request.getCatsShopId());
        entity.setPhone(request.getPhone());
        entity.setLat(request.getLat());
        entity.setLng(request.getLng());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setTags(request.getTags());
        entity.setDescription(request.getDescription());
        entity.setImageUrl(request.getImageUrl());
        entity.setOpenTime(request.getOpenTime());
        entity.setCloseTime(request.getCloseTime());
        if (entity.getLikeNumber() == null) {
            entity.setLikeNumber(0);
        }
        return entity;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object getShopsByUserId(SearchShopsRequest request) throws TeleCareException {
        if (request.getKeySearch() == null) {
            request.setKeySearch("");
        }

        return shopRepositoryJPA.getAllByCreateUserIdAndIsActive(request.getUserId(), request.getKeySearch().trim().toLowerCase());
    }

    @Override
    public Object getShopsById(Integer shopId) throws TeleCareException {
        ShopEntity entity = shopRepositoryJPA.getByIdAndIsActive(shopId, Constants.IS_ACTIVE);
        return entity;
    }

    @Override
    public Object getRecommendShops(SearchShopsRequest request) throws TeleCareException {
        return shopRepository.getRecommendShop(request);
    }

    @Override
    public Object getAllShops() throws TeleCareException {
        return shopRepositoryJPA.getAllByIsActive(Constants.IS_ACTIVE);
    }
}