package com.shop.system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SysUserService {
    String getCaptcha(HttpServletRequest request, HttpServletResponse response, Integer width, Integer height, String imgType) throws IOException;
}
