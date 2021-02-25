package com.shop.auth.service.impl;

import com.shop.auth.service.ManageLoginAuthService;
import com.shop.common.utils.CaptchaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

@Service
public class ManageLoginAuthServiceImpl implements ManageLoginAuthService {

    @Autowired
    private CaptchaUtils captchaUtils;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getCaptcha(HttpServletRequest request, HttpServletResponse response, Integer width, Integer height, String imgType) throws IOException {
        HttpSession session = request.getSession();
        final int w = width;
        final int h = height;
        final String t = imgType;
        final OutputStream outputStream = response.getOutputStream();
        String captcha = this.captchaUtils.createCaptcha(w, h, t, outputStream);
        // 将验证码存入redis
        this.redisTemplate.opsForValue().set(captchaUtils.GET_CAPTCHA_SESSION(), captcha, 2, TimeUnit.MINUTES);
        System.out.println(captcha);
        return captcha;
    }
}
