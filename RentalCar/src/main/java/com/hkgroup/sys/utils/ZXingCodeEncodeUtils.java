package com.hkgroup.sys.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.EnumMap;

/**
 * 生成二维码
 */
public class ZXingCodeEncodeUtils {
    // ⼆维码颜⾊
    private static final int BLACK = 0xFF000000;
    // ⼆维码背景颜⾊
    private static final int WHITE = 0xFFFFFFFF;
    // ⼆维码格式参数
    private static final EnumMap<EncodeHintType, Object> hints =
            new EnumMap<EncodeHintType, Object>(EncodeHintType.class);

    static {
 /*
 * ⼆维码的纠错级别(排错率),4个级别： L (7%)、 M (15%)、 Q (25%)、
H (30%)(最⾼H)
 * 纠错信息同样存储在⼆维码中，纠错级别越⾼，纠错信息占⽤的空间越多，那么
能存储的有⽤讯息就越少；共有四级； 选择M，扫描速度快。
 */
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // ⼆维码边界空⽩⼤⼩ 1,2,3,4 (4为默认,最⼤)
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");// 设置放⼊的字符的编码
    }

    /**
     * ⽣成⼆维码保存到D盘
     */
    public static void createZXingCodeSaveToDisk(String content, int width, int height, String savePath, String imageType) {
        try {
            BufferedImage image = createZXingCodeNormal(content, width,
                    height);
            // 保存图⽚到硬盘
            File file = new File(savePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            ImageIO.write(image, imageType, file);
            System.out.println("⽣成成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ⽣成⼆维码返回图⽚对象
     */
    public static BufferedImage createZXingCodeNormal(String content, int width, int height) {
        try {
            BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 得到⼆维码的宽度
            int code_width = encode.getWidth();
            int code_height = encode.getHeight();
            // 创建图⽚
            BufferedImage image = new BufferedImage(code_width,
                    code_height, BufferedImage.TYPE_INT_RGB);
            // 把⼆维码⾥⾯的信息写到图⽚⾥⾯
            for (int i = 0; i < code_width; i++) {
                for (int j = 0; j < code_height; j++) {
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * ⽣成⼀张带logo的⼆维码
     *
     * @param logoStream logo的流对象
     */
    public static void createZxingCodeUseLogoSaveToDisk(String content, int width, int height, String savePath, String imageType, InputStream logoStream) {
        try {
            BufferedImage codeImage = createZxingCodeUseLogo(content,
                    width, height, logoStream);
            // 保存图⽚到硬盘
            File file = new File(savePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            ImageIO.write(codeImage, imageType, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ⽣成⼀张带logo的⼆维码 返回BuffredeImage
     *
     * @param logoStream logo的流对象
     */
    public static BufferedImage createZxingCodeUseLogo(String content, int width, int height, InputStream logoStream) {
        try {
            //⽣成⼆维码图⽚
            BufferedImage codeNormal = createZXingCodeNormal(content,
                    width, height);
            if (null != codeNormal) {
                //判断logoStream是否为空
                if (null != logoStream) {
                    //拿到可以操作当前图⽚的画笔
                    Graphics2D graphics = codeNormal.createGraphics();
                    //得到logo图⽚的对象
                    BufferedImage logoImage = ImageIO.read(logoStream);
                    //得到logo的原始宽⾼
                    int old_logo_width = logoImage.getWidth();
                    int old_logo_height = logoImage.getHeight();
                    //得到⼆维码的宽⾼
                    int code_width = codeNormal.getWidth();
                    int code_height = codeNormal.getHeight();

                    //算出logo在⼆维码⾥⾯能存在的最⼤值
                    int logo_max_width = code_width / 5;
                    int logo_max_height = code_height / 5;

                    //计算logo的可⽤宽⾼
                    int logo_width = logo_max_width < old_logo_width ?
                            logo_max_width : old_logo_width;
                    int logo_height = logo_max_height < old_logo_height ?
                            logo_max_height : old_logo_height;

                    //计算logo的开始点的坐标
                    int x = (code_width - logo_width) / 2;
                    int y = (code_height - logo_height) / 2;

                    /**
                     * logoImage logo图⽚对象
                     * x 开始画的x轴坐标
                     * y 开始画的y轴的坐
                     * logo_width 要画的x轴的⻓度
                     * logo_height 要画的y⻋的⻓度
                     * arg5 null
                     */
                    graphics.drawImage(logoImage, x, y, logo_width,
                            logo_height, null);

                    graphics.setStroke(new BasicStroke(2));
                    graphics.setColor(Color.WHITE);
                    //画⽩⾊边框
                    graphics.drawRoundRect(x, y, logo_width, logo_height,
                            15, 15);
                    graphics.dispose();//让画上的去的内容⽣效
                    return codeNormal;
                }
            } else {
                System.out.println("⽣成失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⽣成失败");
        }
        return null;
    }

    public static void main(String[] args) {
    // createZXingCodeSaveToDisk("⽼王", 400, 400, "D:/laowang.gif", "JPEG");
        InputStream logoStream = ZXingCodeEncodeUtils.class.getClassLoader().getResourceAsStream("logo.jpg");
        createZxingCodeUseLogoSaveToDisk("⽼王", 400, 400, "D:/laowang.gif", "JPEG", logoStream);
    }
}
