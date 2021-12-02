package com.mrlep.meon.controllers;

import com.mrlep.meon.dto.request.CreateStaffRequest;
import com.mrlep.meon.dto.request.StatisticsBillRequest;
import com.mrlep.meon.services.StaffService;
import com.mrlep.meon.services.StatisticsService;
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
@RequestMapping("statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping(path = "/bill", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStaff(@RequestBody StatisticsBillRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = statisticsService.getStatisticsByShop(request);

        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> order(@RequestBody StatisticsBillRequest request, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = statisticsService.getStatisticsOrderByShop(request);

        } catch (TeleCareException e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(path = "/order/by-month/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> orderByMonth(@PathVariable Integer shopId, @AuthenticationPrincipal Authentication authentication) {
        Object result;
        try {
            result = statisticsService.getStatisticsOrderByMonth(shopId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

}