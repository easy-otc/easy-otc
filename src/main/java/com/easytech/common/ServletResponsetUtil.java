/**
 * Copyright (C), 2011-2016, 微贷网.
 */
package com.easytech.common;

import com.easytech.otc.mvc.protocol.Resp;

import java.io.IOException;

import javax.servlet.ServletResponse;

/**
 *
 * @author exgods
 * @version $Id: ServletResponsetUtil.java, v 0.1 2016年5月18日 下午7:36:52 exgods Exp $
 */
public class ServletResponsetUtil {

    public final static String HTTP_CONTENT_TYPE = "application/json;charset=utf-8";

    /**
     * 响应
     *
     * @param response
     * @param result
     * @throws IOException
     */
    public static void respond(ServletResponse response, Resp<?> result) throws IOException {
        String jsonString = result.toPlainProtocol();
        response.setContentType(HTTP_CONTENT_TYPE);
        response.getWriter().write(jsonString);
        response.getWriter().flush();
        response.getWriter().close();
    }

}
