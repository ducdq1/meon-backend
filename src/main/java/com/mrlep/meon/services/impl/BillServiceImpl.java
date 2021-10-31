package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.request.UpdateBillStatusRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.dto.response.SearchBillResponse;
import com.mrlep.meon.firebase.FirestoreBillManagement;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.*;
import com.mrlep.meon.repositories.tables.entities.*;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.OrderItemService;
import com.mrlep.meon.services.ShopTableService;
import com.mrlep.meon.services.ValidateService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private ShopTableRepositoryJPA shopTableRepositoryJPA;

    @Autowired
    private ShopRepositoryJPA shopRepositoryJPA;

    @Autowired
    private OrderItemService orderItemlService;

    @Autowired
    private MenusOptionRepositoryJPA menusOptionRepositoryJPA;

    @Autowired
    private BillTablesRepositoryJPA billTablesRepositoryJPA;

    @Autowired
    private UsersRepositoryJPA usersRepositoryJPA;


    @Autowired
    private BillMembersRepositoryJPA billMembersRepositoryJPA;

    @Autowired
    private FirestoreBillManagement firestoreBillManagement;


    @Autowired
    private ValidateService validateService;

    @Autowired
    private ShopRepository shopRepository;

    private void validateCreateBill(CreateBillRequest request) throws TeleCareException {
        if (request.getTableIds() != null) {
            for (Integer tableId : request.getTableIds()) {
                Integer countTableAndBill = billRepositoryJPA.checkExistBillAndTable(tableId);
                if (countTableAndBill != null && countTableAndBill > 0) {
                    throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.table.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
                }
            }
        }

    }

    @Override
    public Object getBillByTable(Integer shopId, Integer tableId) throws TeleCareException {
        return billRepository.getBillByTable(shopId,tableId);
    }

    @Override
    public Object getBillActiveByUser(Integer userId) throws TeleCareException {
        DetailBillResponse detailBillResponse = billRepository.getBillActiveByUser(userId);
        if (detailBillResponse == null) {
            return null;
        }

        List<OrderItem> orderItemEntitiesList = (List<OrderItem>) orderItemlService.getOrderItemsByBill(detailBillResponse.getBillId());
        if (orderItemEntitiesList != null) {
            getMenuOptions(orderItemEntitiesList);
        }

        detailBillResponse.setOrderItems(orderItemEntitiesList);
        detailBillResponse.setMembers(billRepository.getBillMembers(detailBillResponse.getBillId()));
        detailBillResponse.setTables(billRepository.getBillTables(detailBillResponse.getBillId()));

        return detailBillResponse;

    }

    private void getMenuOptions(List<OrderItem> orderItemEntitiesList) {
        for (OrderItem orderItem : orderItemEntitiesList) {

            List<MenuOptionEntity> menuOptionEntities = new ArrayList<>();
            String menuOptionIds = orderItem.getMenuOptionIds();
            if (!StringUtils.isNullOrEmpty(menuOptionIds)) {
                String[] ids = menuOptionIds.split(";");
                for (String id : ids) {
                    if (FnCommon.getIntegerFromString(id) != null) {
                        MenuOptionEntity menuOptionEntity = menusOptionRepositoryJPA.getByIdAndIsActive(FnCommon.getIntegerFromString(id), Constants.IS_ACTIVE);
                        if (menuOptionEntity != null) {
                            menuOptionEntities.add(menuOptionEntity);
                        }
                    }
                }
                orderItem.setMenuOptions(menuOptionEntities);
            }
        }
    }

    @Override
    public Object getDetailBills(Integer billId) throws TeleCareException {
        DetailBillResponse detailBillResponse = billRepository.getDetailBill(billId);
        if (detailBillResponse == null) {
            return null;
        }

        List<OrderItem> orderItemEntitiesList = (List<OrderItem>) orderItemlService.getOrderItemsByBill(billId);
        if (orderItemEntitiesList != null) {
            getMenuOptions(orderItemEntitiesList);
        }

        detailBillResponse.setOrderItems(orderItemEntitiesList);
        detailBillResponse.setMembers(billRepository.getBillMembers(billId));
        detailBillResponse.setTables(billRepository.getBillTables(billId));

        return detailBillResponse;
    }

    @Override
    public Object getBillsByShop(Integer shopId, Integer offset, Integer pageSize) throws TeleCareException {
        return billRepository.getBillOfShop(shopId, offset, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object createBill(CreateBillRequest request) throws TeleCareException {
        validateCreateBill(request);

        if (request.getIsCreateByStaff() != 1 && getBillActiveByUser(request.getCreateUserId()) != null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.user.bill.exists.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        BillEntity entity = new BillEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setShopId(request.getShopId());
        entity.setCreateUserId(request.getCreateUserId());
        entity.setIsActive(Constants.IS_ACTIVE);
        entity.setStatus(Constants.TABLE_STATUS_READY);
        entity.setCreateDate(new Date());
        entity.setStatus(Constants.BILL_STATUS_PROGRESS);
        entity.setReconfirmMessage(request.getReconfirmMessage());
        entity.setIsCreateByStaff(request.getIsCreateByStaff());

        if (request.getIsCreateByStaff() != null && request.getIsCreateByStaff() == 1) {
            entity.setStatus(Constants.BILL_STATUS_ACCEPTED);
        }

        billRepositoryJPA.save(entity);

        if (request.getTableIds() != null) {
            for (Integer tableId : request.getTableIds()) {
                addTableBill(entity.getId(), request.getCreateUserId(), Arrays.asList(new Integer[]{tableId}));
                shopTableService.updateShopTableStatus(request.getCreateUserId(), tableId, Constants.TABLE_STATUS_IN_USE);
            }
        }

        addBillMembers(entity.getId(), request.getCreateUserId());

        firestoreBillManagement.updateBill(entity.getId(), entity);
        return entity.getId();
    }

    private void addBillMembers(Integer billId, Integer userId) {
        //Luu bill members
        BillMembersEntity billMembersEntity = new BillMembersEntity();
        billMembersEntity.setBillId(billId);
        billMembersEntity.setUserId(userId);
        billMembersEntity.setCreateDate(new Date());
        billMembersEntity.setCreateUserId(userId);
        billMembersEntity.setIsActive(Constants.IS_ACTIVE);
        billMembersRepositoryJPA.save(billMembersEntity);
    }

    private void addBillTables(Integer billId, Integer userId, Integer tableId) throws TeleCareException {
        //luu ban

        Integer countTableAndBill = billRepositoryJPA.checkExistBillAndTable(tableId);
        if (countTableAndBill != null && countTableAndBill > 0) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.table.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        ShopTableEntity shopTableEntity = shopTableRepositoryJPA.getByIdAndIsActive(tableId, Constants.IS_ACTIVE);
        if (shopTableEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, " Invalid table", ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        BillTablesEntity billTablesEntity = new BillTablesEntity();

        billTablesEntity.setBillId(billId);
        billTablesEntity.setTableName(shopTableEntity.getName());
        billTablesEntity.setCreateDate(new Date());
        billTablesEntity.setIsActive(Constants.IS_ACTIVE);
        billTablesEntity.setCreateUserId(userId);
        billTablesEntity.setTableId(tableId);
        billTablesRepositoryJPA.save(billTablesEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateBill(CreateBillRequest request) throws TeleCareException {
        BillEntity entity = billRepositoryJPA.findByIdAndIsActive(request.getBillId(), Constants.IS_ACTIVE);
        if (entity != null) {
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
            entity.setReconfirmMessage(request.getReconfirmMessage());
            entity.setTotalMoney(request.getTotalMoney());
            entity.setCancelMessage(request.getCancelMessage());
            entity.setIsCreateByStaff(request.getIsCreateByStaff());
            billRepositoryJPA.save(entity);

            if (request.getTableIds() != null) {
                for (Integer tableId : request.getTableIds()) {
                    BillTablesEntity billTablesEntity = billTablesRepositoryJPA.findByTableIdAndBillIdAndIsActive(tableId, entity.getId(), Constants.IS_ACTIVE);
                    if (billTablesEntity == null) {
                        addTableBill(entity.getId(), request.getCreateUserId(), Arrays.asList(new Integer[]{tableId}));
                    }
                }
            }

            if (request.getDeletedTableIds() != null) {
                for (Integer tableId : request.getDeletedTableIds()) {
                    BillTablesEntity billTablesEntity = billTablesRepositoryJPA.findByTableIdAndBillIdAndIsActive(tableId, entity.getId(), Constants.IS_ACTIVE);
                    if (billTablesEntity != null) {
                        billTablesEntity.setIsActive(Constants.IS_NOT_ACTIVE);
                        billTablesEntity.setUpdateDate(new Date());
                        billTablesEntity.setUpdateUserId(request.getCreateUserId());
                        billTablesRepositoryJPA.save(billTablesEntity);
                    }
                }
            }

            firestoreBillManagement.updateBill(entity.getId(), entity);
            return true;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateBillStatus(Integer userId, Integer billId, List<String> permissions, UpdateBillStatusRequest request) throws TeleCareException {
        BillEntity entity = billRepositoryJPA.findByIdAndIsActive(billId, Constants.IS_ACTIVE);
        if (entity != null) {

            if (entity.getStatus() == Constants.BILL_STATUS_DONE) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.status"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }


            Integer status = request.getStatus();
            String cancelMessage = request.getCancelMessage();
            String reconfirmMessage = request.getReconfirmMessage();

            validateService.validateBillStatusPermission(permissions,status);

            if(cancelMessage !=null){
                entity.setCancelMessage(cancelMessage);
            }

            if(reconfirmMessage !=null){
                entity.setReconfirmMessage(reconfirmMessage);
            }

            entity.setStatus(status);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);

            entity.setTotalMoney(getTotalMoney(entity.getId()));
            billRepositoryJPA.save(entity);

            if (status != null && (status.intValue() == Constants.BILL_STATUS_DONE
                    || status.intValue() == Constants.BILL_STATUS_CANCEL)) {
                //cap nhat lai trang thai ban OK
                List<BillTablesEntity> tablesEntities = billTablesRepositoryJPA.findAllByBillIdAndIsActive(entity.getId(), Constants.IS_ACTIVE);
                for (BillTablesEntity billTable : tablesEntities) {
                    shopTableService.updateShopTableStatus(userId, billTable.getTableId(), Constants.TABLE_STATUS_READY);
                }
            }

            firestoreBillManagement.updateBill(entity.getId(), entity);
            firestoreBillManagement.sendBillStatusNotification(entity);
            return true;
        }
        return null;
    }

    public void updateBillInfo(Integer userId, Integer billId) throws TeleCareException {
        BillEntity entity = billRepositoryJPA.findByIdAndIsActive(billId, Constants.IS_ACTIVE);
        if (entity != null) {

            if (entity.getStatus() == Constants.BILL_STATUS_DONE) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.status"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }

            entity.setTotalMoney(getTotalMoney(entity.getId()));
            billRepositoryJPA.save(entity);
            firestoreBillManagement.updateBill(entity.getId(), entity);
        }
    }


    private Integer getTotalMoney(Integer billId) throws TeleCareException {
        List<OrderItem> orderItemEntities = (List<OrderItem>) orderItemService.getOrderItemsByBill(billId);
        Integer totalMoney = 0;
        for (OrderItem orderItemEntity : orderItemEntities) {
            if (FnCommon.validateOrderItemStatus(orderItemEntity.getStatus())) {
                totalMoney += orderItemEntity.getMoney() == null ? 0 : orderItemEntity.getMoney();
            }
        }
        return totalMoney;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object deleteBill(Integer billId, Integer userId) throws TeleCareException {
        BillEntity entity = billRepositoryJPA.findByIdAndIsActive(billId, Constants.IS_ACTIVE);
        if (entity != null && entity.getStatus() == Constants.BILL_STATUS_PROGRESS) {
            entity.setIsActive(Constants.IS_NOT_ACTIVE);
            entity.setUpdateDate(new Date());
            entity.setUpdateUserId(userId);
            billRepositoryJPA.save(entity);
            firestoreBillManagement.deleteBill(entity.getId());
            return true;
        }
        return null;
    }

    @Override
    public Object joinBill(Integer billId, Integer userId) throws TeleCareException {
        Integer countExist = billRepositoryJPA.checkExistBillOfUser(userId);
        if (countExist > 0) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.user.bill.member.exists.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        BillEntity entity = billRepositoryJPA.findByIdAndIsActive(billId, Constants.IS_ACTIVE);
        if (entity != null && FnCommon.validateBillStatus(entity.getStatus())) {
            addBillMembers(billId, userId);
            return true;
        } else {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }

    @Override
    @Transactional
    public Object addTableBill(Integer billId, Integer userId, List<Integer> tableIds) throws TeleCareException {
        BillEntity entity = billRepositoryJPA.findByIdAndIsActive(billId, Constants.IS_ACTIVE);
        if (entity != null && FnCommon.validateBillStatus(entity.getStatus())) {
            for (Integer tableId : tableIds) {
                addBillTables(billId, userId, tableId);
                shopTableService.updateShopTableStatus(userId, tableId, Constants.TABLE_STATUS_IN_USE);
            }

            return true;
        }
        return null;
    }

    @Override
    public SearchBillResponse searchBillUser(SearchBillRequest request) throws TeleCareException {
        return billRepository.getBillOfUser(request);
    }
}