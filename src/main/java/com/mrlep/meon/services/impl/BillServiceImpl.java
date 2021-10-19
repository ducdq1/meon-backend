package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.CreateShopTableRequest;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.BillRepositoryJPA;
import com.mrlep.meon.repositories.tables.ShopTableRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.ShopTableEntity;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.ShopTableService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepositoryJPA billRepositoryJPA;


    @Autowired
    private ShopTableService shopTableService;


    @Autowired
    private ShopRepository shopRepository;

    private void validateCreateBill(CreateBillRequest request) throws TeleCareException {
        Integer countTableAndBill = billRepositoryJPA.checkExistBillAndTable(request.getShopId(), request.getTableId());

        if (countTableAndBill != null && countTableAndBill > 0) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.table.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

    }

    @Override
    public Object getBillsByShop(Integer shopId) throws TeleCareException {
        return billRepositoryJPA.getAllByShopIdAndIsActiveOrderByCreateDateDesc(shopId, Constants.IS_ACTIVE);
    }

    @Override
    public Object createBill(CreateBillRequest request) throws TeleCareException {
        validateCreateBill(request);

        BillEntity entity = new BillEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setShopId(request.getShopId());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setStatus(Constants.TABLE_STATUS_READY);
        entity.setCreateDate(new Date());
        entity.setTableId(request.getTableId());
        entity.setStatus(Constants.BILL_STATUS_PROGRESS);
        entity.setReconfirmMessage(request.getReconfirmMessage());
        billRepositoryJPA.save(entity);

        shopTableService.updateShopTableStatus(request.getCreateUserId(), request.getTableId(), Constants.TABLE_STATUS_IN_USE);
        return entity.getId();
    }

    @Override
    public Object updateBill(CreateBillRequest request) throws TeleCareException {
        Optional<BillEntity> entityOptional = billRepositoryJPA.findById(request.getBillId());
        if (entityOptional.isPresent()) {
            BillEntity entity = entityOptional.get();
            if (Constants.BILL_STATUS_DONE == entity.getStatus().intValue()) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.status"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }

            entity.setName(request.getName());
            entity.setDescription(request.getDescription());
            entity.setShopId(request.getShopId());
            entity.setStatus(request.getStatus());
            entity.setCreateUserId(request.getCreateUserId());
            entity.setIsActive(Constants.IS_ACTIVE);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(request.getCreateUserId());
            entity.setTableId(request.getTableId());
            entity.setReconfirmMessage(request.getReconfirmMessage());
            entity.setTotalMoney(request.getTotalMoney());
            billRepositoryJPA.save(entity);
            return true;
        }

        return null;
    }

    @Override
    public Object updateBillStatus(Integer userId, Integer billId, Integer status) throws TeleCareException {
        Optional<BillEntity> entityOptional = billRepositoryJPA.findById(billId);
        if (entityOptional.isPresent()) {
            BillEntity entity = entityOptional.get();
            entity.setStatus(status);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);
            billRepositoryJPA.save(entity);

            if (status.intValue() == Constants.BILL_STATUS_DONE
                    || status.intValue() == Constants.BILL_STATUS_CANCEL) {
                //cap nhat lai trang thai ban OK
                shopTableService.updateShopTableStatus(userId, entity.getTableId(), Constants.TABLE_STATUS_READY);
            }
            return true;
        }
        return null;
    }

    @Override
    public Object deleteBill(Integer billId, Integer userId) throws TeleCareException {
        Optional<BillEntity> entityOptional = billRepositoryJPA.findById(billId);
        if (entityOptional.isPresent()) {
            BillEntity entity = entityOptional.get();
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);
            billRepositoryJPA.save(entity);
            return true;
        }
        return null;
    }
}