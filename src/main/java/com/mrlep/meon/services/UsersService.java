package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.LoginRequest;
import com.mrlep.meon.dto.request.RegisterRequest;
import com.mrlep.meon.dto.request.VerifyOTPRequest;
import com.mrlep.meon.repositories.tables.entities.UsersEntity;
import com.mrlep.meon.utils.TeleCareException;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface UsersService {
    Object login(LoginRequest request) throws TeleCareException;
    Object register(RegisterRequest request) throws TeleCareException;
    Object getOTP(LoginRequest request) throws TeleCareException;
    Object verifyOTP(VerifyOTPRequest request) throws TeleCareException;
    Object getUserByPhone(String phone) throws TeleCareException;

    Object updateUserInfo(UsersEntity entity)  throws TeleCareException;
    Object updateUserDeviceToken(Integer userId,String deviceToken)  throws TeleCareException;
}