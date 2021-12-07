package com.mrlep.meon.services;

import com.mrlep.meon.dto.request.*;
import com.mrlep.meon.repositories.tables.entities.UsersEntity;
import com.mrlep.meon.utils.TeleCareException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface UsersService {
    Object uploadAvatar(MultipartFile file, Integer createUserId) throws TeleCareException;
    Object login(LoginRequest request) throws TeleCareException;
    Object logOut(Integer userId) throws TeleCareException;
    Object changePass(Integer userId, ChangePassRequest request) throws TeleCareException;
    Object register(RegisterRequest request) throws TeleCareException;
    Object getOTP(LoginRequest request) throws TeleCareException;
    Object verifyOTP(VerifyOTPRequest request) throws TeleCareException;
    Object getUserByPhone(String phone) throws TeleCareException;
    Object resetPassForStaff(ResetPassRequest request) throws TeleCareException;
    Object confirmResetPassForStaff(ResetPassRequest request) throws TeleCareException;
    Object getShopForResetPassForStaff(ResetPassRequest request) throws TeleCareException;
    Object getUsersForUserResetPass(ResetPassRequest request) throws TeleCareException;

    Object updateUserInfo(UsersEntity entity)  throws TeleCareException;
    Object updateUserDeviceToken(Integer userId,String deviceToken)  throws TeleCareException;
}