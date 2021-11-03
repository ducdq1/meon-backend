package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.utils.TeleCareException;

import java.util.List;

public interface ValidateService {
    void validateBillMember(Integer billId, Integer userId,Integer staffId) throws TeleCareException;
    void validateBillStatusPermission(List<String> permission, Integer billStatus) throws TeleCareException;
    void validateOrderStatusPermission(List<String> permission, Integer orderStatus) throws TeleCareException;
}