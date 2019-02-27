package com.shuframework.commontools;

import com.shuframework.commonbase.exception.UtilException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码生成器 无干扰线
 *
 * @author shuheng
 */
@Deprecated
public class VCodeNoLine {

    // 图片的宽度。
    private static int width = 130;
    // 图片的高度。
    private static int height = 50;
    // 验证码字符个数
    private static int codeCount = 4;
//    // 验证码
//    private String code = null;
//    // 验证码图片Buffer
//    private BufferedImage buffImg;

    //    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'P', 'Q', 'R',
//            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
    // 使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 获得4位验证码且输出
     * @param outputStream
     */
    public static String getCodeAndWrite(OutputStream outputStream) {
        try {
            return getCodeAndWrite(width, height, codeCount, outputStream);
        } catch (IOException e) {
            throw new UtilException(e.getMessage());
        }
    }

    /**
     * 获得4位验证码且输出
     * @param path
     * @return
     */
    public static String getCodeAndWrite(String path) {
        try {
            OutputStream outputStream = new FileOutputStream(path);
            return getCodeAndWrite(width, height, codeCount, outputStream);
        } catch (IOException e) {
            throw new UtilException(e.getMessage());
        }
    }

    public static String getCodeAndWrite(int width, int height, int codeCount, OutputStream outputStream) throws IOException {
        int codeX = 0;
        int fontHeight = 0;
        fontHeight = height - 5;// 字体的高度
        codeX = width / (codeCount + 2);// 每个字符的宽度

        // 图像buffer
        BufferedImage buffImg = getBufferedImage(width, height);
        Graphics2D g = buffImg.createGraphics();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体
        Font font = ImgFontByte.getFont(fontHeight);
        g.setFont(font);

        StringBuffer randomCode = new StringBuffer();
        int codesLen = VERIFY_CODES.length();
        // 随机产生验证码字符
        for (int i = 0; i < codeCount; i++) {
//            int index = getRandomNumber(codeSequence.length);
//            String strRand = String.valueOf(codeSequence[index]);
            char strRand = VERIFY_CODES.charAt(getRandomNumber(codesLen));
            // 设置字体颜色
            g.setColor(getRandomColor());
            // 设置字体位置
//            int x1 = ((width - 10) / codeCount) * i + 5;//这样字体间隔较大，适合有干扰线的算法
//            System.out.println("x1:"+x1);
//            int y2 = getRandomNumber(height / 2) + 25;
//            System.out.println("y2:"+y2);
            int x2 = (i + 1) * codeX; // codeX = width / (codeCount + 2)
//            System.out.println("x2:"+x2);
            g.drawString(String.valueOf(strRand), x2, getRandomNumber(height / 2) + 25);
            randomCode.append(strRand);
        }
        //输出
        ImageIO.write(buffImg, "png", outputStream);
        outputStream.close();

        return randomCode.toString();
    }

    private static BufferedImage getBufferedImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * 获取随机颜色
     */
    private static Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }

    /**
     * 获取随机数
     */
    private static int getRandomNumber(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }


    /**
     * 字体样式类
     */
    static class ImgFontByte {
        public static Font getFont(int fontHeight) {
            try {
                Font baseFont = Font.createFont(Font.HANGING_BASELINE, new ByteArrayInputStream(
                        hex2byte(getFontByteStr())));
                return baseFont.deriveFont(Font.PLAIN, fontHeight);
            } catch (Exception e) {
                return new Font("Arial", Font.PLAIN, fontHeight);
            }
        }

        private static byte[] hex2byte(String str) {
            if (str == null)
                return null;
            str = str.trim();
            int len = str.length();
            if (len == 0 || len % 2 == 1)
                return null;

            byte[] b = new byte[len / 2];
            try {
                for (int i = 0; i < str.length(); i += 2) {
                    b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
                }
                return b;
            } catch (Exception e) {
                return null;
            }
        }

        // 字体文件的十六进制字符串
        private static String getFontByteStr() {
//            return "宋体";
            return "Algerian";
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            String path = "D:/" + System.currentTimeMillis() + ".png";
            String codeStr = VCodeNoLine.getCodeAndWrite(path);
            System.out.println(codeStr);
        }
    }
}
