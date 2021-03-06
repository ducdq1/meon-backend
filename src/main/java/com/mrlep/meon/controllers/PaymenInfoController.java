package com.mrlep.meon.controllers;

import com.google.gson.Gson;
import com.mrlep.meon.config.ConfigValue;
import com.mrlep.meon.dto.request.CreateMediaRequest;
import com.mrlep.meon.repositories.tables.entities.PaymentInfoEntity;
import com.mrlep.meon.services.MediaService;
import com.mrlep.meon.services.PaymentInfoService;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.MediaValidateUtils;
import com.mrlep.meon.utils.TeleCareException;
import com.mrlep.meon.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/payment-info")
public class PaymenInfoController {

    @Autowired
    private PaymentInfoService paymentInfoService;

    @Autowired
    private ConfigValue configValue;

    @RequestMapping(path = "/shop/{shopId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByShop(@PathVariable Integer shopId
            , @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            FnCommon.getUserIdFromToken(authentication);
            result = paymentInfoService.getPaymentInfoByShop(shopId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addPaymentByShop(@RequestBody PaymentInfoEntity request
            , @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = paymentInfoService.addPaymentInfo(request);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @RequestMapping(path = "/{paymentInfoId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePaymentByShop(@PathVariable Integer paymentInfoId, @RequestBody PaymentInfoEntity request
            , @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setId(paymentInfoId);
            request.setCreateUserId(userId);
            result = paymentInfoService.addPaymentInfo(request);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{paymentInfoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePayment(@PathVariable Integer paymentInfoId, @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
           Integer userId =  FnCommon.getUserIdFromToken(authentication);
            result = paymentInfoService.deletePaymentInfo(paymentInfoId,userId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }
}
