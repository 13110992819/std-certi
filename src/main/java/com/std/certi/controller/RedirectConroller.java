package com.std.certi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.std.certi.bo.IGatewayIdAuthLogBO;
import com.std.certi.domain.GatewayIdAuthLog;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午1:44:16 
 * @history:
 */
@Controller
public class RedirectConroller {

    @Autowired
    IGatewayIdAuthLogBO gatewayIdAuthLogBO;

    @RequestMapping("/zhima")
    public String doCallbackOrder(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        GatewayIdAuthLog gatewayIdAuthLog = gatewayIdAuthLogBO
            .getGatewayIdAuthLogByBizNo(request.getParameter("bizNo"));
        return "redirect:" + gatewayIdAuthLog.getZhimaUrl();
    }

}
