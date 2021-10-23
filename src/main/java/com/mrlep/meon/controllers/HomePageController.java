package com.mrlep.meon.controllers;

import com.mrlep.meon.services.HomeBannerService;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Autogen class: Lop thong tin benh nhan covid F
 *
 * @author ToolGen
 * @date Thu Sep 23 09:18:59 ICT 2021
 */
@RestController
@RequestMapping("home")
@CrossOrigin(origins = "*")
public class HomePageController {

    @Autowired
    private HomeBannerService homeBanenrService;

    @Autowired
    HttpServletRequest request;

    @GetMapping(value = "/banners",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCatsShop() {
        Object result;
        try {
            result = homeBanenrService.getHomeBanners();
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


}