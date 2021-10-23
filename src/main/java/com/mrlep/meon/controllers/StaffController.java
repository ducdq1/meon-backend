package com.mrlep.meon.controllers;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.CreateStaffRequest;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.StaffService;
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

@RestController
@RequestMapping("staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStaff(@RequestBody CreateStaffRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = staffService.createStaff(request);

        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStaff(@RequestBody CreateStaffRequest request, @PathVariable Integer staffId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            request.setStaffId(staffId);
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = staffService.updateStaff(request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/shop/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStaffsByShopId(@PathVariable Integer shopId) {
        Object result;
        try {
            result = staffService.getStaffsByShop(shopId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillDetail(@PathVariable Integer staffId) {
        Object result;
        try {
            result = staffService.getStaffDetail(staffId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteStaff(@PathVariable Integer staffId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = staffService.deleteStaff(staffId,userId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

}