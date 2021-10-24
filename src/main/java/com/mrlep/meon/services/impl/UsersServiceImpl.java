package com.mrlep.meon.services.impl;

import com.mrlep.meon.config.JwtTokenUtil;
import com.mrlep.meon.dto.request.LoginRequest;
import com.mrlep.meon.dto.request.RegisterRequest;
import com.mrlep.meon.dto.request.VerifyOTPRequest;
import com.mrlep.meon.dto.response.LoginResponse;
import com.mrlep.meon.repositories.tables.OTPRepositoryJPA;
import com.mrlep.meon.repositories.tables.StaffRepositoryJPA;
import com.mrlep.meon.repositories.tables.UsersRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.OTPEntity;
import com.mrlep.meon.repositories.tables.entities.StaffEntity;
import com.mrlep.meon.repositories.tables.entities.UsersEntity;
import com.mrlep.meon.services.UsersService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private StaffRepositoryJPA staffRepositoryJPA;

    @Override
    public Object login(LoginRequest request) throws TeleCareException {
        validateLogin(request);

        UsersEntity usersEntity = usersRepositoryJPA.getUserByPhoneAndPass(request.getPhone().trim(), request.getPass());
        if (usersEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.login.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        LoginResponse response = new LoginResponse();
        final UserDetails userDetails = new User(usersEntity.getPhone(), usersEntity.getPass(),
                new ArrayList<>());

        String token = jwtTokenUtil.generateToken(userDetails);
        response.setToken(token);
        response.setUser(usersEntity);
        if (request.isShopMode()) {
            List<StaffEntity> staffEntityList = staffRepositoryJPA.getAllByUserIdAndIsActive(usersEntity.getId(), Constants.IS_ACTIVE);
            response.setStaffs(staffEntityList);
        }

        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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