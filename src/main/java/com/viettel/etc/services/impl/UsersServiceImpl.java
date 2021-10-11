package com.viettel.etc.services.impl;

import com.viettel.etc.dto.request.LoginRequest;
import com.viettel.etc.dto.request.RegisterRequest;
import com.viettel.etc.repositories.tables.UsersRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
import com.viettel.etc.services.UsersService;
import com.viettel.etc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public Object login(LoginRequest request) throws TeleCareException {
        validateLogin(request);

        UsersEntity usersEntity = usersRepositoryJPA.getUserByPhoneAndPass(request.getPhone().trim(),request.getPass());
        return usersEntity;
    }

    @Override
    public Object register(RegisterRequest request) throws TeleCareException {
        validateRegister(request);

        UsersEntity usersEntity = usersRepositoryJPA.getUserByPhone(request.getPhone().trim());
        if(usersEntity != null){
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

    private void validateLogin(LoginRequest request)throws TeleCareException{
        if (StringUtils.isNullOrEmpty(request.getPhone())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.phone.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }

        if (StringUtils.isNullOrEmpty(request.getPass())) {
            throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("message.error.pass.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
        }
    }

    private void validateRegister(RegisterRequest request)throws TeleCareException{
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