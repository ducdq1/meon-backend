package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.CreateStaffRequest;
import com.mrlep.meon.utils.TeleCareException;

public interface StaffService {
    Object getStaffDetail(Integer staffId) throws TeleCareException;
    Object getStaffsByShop(Integer shopId) throws TeleCareException;
    Object createStaff(CreateStaffRequest request) throws TeleCareException;
    Object updateStaff(CreateStaffRequest request) throws TeleCareException;
    Object deleteStaff(Integer staffId,Integer userId) throws TeleCareException;
}