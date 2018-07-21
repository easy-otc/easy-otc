package com.easytech.otc.mvc.controller.api;

import org.springframework.web.bind.annotation.*;

import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.vo.LoginRequest;
import com.easytech.otc.mvc.vo.LoginReturnVO;

@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/demo")
public class DemoController {

    @GetMapping(value = "/{uid}")
    @ACL(authControl = true, authTagKey = "uid", idempotent = true)
    public Resp<Object> demo(@PathVariable("uid") String uid, @RequestParam String idempotent) {
        Resp<Object> result = new Resp<>();

        return result;
    }

    @PostMapping
    @ACL(authControl = false)
    public Resp<LoginReturnVO> login(@RequestBody LoginRequest loginRequest) {
        Resp<LoginReturnVO> result = new Resp<>();

        return result;
    }
}