package com.shop.system.service.impl;

import com.shop.common.utils.CaptchaUtils;
import com.shop.common.utils.IdWorker;
import com.shop.system.dao.SysUserDao;
import com.shop.system.domain.SysUser;
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

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public String getCaptcha(HttpServletRequest request, HttpServletResponse response, Integer width, Integer height, String imgType) throws IOException {
        HttpSession session = request.getSession();
        final int w = width;
        final int h = height;
        final String t = imgType;
        final OutputStream outputStream = response.getOutputStream();
        String captchaImg = this.captchaUtils.createCaptcha(w, h, t, outputStream);
        session.setAttribute(captchaUtils.GET_CAPTCHA_SESSION(), captchaImg);
        session.setMaxInactiveInterval(2*60); // 两分钟后过期
        return captchaImg;
    }

    @Override
    public SysUser checkUser(String username, String password) {

        return null;
    }
}
