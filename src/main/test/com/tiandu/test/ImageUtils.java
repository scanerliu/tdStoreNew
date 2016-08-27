package com.tiandu.test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class ImageUtils {
    public ImageUtils() {

    }
    /**
     * 把图片印刷到图片上
     *
     * @param pressImg --
     *            水印文件
     * @param targetImg --
     *            目标文件
     * @param x
     *            --x坐标
     * @param y
     *            --y坐标
     */
    public final static void pressImage(String pressImg, String targetImg, String unick, String title, String expireDate) {
        try {
            //目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            
            //头像水印文件
            BufferedImage headimage = ImageIO.read(new URL("http://wx.qlogo.cn/mmopen/Gs37d0V15yPs9gG71Q1a72scdZsibT5rqySqFibrGj9EAtJ7xgTol8C71Xp3ZGic9JujJTY45B6Yb3w5qiaA3tPs5kaSbQyLze4U/0"));
            int w = headimage.getWidth();
            int h = headimage.getHeight();
            int radus = w>h?w:h;
            BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            g2.setColor(Color.WHITE);
            g2.setBackground(Color.WHITE);
            output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setBackground(Color.WHITE);
            g2.fillRoundRect(0, 0,w, h, radus, radus);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,  1.0f));
            g2.drawImage(headimage, 0, 0, w, h, null);
            g2.dispose();
            int wideth_biao1 = 100;
            int height_biao1 = 100;
            g.drawImage(output, 60, 40, wideth_biao1, height_biao1, null);
            
            //nick水印
            g.setColor(Color.BLACK);
            g.setFont(new Font("微软雅黑", Font.BOLD, 29));
            g.drawString(unick, 170, 100);
            //title水印
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("微软雅黑", Font.PLAIN, 22));
            g.drawString(title, 90, 185);
            //expireDate水印
            g.setColor(Color.GRAY);
            g.setFont(new Font("微软雅黑", Font.BOLD,16));
            g.drawString(expireDate, 358, 745);
            

            //二维码水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = 400;
            int height_biao = 400;
            g.drawImage(src_biao, 118, 250, wideth_biao, height_biao, null);
            //水印文件结束
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字水印图片
     *
     * @param pressText
     *            --文字
     * @param targetImg --
     *            目标图片
     * @param fontName --
     *            字体名
     * @param fontStyle --
     *            字体样式
     * @param color --
     *            字体颜色
     * @param fontSize --
     *            字体大小
     * @param x --
     *            偏移量
     * @param y
     */

    public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, int color, int fontSize, int x,
                                 int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            g.setColor(Color.BLACK);
            g.setFont(new Font(fontName, fontStyle, fontSize));

            g.drawString(pressText, wideth - fontSize - x, height - fontSize
                    / 2 - y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static String makeRoundedCorner(String srcImageFile, String result, String type, int cornerRadius) {
        try {
            BufferedImage image = ImageIO.read(new File(srcImageFile));
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            g2.setColor(Color.WHITE);
            g2.setBackground(Color.WHITE);
            output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setBackground(Color.WHITE);
//            g2.fillRoundRect(0, 0,w, h, cornerRadius, cornerRadius);
            g2.fillRoundRect(0, 0,w, h, cornerRadius, cornerRadius);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,  1.0f));
            g2.drawImage(image, 0, 0, w, h, null);
            g2.dispose();
            ImageIO.write(output, type, new File(result));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //pressText("by kiritor","D://1.jpg","华文楷体",5,12,20, 50,50);
    	pressImage("D://var//qrcode.jpg","D://var//share.jpg","只是快一个谁丹佛","额头股份热情而阿斯顿","2016-09-30");
    	//makeRoundedCorner("D://var//qrcode.png","D://var//qrcode_round.png","png",160);
    }
}