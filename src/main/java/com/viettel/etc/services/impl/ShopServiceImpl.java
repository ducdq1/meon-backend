package com.viettel.etc.services.impl;

import com.viettel.etc.dto.request.CreateShopRequest;
import com.viettel.etc.dto.request.SearchShopsRequest;
import com.viettel.etc.repositories.ShopRepository;
import com.viettel.etc.repositories.tables.ShopRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ShopEntity;
import com.viettel.etc.services.ShopService;
import com.viettel.etc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

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
    public Object createShop(CreateShopRequest request) throws TeleCareException {
        validateCreateShop(request);
        ShopEntity entity = new ShopEntity();
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setCatsShopId(request.getCatsShopId());
        entity.setPhone(request.getPhone());
        entity.setLat(request.getLat());
        entity.setLng(request.getLng());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setCreateDate(new Date());
        entity.setTags(request.getTags());
        entity.setDescription(request.getDescription());
        entity.setImageUrl(request.getImageUrl());

        shopRepositoryJPA.save(entity);
        return entity;
    }

    @Override
    public Object updateShop(CreateShopRequest request) throws TeleCareException {
        validateCreateShop(request);
        ShopEntity entity = shopRepositoryJPA.getByCreateUserIdAndShopId(request.getCreateUserId(),request.getId());
        if(entity !=null) {
            entity.setName(request.getName());
            entity.setAddress(request.getAddress());
            entity.setCatsShopId(request.getCatsShopId());
            entity.setPhone(request.getPhone());
            entity.setLat(request.getLat());
            entity.setLng(request.getLng());
            entity.setUpdateUserId(request.getCreateUserId());
            entity.setIsActive(Constants.IS_ACTIVE);
            entity.setUpdateDate(new Date());
            entity.setTags(request.getTags());
            entity.setDescription(request.getDescription());
            entity.setImageUrl(request.getImageUrl());

            shopRepositoryJPA.save(entity);

            return entity;
        }
        return null;
    }

    @Override
    public Object getShopsByUserId(SearchShopsRequest request) throws TeleCareException {
        if (request.getKeySearch() == null) {
            request.setKeySearch("");
        }

        return shopRepositoryJPA.getAllByCreateUserIdAndIsActive(request.getUserId(), request.getKeySearch().trim().toLowerCase());
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