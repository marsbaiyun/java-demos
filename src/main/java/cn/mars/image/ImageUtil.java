package cn.mars.image;

import org.springframework.stereotype.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Description：
 * Created by Mars on 2018/1/15.
 */
@org.springframework.stereotype.Component
public class ImageUtil {

    private static final String PIC_PNG = "png";

    /**
     * 添加图片水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param waterImg  水印图片路径，如：C://myPictrue//logo.png
     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public final static void pressImage(String targetImg, String waterImg, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);

            Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }
            g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
            g.dispose();
            ImageIO.write(bufferedImage, PIC_PNG, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int width_1 = fontSize * getLength(pressText);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }

            g.drawString(pressText, x, y + height_1);
            g.dispose();
            ImageIO.write(bufferedImage, PIC_PNG, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    /**
     * 缩小Image，此方法返回源图像按给定宽度、高度限制下缩放后的图像
     * @param inputImage
     * @param newWidth：压缩后宽度
     * @param newHeight：压缩后高度
     * @throws java.io.IOException
     * return
     */
    public static BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight) throws Exception {
        //获取原始图像透明度类型
        int type = inputImage.getColorModel().getTransparency();
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        //开启抗锯齿
        RenderingHints renderingHints=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //使用高质量压缩
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        BufferedImage img = new BufferedImage(newWidth, newHeight, type);
        Graphics2D graphics2d =img.createGraphics();
        graphics2d.setRenderingHints(renderingHints);
        graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
        graphics2d.dispose();
        return img;
    }



    /**
     * 传入的图像必须是正方形的 才会 圆形  如果是长方形的比例则会变成椭圆的
     * @param bi1 用户头像地址
     * @return
     * @throws IOException
     */
    public static BufferedImage convertCircular(BufferedImage bi1) throws IOException{
//        BufferedImage bi1 = ImageIO.read(new File(url));
        //这种是黑色底的
//        BufferedImage bi2 = new BufferedImage(bi1.getWidth(),bi1.getHeight(),BufferedImage.TYPE_INT_RGB);

        //透明底的图片
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(),bi1.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        Ellipse2D.Double shape = new Ellipse2D.Double(0,0,bi1.getWidth(),bi1.getHeight());
        Graphics2D g2 = bi2.createGraphics();
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1,0,0,null);
        //设置颜色
        g2.setBackground(Color.green);
        g2.dispose();
        return bi2;
    }



    /**
     * 通过网络获取图片
     * @param url
     * @return
     */
    public static BufferedImage getUrlByBufferedImage(String url){
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            // 连接超时
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢,增大时间
            conn.setReadTimeout(25000);
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Accept-Language", "zh-cn");
            conn.addRequestProperty("Content-type", "image/jpeg");
            conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727)");
            conn.connect();
            BufferedImage bufImg = ImageIO.read(conn.getInputStream());
            conn.disconnect();
            return bufImg;
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (ProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
