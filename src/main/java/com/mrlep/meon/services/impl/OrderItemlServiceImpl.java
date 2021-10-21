package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.AddOrderItemRequest;
import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.BillRepositoryJPA;
import com.mrlep.meon.repositories.tables.OrderItemlRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.services.ShopTableService;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.ErrorApp;
import com.mrlep.meon.utils.MessagesUtils;
import com.mrlep.meon.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderItemlServiceImpl implements OrderItemService {

    @Autowired
    private BillRepositoryJPA billRepositoryJPA;

    @Autowired
    private OrderItemlRepositoryJPA orderItemlRepositoryJPA;

    @Autowired
    private ShopTableService shopTableService;
    @Autowired
    private BillService billService;

    @Autowired
    private ShopRepository shopRepository;

    private void validateAddOrderItem(AddOrderItemRequest request) throws TeleCareException {
        BillEntity billEntity = billRepositoryJPA.findByIdAndIsActive(request.getBillId(), Constants.IS_ACTIVE);
        if (billEntity == null || billEntity.getStatus().intValue() == Constants.BILL_STATUS_DONE) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

//        Integer countTableAndBill = billRepositoryJPA.checkExistBillAndTable(request.getShopId(), request.getTableId());
//
//        if (countTableAndBill != null && countTableAndBill > 0) {
//            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.table.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
//        }

    }


    @Override
    public Object getOrderItemsByBill(Integer billId) throws TeleCareException {
        return orderItemlRepositoryJPA.getAllByBillIdAndIsActive(billId,Constants.IS_ACTIVE);
    }

    @Override
    public Object addOrderItem(AddOrderItemRequest request) throws TeleCareException {
        validateAddOrderItem(request);
        OrderItemEntity entity = new OrderItemEntity();
        entity.setCreateDate(new Date());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setStatus(Constants.ORDER_ITEM_STSTUS_PROGRESS);
        entity = setOrderInfo(request, entity);
        orderItemlRepositoryJPA.save(entity);
        billService.updateBillStatus(request.getCreateUserId(),entity.getBillId(), null);
        return entity.getId();
    }

    private OrderItemEntity setOrderInfo(AddOrderItemRequest request, OrderItemEntity entity) {
        entity.setAmount(request.getAmount());
        entity.setReconfirms(request.getReconfirm());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setAmount(request.getAmount());
        entity.setBillId(request.getBillId());
        entity.setReconfirms(request.getReconfirm());
        entity.setPriority(request.getPriority());
        entity.setMenuId(request.getMenuId());
        entity.setCancelMessage(request.getCancelMessage());
        entity.setDescription(request.getDescription());
        return entity;
    }

    @Override
    public Object updateOrderItem(AddOrderItemRequest request) throws TeleCareException {
        Optional<OrderItemEntity> entityOptional = orderItemlRepositoryJPA.findById(request.getOrderItemId());
        if (entityOptional.isPresent()) {
            OrderItemEntity entity = entityOptional.get();
            validateAddOrderItem(request);

            entity.setStatus(request.getStatus());
            entity.setIsActive(Constants.IS_ACTIVE);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(request.getCreateUserId());
            entity = setOrderInfo(request, entity);
            orderItemlRepositoryJPA.save(entity);
            return true;
        }

        return null;
    }

    @Override
    public Object updateOrderItemStatus(Integer userId, Integer orderItemId, Integer status) throws TeleCareException {
        Optional<OrderItemEntity> entityOptional = orderItemlRepositoryJPA.findById(orderItemId);
        if (entityOptional.isPresent()) {
            OrderItemEntity entity = entityOptional.get();
            entity.setStatus(status);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);
            orderItemlRepositoryJPA.save(entity);

            return true;
        }
        return null;
    }

    @Override
    public Object deleteOrderItem(Integer orderItemId, Integer userId) throws TeleCareException {
        Optional<OrderItemEntity> entityOptional = orderItemlRepositoryJPA.findById(orderItemId);
        if (entityOptional.isPresent()) {
            OrderItemEntity entity = entityOptional.get();
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);
            orderItemlRepositoryJPA.save(entity);
            return true;
        }
        return null;
    }
}