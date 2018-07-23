package com.easytech.otc.mvc.protocol;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easytech.otc.common.ServletResponsetUtil;
import com.easytech.otc.common.WebTool;

import lombok.extern.slf4j.Slf4j;

/**
 * 访问控制拦截器
 */
@Slf4j
@Component
public class ACLInterceptor implements HandlerInterceptor {

    @Autowired
    AuthedInfoRepository authedInfoRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ACL aclAnn = null;
        HandlerMethod handlerMethod = null;

        if (handler != null && handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
            aclAnn = handlerMethod.getMethodAnnotation(ACL.class);
        }

        if (aclAnn == null) {
            RespWithoutData ret = new RespWithoutData();
            ret.setFail(RetCodeEnum.FORBIDDEN);
            ServletResponsetUtil.respond(response, ret);
            return false;
        }

        if ((!aclAnn.authControl())) {
            return true;
        }

        String idempotent = request.getHeader(HeaderNames.IDEMPOTENT);
        String token = request.getHeader(HeaderNames.AUTHORIZATION);
        String uid = request.getHeader(HeaderNames.IDENTITY);
        AuthedInfo authedInfo = authedInfoRepository.getAuthedInfo(token);
        if (authedInfo == null) {
            RespWithoutData ret = new RespWithoutData();
            ret.setFail(RetCodeEnum.UNAUTHORIZED);
            ServletResponsetUtil.respond(response, ret);
            return false;
        }

        Map<String, String> arguments = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        //String urlAuthTag = arguments.get(aclAnn.authTagKey());
        String authTag = String.valueOf(authedInfo.getUid());
        if (!StringUtils.equals(uid, authTag)) {
            RespWithoutData ret = new RespWithoutData();
            ret.setFail(RetCodeEnum.ILLEGAL_AUTHORIZATION);
            ServletResponsetUtil.respond(response, ret);
            return false;
        }

        if (aclAnn.idempotent()) {
            if (StringUtils.isBlank(idempotent)) {
                RespWithoutData ret = new RespWithoutData();
                ret.setFail(RetCodeEnum.NO_IDEMPOTENT);
                ServletResponsetUtil.respond(response, ret);
                return false;
            }

            arguments.put(HeaderNames.IDEMPOTENT, idempotent);
            request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, arguments);
        }

        RequestContext.setAuthedInfo(authedInfo);
        RequestContext.setIp(WebTool.getIpAddr(request));
        authedInfoRepository.saveAuthedInfo(authedInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContext.clear();
    }
}