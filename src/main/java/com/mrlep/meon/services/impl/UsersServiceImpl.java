package com.mrlep.meon.services.impl;

import com.mrlep.meon.config.JwtTokenUtil;
import com.mrlep.meon.dto.object.ShopItem;
import com.mrlep.meon.dto.object.StaffItem;
import com.mrlep.meon.dto.request.*;
import com.mrlep.meon.dto.response.LoginResponse;
import com.mrlep.meon.repositories.ShopRepository;
import com.mrlep.meon.repositories.tables.OTPRepositoryJPA;
import com.mrlep.meon.repositories.tables.ShopRepositoryJPA;
import com.mrlep.meon.repositories.tables.StaffRepositoryJPA;
import com.mrlep.meon.repositories.tables.UsersRepositoryJPA;
import com.mrlep.meon.repositories.tables.entities.OTPEntity;
import com.mrlep.meon.repositories.tables.entities.ShopEntity;
import com.mrlep.meon.repositories.tables.entities.StaffEntity;
import com.mrlep.meon.repositories.tables.entities.UsersEntity;
import com.mrlep.meon.services.UsersService;
import com.mrlep.meon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopRepositoryJPA shopRepositoryJPA;


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
            List<StaffItem> staffEntityList = shopRepository.getShopOfsStaff(usersEntity.getId());
            response.setStaffs(staffEntityList);
        }

        return response;
    }

    @Override
    public Object logOut(Integer userId) throws TeleCareException {
        UsersEntity usersEntity = usersRepositoryJPA.getByIdAndIsActive(userId, Constants.IS_ACTIVE);
        if (usersEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.login.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
        usersEntity.setDeviceToken(null);
        usersRepositoryJPA.save(usersEntity);
        return true;
    }

    @Override
    public Object changePass(Integer userId, ChangePassRequest request) throws TeleCareException {
        UsersEntity usersEntity = usersRepositoryJPA.getByIdAndIsActive(userId, Constants.IS_ACTIVE);
        if (usersEntity == null || !usersEntity.getPass().equals(request.getPass())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.login.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        usersEntity.setPass(request.getNewPass());
        usersRepositoryJPA.save(usersEntity);
        return true;
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

    @Override
    public Object getUserByPhone(String phone) throws TeleCareException {
        UsersEntity usersEntity = usersRepositoryJPA.getByPhoneAndIsActive(phone, Constants.IS_ACTIVE);
        if (usersEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.account.notexist"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
        return usersEntity;
    }

    @Override
    public Object resetPassForStaff(ResetPassRequest request) throws TeleCareException {
        UsersEntity usersEntity = usersRepositoryJPA.getByPhoneAndIsActive(request.getPhone(), Constants.IS_ACTIVE);
        if (usersEntity == null || StringUtils.isNullOrEmpty(request.getPass())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.account.notexist"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
        usersEntity.setPass(request.getPass());
        usersRepositoryJPA.save(usersEntity);

        return true;
    }

    @Override
    public Object confirmResetPassForStaff(ResetPassRequest request) throws TeleCareException {
        UsersEntity usersEntity = usersRepositoryJPA.getByPhoneAndIsActive(request.getPhone(), Constants.IS_ACTIVE);
        if (usersEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.account.notexist"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        StaffEntity entity = staffRepositoryJPA.getByUserIdAndShopIdAndIsActive(usersEntity.getId(), request.getShopId(), Constants.IS_ACTIVE);
        if (entity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.account.notexist"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        return usersEntity.getId();
    }

    @Override
    public Object getShopForResetPassForStaff(ResetPassRequest request) throws TeleCareException {
        UsersEntity usersEntity = usersRepositoryJPA.getByPhoneAndIsActive(request.getPhone(), Constants.IS_ACTIVE);
        if (usersEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.account.notexist"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
        List<ShopItem> randomShops = new ArrayList<>();
        randomShops.addAll(shopRepository.getShopByStaff(usersEntity.getId()));
        randomShops.addAll(shopRepository.getShopNotByStaff(usersEntity.getId()));
        Collections.shuffle(randomShops);
        return randomShops;
    }

    @Override
    public Object updateUserInfo(UsersEntity entity) throws TeleCareException {

        UsersEntity usersEntity = usersRepositoryJPA.getByIdAndIsActive(entity.getId(), Constants.IS_ACTIVE);
        if (usersEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.phone.exists"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if (StringUtils.isNullOrEmpty(entity.getFullName())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.fullname.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        usersEntity.setIsActive(Constants.IS_ACTIVE);
        usersEntity.setFullName(entity.getFullName());
        usersEntity.setAvatar(entity.getAvatar());
        usersEntity.setIsReceiveNotification(entity.getIsReceiveNotification());
        usersRepositoryJPA.save(usersEntity);
        return usersEntity;
    }

    @Override
    public Object updateUserDeviceToken(Integer userId, String deviceToken) throws TeleCareException {
        UsersEntity usersEntity = usersRepositoryJPA.getByIdAndIsActive(userId, Constants.IS_ACTIVE);
        if (usersEntity == null) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.phone.exists"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        usersEntity.setDeviceToken(deviceToken);
        usersRepositoryJPA.save(usersEntity);
        return true;
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