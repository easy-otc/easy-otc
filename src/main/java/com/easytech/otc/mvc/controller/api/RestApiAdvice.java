package com.easytech.otc.mvc.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.easytech.otc.exception.BizException;
import com.easytech.otc.mvc.protocol.RespWithoutData;
import com.easytech.otc.mvc.protocol.RetCodeEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestApiAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public RespWithoutData catchException(Throwable err) {
        LOGGER.error("rest exception", err);

        if (err instanceof IllegalArgumentException) {
            RespWithoutData response = new RespWithoutData();
            response.setSystemError();
            response.setErrorMsg(err.getMessage());
            return response;
        }

        if (err instanceof BizException) {
            BizException be = (BizException) err;
            RetCodeEnum re = be.getBizExceptionEnum();

            if (re != null) {
                RespWithoutData response = new RespWithoutData();
                response.setRetCode(re.getCode());
                response.setErrorMsg(re.getDesc());
                return response;
            }

            return newResp(StringUtils.isNotBlank(be.getMessage()) ? be.getMessage() : RetCodeEnum.BUSINESS_ERROR.getDesc());
        }

        return newResp(null);
    }

    private RespWithoutData newResp(String msg) {
        RespWithoutData response = new RespWithoutData();

        response.setSystemError();
        if (StringUtils.isNotBlank(msg)) {
            response.setErrorMsg(msg);
        } else {
            response.setErrorMsg(RetCodeEnum.UNKOWN.getDesc());
        }

        return response;
    }
}