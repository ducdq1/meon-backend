package com.mrlep.meon.controllers;

import com.mrlep.meon.dto.request.CreateMenuGroupRequest;
import com.mrlep.meon.dto.request.CreateMenuRequest;
import com.mrlep.meon.dto.request.SearchMenusRequest;
import com.mrlep.meon.services.MenusService;
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

/**
 * Autogen class: Lop thong tin benh nhan covid F
 *
 * @author ToolGen
 * @date Thu Sep 23 09:18:59 ICT 2021
 */
@RestController
@RequestMapping("menus")
@CrossOrigin(origins = "*")
public class MenuController {

    @Autowired
    private MenusService menusService;

    @Autowired
    HttpServletRequest request;

    @GetMapping(value = "/groups/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMenuGroups(@PathVariable Integer shopId) {
        Object result;
        try {
            result = menusService.getMenuGroups(shopId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMenuGroup(@RequestBody CreateMenuGroupRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {

            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);

            result = menusService.createMenuGroup(request);
        }  catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), e.getCodeError().intValue() == 401 ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/groups/{menuGroupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMenuGroup(@RequestBody CreateMenuGroupRequest request, @PathVariable Integer menuGroupId,@AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = menusService.updateMenuGroup(menuGroupId, request);
        }  catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), e.getCodeError().intValue() == 401 ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/groups/{menuGroupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteMenuGroup(@PathVariable Integer menuGroupId) {
        Object result;
        try {

            result = menusService.deleteMenuGroup(menuGroupId);
        }  catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @GetMapping(value = "/{shopId}/{menuGroupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMenusByMenuGroup(@PathVariable Integer shopId, @PathVariable Integer menuGroupId) {
        Object result;
        try {
            result = menusService.getMenus(shopId, menuGroupId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/shop/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllMenus(@PathVariable Integer shopId) {
        Object result;
        try {
            result = menusService.getMenus(shopId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @GetMapping(value = "/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMenuDetail(@PathVariable Integer menuId) {
        Object result;
        try {
            result = menusService.getMenuDetail(menuId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e),e.getCodeError().intValue() == 401 ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMenu(@RequestBody CreateMenuRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = menusService.createMenu(request);
        }  catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e),e.getCodeError().intValue() == 401 ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PutMapping(value = "/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMenu(@RequestBody CreateMenuRequest request, @PathVariable Integer menuId,@AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = menusService.updateMenu(menuId, request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), e.getCodeError().intValue() == 401 ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteMenu(@PathVariable Integer menuId) {
        Object result;
        try {
            result = menusService.deleteMenu(menuId);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @PostMapping(value = "/recommend", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMenuRecommend(@RequestBody SearchMenusRequest request) {
        Object result;
        try {
            result = menusService.getRecommendMenus(request);
        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e),e.getCodeError().intValue() == 401 ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }
}