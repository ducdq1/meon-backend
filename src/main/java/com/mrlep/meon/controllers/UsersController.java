package com.mrlep.meon.controllers;

import com.google.cloud.firestore.Firestore;
import com.mrlep.meon.dto.request.ChangePassRequest;
import com.mrlep.meon.dto.request.LoginRequest;
import com.mrlep.meon.dto.request.RegisterRequest;
import com.mrlep.meon.dto.request.VerifyOTPRequest;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.firebase.FirebaseFirestore;
import com.mrlep.meon.firebase.FirestoreBillManagement;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.tables.entities.UsersEntity;
import com.mrlep.meon.services.UsersService;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.TeleCareException;
import com.mrlep.meon.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    HttpServletRequest request;
    @Autowired
    BillRepository billRepository;

    @PostMapping(value = "/otp",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCovidPatientFs(@RequestBody LoginRequest request) {
        Object result;
        try {
            result = usersService.getOTP(request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/verify-otp",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> verifyOTP(@RequestBody VerifyOTPRequest request) {
        Object result;
        try {
            result = usersService.verifyOTP(request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody  LoginRequest request) {
        Object result;
        try {
            result = usersService.login(request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/logout",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> logout(@AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = usersService.logOut(userId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/change-pass",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changePasss(@AuthenticationPrincipal Authentication authentication, @RequestBody ChangePassRequest request) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = usersService.changePass(userId,request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

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

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/phone/{phoneNumber}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserByPhone(@PathVariable String phoneNumber,@AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            FnCommon.getUserIdFromToken(authentication);
            result = usersService.getUserByPhone(phoneNumber);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/info",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserInfo(@RequestBody UsersEntity entity, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userID = FnCommon.getUserIdFromToken(authentication);
            entity.setId(userID);
            result = usersService.updateUserInfo(entity);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/device-token/{deviceToken}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserDeviceToken(@PathVariable String deviceToken, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userID = FnCommon.getUserIdFromToken(authentication);
            result = usersService.updateUserDeviceToken(userID,deviceToken);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


}