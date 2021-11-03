package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
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
import java.util.Date;
import java.util.List;

@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private BillMembersRepositoryJPA billMembersRepositoryJPA;
    @Autowired
    private BillRepositoryJPA billRepositoryJPA;

    @Autowired
    private StaffRepositoryJPA staffRepositoryJPA;

    @Override
    public void validateBillMember(Integer billId, Integer userId, Integer staffId) throws TeleCareException {
        BillEntity billEntity = billRepositoryJPA.findByIdAndIsActive(billId, Constants.IS_ACTIVE);
        if (billEntity == null || billEntity.getStatus() == Constants.BILL_STATUS_DONE
                || billEntity.getStatus() == Constants.BILL_STATUS_CANCEL) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.status"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if (staffId != null) {
            StaffEntity staffEntity = staffRepositoryJPA.getByIdAndIsActive(staffId, Constants.IS_ACTIVE);
            if (staffEntity == null || staffEntity.getShopId() != billEntity.getShopId()) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.member.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }

        } else {
            BillMembersEntity billMembersEntity = billMembersRepositoryJPA.findByUserIdAndBillIdAndIsActive(userId, billId, Constants.IS_ACTIVE);
            if (billMembersEntity == null || Constants.IS_ACTIVE.equals(billMembersEntity.getIsBlackList())) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.member.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }
        }
    }

    @Override
    public void validateBillStatusPermission(List<String> permissions, Integer billStatus) throws TeleCareException {
        if (permissions == null || permissions.isEmpty()) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.member.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        for (String permission : permissions) {
            if (!permission.equals(Constants.PERMISSION_STAFF) &&
                    !permission.equals(Constants.PERMISSION_MANAGER) &&
                    !permission.equals(Constants.PERMISSION_ACCOUNTANT)) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.member.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }
        }

    }

    @Override
    public void validateOrderStatusPermission(List<String> permissions, Integer orderStatus) throws TeleCareException {
        if (permissions == null || permissions.isEmpty()) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.member.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        for (String permission : permissions) {
            if (!permission.equals(Constants.PERMISSION_STAFF) &&
                    !permission.equals(Constants.PERMISSION_MANAGER) &&
                    !permission.equals(Constants.PERMISSION_ACCOUNTANT)) {
                throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.bill.member.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
            }
        }

    }
}