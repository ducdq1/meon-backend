package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.CreateShopTableRequest;
import com.mrlep.meon.dto.response.DetailTableResponse;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.ShopTableRepository;
import com.mrlep.meon.repositories.tables.BillRepositoryJPA;
import com.mrlep.meon.repositories.tables.BillTablesRepositoryJPA;
import com.mrlep.meon.repositories.tables.ShopTableRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.BillTablesEntity;
import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
import com.mrlep.meon.services.ShopTableService;
import com.mrlep.meon.utils.*;
import org.checkerframework.checker.units.qual.C;
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
public class ShopTableServiceImpl implements ShopTableService {

    @Autowired
    private ShopTableRepositoryJPA shopTableRepositoryJPA;
    @Autowired
    private ShopTableRepository shopTableRepository;
    @Autowired
    private BillTablesRepositoryJPA billTablesRepositoryJPA;
    @Autowired
    private BillRepositoryJPA billRepositoryJPA;
    @Autowired
    private ShopRepository shopRepository;

    private void validateCreateShop(CreateShopTableRequest request) throws TeleCareException {

        if (StringUtils.isNullOrEmpty(request.getName())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.table.name.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

    }

    @Override
    public Object getShopTablesByStatus(Integer shopId, Integer status, Integer startRecord, Integer pageSize) throws TeleCareException {
        return shopTableRepository.getTableOfShopAndStatus(shopId, status, startRecord, pageSize);
    }

    @Override
    public Object getShopTables(Integer shopId) throws TeleCareException {
        return shopTableRepositoryJPA.getAllByShopIdAndIsActiveOrderByUniqueNumberAsc(shopId, Constants.IS_ACTIVE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object createShopTable(CreateShopTableRequest request) throws TeleCareException {
        validateCreateShop(request);

        /* List<ShopTableEntity> shopTableEntityList = shopTableRepositoryJPA.getAllByShopIdAndIsActiveAndUniqueNumber(request.getShopId(), Constants.IS_ACTIVE, request.getUniqueNumber());
        if (!shopTableEntityList.isEmpty()) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.table.unique.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }*/

        ShopTableEntity entity = new ShopTableEntity();
        entity.setName(request.getName());
        entity.setCapability(request.getCapability());
        entity.setShopId(request.getShopId());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setStatus(Constants.TABLE_STATUS_READY);
        entity.setCreateDate(new Date());
        entity.setImageUrl(request.getImageUrl());
        entity.setAreaId(request.getAreaId());
        shopTableRepositoryJPA.save(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateShopTable(CreateShopTableRequest request) throws TeleCareException {
        validateCreateShop(request);

        Optional<ShopTableEntity> entityOptional = shopTableRepositoryJPA.findById(request.getTableId());
        if (entityOptional.isPresent()) {
            ShopTableEntity entity = entityOptional.get();
            entity.setName(request.getName());
            entity.setCapability(request.getCapability());
            entity.setImageUrl(request.getImageUrl());
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(request.getCreateUserId());
            entity.setAreaId(request.getAreaId());
            shopTableRepositoryJPA.save(entity);
            return true;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateShopTableStatus(Integer userId, Integer tableId, Integer status) throws TeleCareException {
        Optional<ShopTableEntity> entityOptional = shopTableRepositoryJPA.findById(tableId);
        if (entityOptional.isPresent()) {
            ShopTableEntity entity = entityOptional.get();
            entity.setStatus(status);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);
            shopTableRepositoryJPA.save(entity);
            return true;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object deleteShopTable(Integer tableId, Integer userId) throws TeleCareException {
        Optional<ShopTableEntity> entityOptional = shopTableRepositoryJPA.findById(tableId);
        if (entityOptional.isPresent()) {
            ShopTableEntity entity = entityOptional.get();
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);
            shopTableRepositoryJPA.save(entity);
            return true;
        }
        return null;
    }

    @Override
    public Object getTableDetail(Integer id) throws TeleCareException {
        DetailTableResponse response = new DetailTableResponse();
        ShopTableEntity shopTableEntity = shopTableRepositoryJPA.getByIdAndIsActive(id, Constants.IS_ACTIVE);
        if (shopTableEntity != null) {
            response.setTable(shopTableEntity);
            List<BillEntity> billEntitys = billRepositoryJPA.findByTableIdAndIsActive(id);
            if (billEntitys != null && !billEntitys.isEmpty()) {
                for (BillEntity billEntity : billEntitys) {
//                    Integer billId = billTablesEntity.getBillId();
//                    BillEntity billEntity = billRepositoryJPA.findByIdAndIsActive(billId, Constants.IS_ACTIVE);
//                    if (billEntity != null && billEntity.getStatus().intValue() != Constants.BILL_STATUS_DONE
//                            && billEntity.getStatus().intValue() != Constants.BILL_STATUS_CANCEL) {
                    response.setBillInfo(billEntity);
//                    }
                }
            }
        }
        return response;
    }
}