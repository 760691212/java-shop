package com.shop.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public final class CaptchaUtils {

    private static final char MAP_TABLE[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private final int CHAR_NUM = 5; // 显示的字符数目
    private final int DISTURB_LINES_NUM = 25; // 干扰线数目

    public String createCaptcha(final int width, final int height, final String imgType, OutputStream outputStream){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        // 设定背景色
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        // 在 "画板"上生成干扰线条 ( 25 是线条个数)
        graphics.setColor(getRandColor(160, 200));
        for (int i = 0; i < DISTURB_LINES_NUM; i++) {
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);
            final int w = random.nextInt(20);
            final int h = random.nextInt(20);
            final int signA = random.nextBoolean() ? 1 : -1;
            final int signB = random.nextBoolean() ? 1 : -1;
            graphics.drawLine(x, y, x + w * signA, y + h * signB);
        }

        // 在 "画板"上绘制字母
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        for (int i = 0; i < CHAR_NUM; i++) {
            stringBuffer.append(MAP_TABLE[(int) (MAP_TABLE.length * Math.random())]);
            // 将认证码显示到图象中
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 直接生成
            String str = stringBuffer.substring(i, i + 1);
            // 在背景图图片上的位置
            graphics.drawString(str, i * (width / 6) + 20, height - (height / 3));
        }
        graphics.dispose();

        try {
            ImageIO.write(image, imgType, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 给定范围获得随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


}
