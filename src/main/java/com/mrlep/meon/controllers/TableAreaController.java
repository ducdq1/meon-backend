package com.mrlep.meon.controllers;

import com.mrlep.meon.config.ConfigValue;
import com.mrlep.meon.repositories.tables.entities.TableAreaEntity;
import com.mrlep.meon.services.TableAreaService;
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
@RequestMapping("/table-area")
public class TableAreaController {

    @Autowired
    private TableAreaService tableAreaService;

    @Autowired
    private ConfigValue configValue;

    @RequestMapping(path = "/shop/{shopId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByShop(@PathVariable Integer shopId
            , @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            FnCommon.getUserIdFromToken(authentication);
            result = tableAreaService.getTableAreaByShop(shopId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addPaymentByShop(@RequestBody TableAreaEntity request
            , @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            request.setCreateUserId(userId);
            result = tableAreaService.addTableArea(request);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{tableAreaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePayment(@PathVariable Integer tableAreaId, @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            FnCommon.getUserIdFromToken(authentication);
            result = tableAreaService.deleteTableArea(tableAreaId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }
}
