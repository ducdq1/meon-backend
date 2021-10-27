package com.mrlep.meon.services.impl;

import com.mrlep.meon.dto.request.CreateStaffRequest;
import com.mrlep.meon.repositories.StaffRepository;
import com.mrlep.meon.repositories.tables.StaffRepositoryJPA;
import com.mrlep.meon.repositories.tables.UsersRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.StaffEntity;
import com.mrlep.meon.services.StaffService;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.ErrorApp;
import com.mrlep.meon.utils.MessagesUtils;
import com.mrlep.meon.utils.TeleCareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepositoryJPA staffRepositoryJPA;

    @Autowired
    private UsersRepositoryJPA usersRepositoryJPA;

    @Autowired
    private StaffRepository staffRepository;
    @Override
    public Object getStaffDetail(Integer staffId) throws TeleCareException {
        return  staffRepository.getDetailStaff(staffId);
    }

    @Override
    public Object getStaffsByShop(Integer shopId) throws TeleCareException {
        return staffRepository.getStaffsByShop(shopId);
    }


    @Override
    public Object createStaff(CreateStaffRequest request) throws TeleCareException {
        StaffEntity staffEntityExist = staffRepositoryJPA.getByUserIdAndShopIdAndIsActive(request.getUserId(), request.getShopId(), Constants.IS_ACTIVE);
        if (staffEntityExist != null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.staff.exist.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        StaffEntity staffEntity = new StaffEntity();
        setStaffInfo(request, staffEntity);
        staffEntity.setCreateDate(new Date());
        staffEntity.setCreateUserId(request.getCreateUserId());
        staffRepositoryJPA.save(staffEntity);

        return staffEntity;
    }

    private StaffEntity setStaffInfo(CreateStaffRequest request, StaffEntity staffEntity) {

        staffEntity.setShopId(request.getShopId());
        staffEntity.setUserId(request.getUserId());
        staffEntity.setCertification(request.getCertification());
        staffEntity.setIdentityNumber(request.getIdentityNumber());
        staffEntity.setSalary(request.getSalary());
        staffEntity.setPermission(request.getPermission());
        staffEntity.setIsActive(Constants.IS_ACTIVE);
        staffEntity.setStatus(request.getStatus());
        return staffEntity;
    }

    @Override
    public Object updateStaff(CreateStaffRequest request) throws TeleCareException {
        StaffEntity staffEntity = staffRepositoryJPA.getByIdAndIsActive(request.getStaffId(), Constants.IS_ACTIVE);
        if (staffEntity == null) {
            return null;
        }

        setStaffInfo(request, staffEntity);
        staffEntity.setUpdateDate(new Date());
        staffEntity.setUpdateUserId(request.getCreateUserId());
        staffRepositoryJPA.save(staffEntity);

        return true;
    }

    @Override
    public Object deleteStaff(Integer staffId, Integer userId) throws TeleCareException {
        StaffEntity staffEntity = staffRepositoryJPA.getByIdAndIsActive(staffId, Constants.IS_ACTIVE);
        if (staffEntity == null) {
            return null;
        }

        staffEntity.setUpdateDate(new Date());
        staffEntity.setUpdateUserId(userId);
        staffEntity.setIsActive(Constants.IS_NOT_ACTIVE);
        staffRepositoryJPA.save(staffEntity);

        return true;
    }
}
