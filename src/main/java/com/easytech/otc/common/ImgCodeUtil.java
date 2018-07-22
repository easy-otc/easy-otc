package com.easytech.otc.common;

import com.easytech.otc.config.PropertiesConfig;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Description: 生成图片验证码
 * Author: Hank
 * Date: 2018/7/22 13:55
 */

public class ImgCodeUtil {

    public static String genCode() {

        if (PropertiesConfig.isMock()) {
            return "1234";
        } else {
            String vcode = "";
            for (int i = 0; i < 4; i++) {
                vcode = vcode + (int) (Math.random() * 9);
            }
            return vcode;

        }
    }

    /**
     *
     * @param code
     * @param width 宽
     * @param height 高
     * @param lines 干扰线条数
     * @return
     */
    public static BufferedImage getImgeCode(String code,int width,int height,int lines) {

        Random r = new Random();
        BufferedImage b = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = b.getGraphics();
        g.setFont(new Font("宋体", Font.BOLD, 25));

        for (int i=0;i<code.length();i++) {

            int y = 20 + r.nextInt(20);// 10~40范围内的一个整数，作为y坐标
            //随机颜色，RGB模式
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
//            g.drawString("" + a, 5 + i * width / 4, y);
            //写验证码
            g.drawString(String.valueOf(code.charAt(i)), 5 + i * width / 4, y);

        }
        for (int i = 0; i < lines; i++) {
            //设置干扰线颜色
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }
        try {
            //清空缓冲
            g.dispose();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;

    }
}
