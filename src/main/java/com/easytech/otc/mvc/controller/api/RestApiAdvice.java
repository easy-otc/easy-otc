package com.easytech.otc.mvc.controller.api;

import com.easytech.otc.mvc.protocol.RespWithoutData;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class RestApiAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public RespWithoutData catchException(Throwable err) {
        LOGGER.error("rest exception", err);

        RespWithoutData response = new RespWithoutData();
        response.setSystemError();
        response.setErrorMsg(RetCodeEnum.UNKOWN.getDesc());
        return response;
    }
}