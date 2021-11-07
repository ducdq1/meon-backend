package com.mrlep.meon.controllers;

import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.SearchBillRequest;
import com.mrlep.meon.dto.request.UpdateStatusRequest;
import com.mrlep.meon.services.BillService;
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

import java.util.List;

@RestController
@RequestMapping("bill")
@CrossOrigin(origins = "*")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createBill(@RequestBody CreateBillRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = billService.createBill(request);

        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateBill(@RequestBody CreateBillRequest request, @PathVariable Integer billId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            request.setBillId(billId);
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = billService.updateBill(request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/status/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTableStatus(@PathVariable Integer billId, @RequestBody UpdateStatusRequest request,
                                                    @AuthenticationPrincipal Authentication authentication,
                                                    @RequestHeader(value = "permissions") List<String> permissions) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = billService.updateBillStatus(userId, billId, permissions, request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/join/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> joinBill(@PathVariable Integer billId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = billService.joinBill(billId, userId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/join/{billId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> joinBillByPhoneNumber(@PathVariable Integer billId, @PathVariable Integer userId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            FnCommon.getUserIdFromToken(authentication);
            result = billService.joinBill(billId, userId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{billId}/table/{tableIds}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addTable(@PathVariable Integer billId, @PathVariable List<Integer> tableIds, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = billService.addTableBill(billId, userId, tableIds);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/shop/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillByShopId(@PathVariable Integer shopId, @RequestBody SearchBillRequest request) {
        Object result;
        try {
            result = billService.getBillsByShop(shopId, request);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillDetail(@PathVariable Integer billId) {
        Object result;
        try {
            result = billService.getDetailBills(billId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/check-bill", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillActiveByUser(@AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = billService.getBillActiveByUser(userId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/check-bill/{shopId}/{tableId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillByTable(@AuthenticationPrincipal Authentication authentication, @PathVariable Integer shopId, @PathVariable Integer tableId) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = billService.getBillByTable(shopId, tableId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteBill(@PathVariable Integer billId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = billService.deleteBill(billId, userId);
        } catch (TeleCareException e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchBillsOfUser(@RequestBody SearchBillRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setUserId(userId);
            result = billService.searchBillUser(request);
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