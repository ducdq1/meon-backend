package com.viettel.etc.controllers;

import com.viettel.etc.dto.request.LoginRequest;
import com.viettel.etc.dto.request.RegisterRequest;
import com.viettel.etc.services.UsersService;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.viettel.etc.utils.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Autogen class: Lop thong tin benh nhan covid F
 *
 * @author ToolGen
 * @date Thu Sep 23 09:18:59 ICT 2021
 */
@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    HttpServletRequest request;

    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCovidPatientFs(@AuthenticationPrincipal Authentication authentication,
                                                    CovidPatientSearchRequestDTO dataParams, @RequestHeader Optional<String> lang) {
        Object result;
        String language = lang.orElse(Constants.VIETNAM_CODE);
        try {
            result = covidPatientFService.searchCovidPatientF(authentication,dataParams,language);
        } catch (TeleCareException e) {
            LoggingUtils.logVTMException(LoggingInfo.getFromHttpRequest(request, e, "getCovidPatientFs"));
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LoggingUtils.logVTMException(LoggingInfo.getFromHttpRequest(request, e, "getCovidPatientFs"));
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }*/

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody  LoginRequest request, @RequestHeader Optional<String> lang) {
        Object result;
        String language = lang.orElse(Constants.VIETNAM_CODE);
        try {
            result = usersService.login(request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request, @RequestHeader Optional<String> lang) {
        Object result;
        try {
            result = usersService.register(request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


}