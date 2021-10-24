package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.AddOrderItemRequest;
import com.mrlep.meon.repositories.OrderItemRepository;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.BillRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenusRepositoryJPA;
import com.mrlep.meon.repositories.tables.OrderItemRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderItemlServiceImpl implements OrderItemService {

    @Autowired
    private BillRepositoryJPA billRepositoryJPA;

    @Autowired
    private OrderItemRepositoryJPA orderItemRepositoryJPA;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenusRepositoryJPA menusRepositoryJPA;

    @Autowired
    private BillService billService;

    @Autowired
    private ShopRepository shopRepository;

    private void validateAddOrderItem(AddOrderItemRequest request) throws TeleCareException {
        BillEntity billEntity = billRepositoryJPA.findByIdAndIsActive(request.getBillId(), Constants.IS_ACTIVE);
        if (billEntity == null || !FnCommon.validateBillStatus(billEntity.getStatus())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if (request.getAmount() == null || request.getPrice() == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, "Invalid input param", ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

    }


    @Override
    public Object getOrderItemsByBill(Integer billId) throws TeleCareException {
        return orderItemRepository.getOrderItemOfBill(billId);
    }

    @Override
    public Object addOrderItem(AddOrderItemRequest request) throws TeleCareException {
        validateAddOrderItem(request);
        OrderItemEntity entity = new OrderItemEntity();
        entity.setCreateDate(new Date());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setStatus(Constants.ORDER_ITEM_STATUS_PROGRESS);
        entity.setPrice(request.getPrice());
        entity.setAmount(request.getAmount());
        entity.setMoney((int) (request.getPrice() * request.getAmount()));
        entity = setOrderInfo(request, entity);
        orderItemRepositoryJPA.save(entity);
        billService.updateBillStatus(request.getCreateUserId(), entity.getBillId(), null);
        menusRepositoryJPA.updateOrderNumber(request.getMenuId());

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
        entity.setMenuOptionIds(request.getMenuOptionIds());
        return entity;
    }

    @Override
    public Object updateOrderItem(AddOrderItemRequest request) throws TeleCareException {
        Optional<OrderItemEntity> entityOptional = orderItemRepositoryJPA.findById(request.getOrderItemId());
        if (entityOptional.isPresent()) {
            OrderItemEntity entity = entityOptional.get();
            if (entity.getStatus() != Constants.ORDER_ITEM_STATUS_DELIVERED) {
                validateAddOrderItem(request);

                entity.setStatus(request.getStatus());
                entity.setIsActive(Constants.IS_ACTIVE);
                entity.setUpdateDate(new Date());
                entity.setUpdateUserId(request.getCreateUserId());
                entity = setOrderInfo(request, entity);
                orderItemRepositoryJPA.save(entity);
                billService.updateBillStatus(request.getCreateUserId(), entity.getBillId(), null);
                return true;
            }
        }

        return null;
    }

    @Override
    public Object updateOrderItemStatus(Integer userId, Integer orderItemId, Integer status) throws TeleCareException {
        Optional<OrderItemEntity> entityOptional = orderItemRepositoryJPA.findById(orderItemId);
        if (entityOptional.isPresent()) {
            OrderItemEntity entity = entityOptional.get();
            if (entity.getStatus() != Constants.ORDER_ITEM_STATUS_DELIVERED) {
                entity.setStatus(status);
                entity.setUpdateDate(new Date());
                entity.setUpdateUserId(userId);
                orderItemRepositoryJPA.save(entity);
                billService.updateBillStatus(userId, entity.getBillId(), null);
                return true;
            }
        }
        return null;
    }

    @Override
    public Object deleteOrderItem(Integer orderItemId, Integer userId) throws TeleCareException {
        Optional<OrderItemEntity> entityOptional = orderItemRepositoryJPA.findById(orderItemId);
        if (entityOptional.isPresent()) {
            OrderItemEntity entity = entityOptional.get();
            if (entity.getStatus() != Constants.ORDER_ITEM_STATUS_DELIVERED) {
                entity.setIsActive(Constants.IS_NOT_ACTIVE);
                entity.setUpdateDate(new Date());
                entity.setUpdateUserId(userId);
                orderItemRepositoryJPA.save(entity);
                billService.updateBillStatus(userId, entity.getBillId(), null);
                return true;
            }

        }
        return null;
    }
}