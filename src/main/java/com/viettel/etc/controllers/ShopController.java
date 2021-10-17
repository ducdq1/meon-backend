package com.viettel.etc.controllers;

import com.viettel.etc.dto.request.*;
import com.viettel.etc.services.ShopService;
import com.viettel.etc.services.UsersService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
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
@RequestMapping("shops")
@CrossOrigin(origins = "*")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createShop(@RequestBody CreateShopRequest request,@AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = shopService.createShop(request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{shopId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateShp(@RequestBody CreateShopRequest request,@PathVariable Integer shopId,@AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            request.setId(shopId);
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = shopService.updateShop(request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllShop() {
        Object result;
        try {
            result = shopService.getAllShops();
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getShopByUserId(@RequestBody SearchShopsRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setUserId(userId);
            result = shopService.getShopsByUserId(request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/recommend",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRecommendedShop(@RequestBody SearchShopsRequest request) {
        Object result;
        try {
            result = shopService.getRecommendShops(request);
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