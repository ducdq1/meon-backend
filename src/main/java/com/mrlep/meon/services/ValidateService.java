package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.utils.TeleCareException;

public interface ValidateService {
    void validateBillMember(Integer billId, Integer userId) throws TeleCareException;
}