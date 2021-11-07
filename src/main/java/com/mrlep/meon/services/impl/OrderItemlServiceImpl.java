package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.AddOrderItemRequest;
import com.mrlep.meon.dto.request.UpdateStatusRequest;
import com.mrlep.meon.firebase.FirestoreBillManagement;
import com.mrlep.meon.repositories.OrderItemRepository;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.BillRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenusOptionRepositoryJPA;
import com.mrlep.meon.repositories.tables.MenusRepositoryJPA;
import com.mrlep.meon.repositories.tables.OrderItemRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.BillEntity;
import com.mrlep.meon.repositories.tables.entities.MenuEntity;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;
import com.mrlep.meon.repositories.tables.entities.OrderItemEntity;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.services.ValidateService;
import com.mrlep.meon.utils.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    private FirestoreBillManagement firestoreBillManagement;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private MenusOptionRepositoryJPA menusOptionRepositoryJPA;

    @Autowired
    private ValidateService validateService;

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
        validateService.validateBillMember(request.getBillId(), request.getCreateUserId(), request.getStaffId());

        OrderItemEntity entity = new OrderItemEntity();
        entity.setCreateDate(new Date());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setStatus(Constants.ORDER_ITEM_STATUS_PROGRESS);
        entity.setAmount(request.getAmount());
        entity = setOrderInfo(request, entity);

        orderItemRepositoryJPA.save(entity);
        billService.updateBillInfo(request.getCreateUserId(), entity.getBillId());
        firestoreBillManagement.updateOrderItem(entity.getId());

        menusRepositoryJPA.updateOrderNumber(request.getMenuId());

        return entity.getId();
    }

    private OrderItemEntity setOrderInfo(AddOrderItemRequest request, OrderItemEntity entity) throws TeleCareException {
        MenuEntity menuEntity = menusRepositoryJPA.getByIdAndIsActive(request.getMenuId(), Constants.IS_ACTIVE);
        if (menuEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, "Invalid input param menu Id", ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        entity.setUnit(menuEntity.getUnit());
        entity.setMenuName(menuEntity.getName());
        entity.setAmount(request.getAmount());
        entity.setReconfirms(request.getReconfirm());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setBillId(request.getBillId());
        entity.setReconfirms(request.getReconfirm());
        entity.setPriority(request.getPriority());
        entity.setMenuId(request.getMenuId());
        entity.setCancelMessage(request.getCancelMessage());
        entity.setDescription(request.getDescription());
        entity.setMenuOptionIds(request.getMenuOptionIds());
        entity.setDiscountType(menuEntity.getDiscountType());
        entity.setDiscountDescription(menuEntity.getDiscountDescription());
        entity.setDiscountValue(menuEntity.getDiscountValue());

        Integer priceMenu = getPriceOfMenu(menuEntity, request.getMenuOptionIds());
        entity.setPrice(priceMenu);

        int totalMoney = (int) (priceMenu * request.getAmount());
        if (menuEntity.getDiscountValue() != null) {
            if (entity.getDiscountType() == Constants.DISCOUNT_TYPE_MONEY) {
                totalMoney = (int) (totalMoney - menuEntity.getDiscountValue());
            } else {
                int discountMoney = (int) (priceMenu * (menuEntity.getDiscountValue() / 100) * request.getAmount());
                totalMoney = totalMoney - discountMoney;
            }
        }

        entity.setMoney(totalMoney);

        return entity;
    }

    private Integer getPriceOfMenu(MenuEntity menuEntity, String menuOptionIds) {
        Integer price = menuEntity.getPrice();
        if (!StringUtils.isNullOrEmpty(menuOptionIds)) {

            String[] ids = menuOptionIds.split(";");
            for (String id : ids) {
                try {
                    MenuOptionEntity menuOptionEntity = menusOptionRepositoryJPA.getByIdAndIsActive(Integer.valueOf(id), Constants.IS_ACTIVE);
                    if (menuOptionEntity != null && menuOptionEntity.getPrice() != null) {
                        price += menuOptionEntity.getPrice();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return price;
    }

    @Override
    public Object updateOrderItem(AddOrderItemRequest request) throws TeleCareException {
        validateService.validateBillMember(request.getBillId(), request.getCreateUserId(), request.getStaffId());

        OrderItemEntity entity = orderItemRepositoryJPA.findByIdIsAndIsActive(request.getOrderItemId(), Constants.IS_ACTIVE);
        if (entity != null) {
            if (entity.getStatus() != Constants.ORDER_ITEM_STATUS_DELIVERED) {
                validateAddOrderItem(request);

                entity.setStatus(request.getStatus());
                entity.setIsActive(Constants.IS_ACTIVE);
                entity.setUpdateDate(new Date());
                entity.setUpdateUserId(request.getCreateUserId());
                entity = setOrderInfo(request, entity);

                orderItemRepositoryJPA.save(entity);
                billService.updateBillInfo(request.getCreateUserId(), entity.getBillId());
                firestoreBillManagement.updateOrderItem(entity.getId());
                return true;
            } else {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.order.item.status.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }
        } else {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.order.item.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

    }

    @Override
    public Object updateOrderItemStatus(Integer userId, Integer orderItemId, UpdateStatusRequest request) throws TeleCareException {
        OrderItemEntity entity = orderItemRepositoryJPA.findByIdIsAndIsActive(orderItemId, Constants.IS_ACTIVE);

        if (entity != null) {
            validateService.validateBillMember(entity.getBillId(), userId, request.getStaffId());

            if (request.getStatus() == Constants.ORDER_ITEM_STATUS_CANCEL && (entity.getStatus() != Constants.ORDER_ITEM_STATUS_PROGRESS
                    && entity.getStatus() !=  Constants.ORDER_ITEM_STATUS_RECONFIRM)) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.order.item.status.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }

            if (entity.getStatus() != Constants.ORDER_ITEM_STATUS_DELIVERED) {
                entity.setStatus(request.getStatus());
                entity.setUpdateDate(new Date());
                entity.setUpdateUserId(userId);
                String cancelMessage = request.getCancelMessage();
                String reconfirmMessage = request.getReconfirmMessage();
                if (cancelMessage != null) {
                    entity.setCancelMessage(cancelMessage);
                }
                if (reconfirmMessage != null) {
                    entity.setReconfirms(reconfirmMessage);
                }

                orderItemRepositoryJPA.save(entity);
                billService.updateBillInfo(userId, entity.getBillId());
                firestoreBillManagement.updateOrderItem(entity.getId());
                return true;
            } else {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.order.item.status.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }
        } else {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.order.item.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }

    @Override
    public Object deleteOrderItem(Integer orderItemId, Integer userId) throws TeleCareException {
        OrderItemEntity entity = orderItemRepositoryJPA.findByIdIsAndIsActive(orderItemId, Constants.IS_ACTIVE);

        if (entity != null) {
            validateService.validateBillMember(entity.getBillId(), userId, null);
            if (entity.getStatus() == Constants.ORDER_ITEM_STATUS_PROGRESS) {
                entity.setIsActive(Constants.IS_NOT_ACTIVE);
                entity.setUpdateDate(new Date());
                entity.setUpdateUserId(userId);
                orderItemRepositoryJPA.save(entity);
                firestoreBillManagement.deleteBillOrderItem(entity.getBillId(), orderItemId);
                billService.updateBillInfo(userId, entity.getBillId());
                return true;
            } else {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.order.item.status.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }
        } else {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.order.item.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }
}