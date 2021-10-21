package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.CreateShopTableRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.BillMembersRepositoryJPA;
import com.mrlep.meon.repositories.tables.BillRepositoryJPA;
import com.mrlep.meon.repositories.tables.BillTablesRepositoryJPA;
import com.mrlep.meon.repositories.tables.ShopTableRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.*;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.services.ShopTableService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepositoryJPA billRepositoryJPA;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ShopTableService shopTableService;


    @Autowired
    private OrderItemService orderItemlService;

    @Autowired
    private BillTablesRepositoryJPA billTablesRepositoryJPA;


    @Autowired
    private BillMembersRepositoryJPA billMembersRepositoryJPA;


    @Autowired
    private ShopRepository shopRepository;

    private void validateCreateBill(CreateBillRequest request) throws TeleCareException {
        Integer countTableAndBill = billRepositoryJPA.checkExistBillAndTable(request.getShopId(), request.getTableId());

        if (countTableAndBill != null && countTableAndBill > 0) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.table.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object getDetailBills(Integer billId) throws TeleCareException {
        DetailBillResponse detailBillResponse = billRepository.getDetailBill(billId);
        List<OrderItemEntity> orderItemEntitiesList = (List<OrderItemEntity>) orderItemlService.getOrderItemsByBill(billId);
        detailBillResponse.setOrderItems(orderItemEntitiesList);
        return detailBillResponse;
    }

    @Override
    public Object getBillsByShop(Integer shopId) throws TeleCareException {
        return billRepositoryJPA.getAllByShopIdAndIsActiveOrderByCreateDateDesc(shopId, Constants.IS_ACTIVE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

        //luu ban
        BillTablesEntity billTablesEntity = new BillTablesEntity();
        billTablesEntity.setBillId(entity.getId());
        billTablesEntity.setCreateDate(new Date());
        billTablesEntity.setIsActive(Constants.IS_ACTIVE);
        billTablesEntity.setCreateUserId(entity.getCreateUserId());
        billTablesEntity.setTableId(request.getTableId());
        billTablesRepositoryJPA.save(billTablesEntity);

        //Luu bill members
        BillMembersEntity billMembersEntity = new BillMembersEntity();
        billMembersEntity.setBillId(entity.getId());
        billMembersEntity.setUserId(entity.getCreateUserId());
        billMembersEntity.setCreateDate(new Date());
        billMembersEntity.setCreateUserId(entity.getCreateUserId());
        billMembersEntity.setIsActive(Constants.IS_ACTIVE);
        billMembersRepositoryJPA.save(billMembersEntity);

        shopTableService.updateShopTableStatus(request.getCreateUserId(), request.getTableId(), Constants.TABLE_STATUS_IN_USE);
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public Object updateBillStatus(Integer userId, Integer billId, Integer status) throws TeleCareException {
        Optional<BillEntity> entityOptional = billRepositoryJPA.findById(billId);
        if (entityOptional.isPresent()) {
            BillEntity entity = entityOptional.get();

            if (entity.getStatus() == Constants.BILL_STATUS_DONE) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.status"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }

            if (status != null) {
                entity.setStatus(status);
            }

            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);

            entity.setTotalMoney(getTotalMoney(entity.getId()));
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


    private Integer getTotalMoney(Integer billId) throws TeleCareException {
        List<OrderItemEntity> orderItemEntities = (List<OrderItemEntity>) orderItemService.getOrderItemsByBill(billId);
        Integer totalMoney = 0;
        for (OrderItemEntity orderItemEntity : orderItemEntities) {
            if (orderItemEntity.getAmount() != null && orderItemEntity.getPrice() != null) {
                totalMoney += (int) (orderItemEntity.getPrice() * orderItemEntity.getAmount());
            }
        }
        return totalMoney;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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