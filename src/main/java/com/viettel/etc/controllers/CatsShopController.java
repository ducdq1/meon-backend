package com.viettel.etc.controllers;

import com.viettel.etc.dto.request.LoginRequest;
import com.viettel.etc.dto.request.RegisterRequest;
import com.viettel.etc.dto.request.VerifyOTPRequest;
import com.viettel.etc.repositories.tables.CatsShopRepositoryJPA;
import com.viettel.etc.services.UsersService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Autogen class: Lop thong tin benh nhan covid F
 *
 * @author ToolGen
 * @date Thu Sep 23 09:18:59 ICT 2021
 */
@RestController
@RequestMapping("category")
public class CatsShopController {

    @Autowired
    private CatsShopRepositoryJPA catsShopRepositoryJPA;

    @Autowired
    HttpServletRequest request;

    @GetMapping(value = "/shop",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCatsShop() {
        Object result;
        try {
            result = catsShopRepositoryJPA.getAllByIsActive(Constants.IS_ACTIVE);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


}