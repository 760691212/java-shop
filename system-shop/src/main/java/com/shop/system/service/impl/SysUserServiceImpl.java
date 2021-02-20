package com.shop.system.service.impl;

import com.shop.common.utils.CaptchaUtils;
import com.shop.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private CaptchaUtils captchaUtils;


    @Override
    public String getCaptcha(HttpServletRequest request, HttpServletResponse response, Integer width, Integer height, String imgType) throws IOException {

        HttpSession session = request.getSession();

        String URI = request.getRequestURI();

        System.out.println("**********" + URI + "***************");

        final int w = width;
        final int h = height;
        final String t = imgType;
        final OutputStream outputStream = response.getOutputStream();

        String captchaImg = this.captchaUtils.createCaptcha(w, h, t, outputStream);

        System.out.println("验证码内容: " + captchaImg);

        session.setAttribute(URI, captchaImg);

        System.out.println(session.getAttribute(URI));

        return captchaImg;
    }
}
