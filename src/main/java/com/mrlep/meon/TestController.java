package com.mrlep.meon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Test2";
    }

    @GetMapping("/healthz")
    public String getHealthz() {
        return "ok";
    }

}
