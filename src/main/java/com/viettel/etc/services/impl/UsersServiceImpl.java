package com.viettel.etc.services.impl;

import com.viettel.etc.dto.request.LoginRequest;
import com.viettel.etc.dto.request.RegisterRequest;
import com.viettel.etc.dto.request.VerifyOTPRequest;
import com.viettel.etc.dto.response.LoginResponse;
import com.viettel.etc.repositories.tables.OTPRepositoryJPA;
import com.viettel.etc.repositories.tables.UsersRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.OTPEntity;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
import com.viettel.etc.services.UsersService;
import com.viettel.etc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * Autogen class: Lop danh muc chi so sp02
 *
 * @author ToolGen
 * @date Thu Sep 23 08:29:44 ICT 2021
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepositoryJPA usersRepositoryJPA;

    @Autowired
    private OTPRepositoryJPA otpRepositoryJPA;

    @Override
    public Object login(LoginRequest request) throws TeleCareException {
        validateLogin(request);

        UsersEntity usersEntity = usersRepositoryJPA.getUserByPhoneAndPass(request.getPhone().trim(), request.getPass());
        if(usersEntity == null){
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.login.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
        LoginResponse response = new LoginResponse();
        response.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
        response.setUser(usersEntity);

        return response;
    }

    @Override
    public Object register(RegisterRequest request) throws TeleCareException {
        validateRegister(request);

        UsersEntity usersEntity = usersRepositoryJPA.getUserByPhone(request.getPhone().trim());
        if (usersEntity != null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.phone.exists"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        usersEntity = new UsersEntity();
        usersEntity.setPhone(request.getPhone());
        usersEntity.setPass(request.getPass());
        usersEntity.setIsActive(Constants.IS_ACTIVE);
        usersEntity.setFullName(request.getFullName());
        usersRepositoryJPA.save(usersEntity);
        return usersEntity;
    }

    @Override
    public Object getOTP(LoginRequest request) throws TeleCareException {
        String phone = request.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.phone.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        OTPEntity entity = otpRepositoryJPA.getByPhoneAndIsActive(phone, Constants.IS_ACTIVE);
        Random rnd = new Random();
        int otpNumber = rnd.nextInt(999999);

        if (entity == null) {
            entity = new OTPEntity();
        }

        entity.setOtp(String.valueOf(otpNumber));
        entity.setCreateDate(new Date());
        entity.setPhone(phone);
        entity.setIsActive(Constants.IS_ACTIVE);

        otpRepositoryJPA.save(entity);

        return entity;
    }

    @Override
    public Object verifyOTP(VerifyOTPRequest request) throws TeleCareException {
        OTPEntity entity = otpRepositoryJPA.getByPhoneAndOtpAndIsActive(request.getPhone(), request.getOtp(), Constants.IS_ACTIVE);
        if (entity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.otp.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if ((new Date().getTime() - entity.getCreateDate().getTime()) > 60000 * 5) {//5 phut
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.otp.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        entity.setIsActive(Constants.IS_NOT_ACTIVE);
        otpRepositoryJPA.save(entity);
        return entity;
    }

    private void validateLogin(LoginRequest request) throws TeleCareException {
        if (StringUtils.isNullOrEmpty(request.getPhone())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.phone.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if (StringUtils.isNullOrEmpty(request.getPass())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.pass.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }

    private void validateRegister(RegisterRequest request) throws TeleCareException {
        if (StringUtils.isNullOrEmpty(request.getPhone())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.phone.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if (StringUtils.isNullOrEmpty(request.getPass())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.pass.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }


        if (StringUtils.isNullOrEmpty(request.getFullName())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.fullname.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }
}