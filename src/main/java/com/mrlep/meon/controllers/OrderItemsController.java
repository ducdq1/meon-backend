package com.mrlep.meon.controllers;

import com.mrlep.meon.dto.request.AddOrderItemRequest;
import com.mrlep.meon.dto.request.CreateBillRequest;
import com.mrlep.meon.dto.request.UpdateStatusRequest;
import com.mrlep.meon.services.BillService;
import com.mrlep.meon.services.OrderItemService;
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
@RequestMapping("orders")
@CrossOrigin(origins = "*")
public class OrderItemsController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addOrderItem(@RequestBody AddOrderItemRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = orderItemService.addOrderItem(request);

        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{orderItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateOrderItem(@RequestBody AddOrderItemRequest request, @PathVariable Integer orderItemId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            request.setOrderItemId(orderItemId);
            result = orderItemService.updateOrderItem(request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/amount/{orderItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateOrderItemAmount(@RequestBody AddOrderItemRequest request, @PathVariable Integer orderItemId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            request.setOrderItemId(orderItemId);
            result = orderItemService.updateOrderItemAmount(request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @PutMapping(value = "/{orderItemId}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateOrderItemStatus(@PathVariable Integer orderItemId, @RequestBody UpdateStatusRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = orderItemService.updateOrderItemStatus(userId, orderItemId, request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/bill/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrderItemByBill(@PathVariable Integer billId) {
        Object result;
        try {
            result = orderItemService.getOrderItemsByBill(billId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{orderItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteOrderItem(@PathVariable Integer orderItemId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = orderItemService.deleteOrderItem(orderItemId, userId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/{orderItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailOrderItem(@PathVariable Integer orderItemId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            FnCommon.getUserIdFromToken(authentication);
            result = orderItemService.getDetailOrderItems(orderItemId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

}