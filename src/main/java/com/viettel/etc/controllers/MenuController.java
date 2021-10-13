package com.viettel.etc.controllers;

import com.viettel.etc.dto.request.CreateMenuGroupRequest;
import com.viettel.etc.dto.request.CreateMenuRequest;
import com.viettel.etc.repositories.tables.CatsShopRepositoryJPA;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Autogen class: Lop thong tin benh nhan covid F
 *
 * @author ToolGen
 * @date Thu Sep 23 09:18:59 ICT 2021
 */
@RestController
@RequestMapping("menus")
public class MenuController {

    @Autowired
    private CatsShopRepositoryJPA catsShopRepositoryJPA;

    @Autowired
    HttpServletRequest request;

    @GetMapping(value = "/groups/{shopId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMenuGroups(@PathVariable Integer shopId) {
        Object result;
        try {
            result = catsShopRepositoryJPA.getAllByIsActive(Constants.IS_ACTIVE);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/groups",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMenuGroup(@RequestBody CreateMenuGroupRequest request) {
        Object result;
        try {
            result = catsShopRepositoryJPA.getAllByIsActive(Constants.IS_ACTIVE);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{menuGroupId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMenuGroup(@RequestBody CreateMenuGroupRequest request,@PathVariable Integer menuGroupId) {
        Object result;
        try {
            result = catsShopRepositoryJPA.getAllByIsActive(Constants.IS_ACTIVE);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @GetMapping(value = "/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMenus(@PathVariable  Integer shopId) {
        Object result;
        try {
            result = catsShopRepositoryJPA.getAllByIsActive(Constants.IS_ACTIVE);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMenu(@RequestBody CreateMenuRequest request) {
        Object result;
        try {
            result = catsShopRepositoryJPA.getAllByIsActive(Constants.IS_ACTIVE);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{menuId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMenu(@RequestBody CreateMenuRequest request,@PathVariable Integer menuId) {
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