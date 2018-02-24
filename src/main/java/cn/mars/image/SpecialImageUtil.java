package cn.mars.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Description：
 * Created by Mars on 2018/1/15.
 */
@org.springframework.stereotype.Component
public class SpecialImageUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImageUtil imageUtil;


    private final String PIC_PNG = "png";
    private final float ALPHA = 1f;
    private final int AVATAR_WIDTH_HEIGHT = 85;
    private final int AVATAR_MARGIN_LEFT = 23;
    private final int AVATAR_MARGIN_TOP = 34;
    private final int NICKNAME_MARGIN_LEFT = 125;
    private final int NICKNAME_MARGIN_TOP = 30;
    private final int SHARE_CODE_MARGIN_LEFT = 280;
    private final int SHARE_CODE_MARGIN_TOP = 980;
    private final String DEFAULT_FONT_FAMILY = "黑体";
    private final int DEFAULT_FONT_STYLE = Font.BOLD|Font.ITALIC;
    private final Color DEFAULT_FONT_COLOR = Color.BLACK;
    private final int NICKNAME_FONT_SIZE = 20;
    private final int SHARECODE_FONT_SIZE = 48;

    private void combineImage(String avatarUrl, String targetFilePath, String nickName, String shareCode){
        try {
            logger.info("开始下载头像...");
            BufferedImage bi = imageUtil.getUrlByBufferedImage(avatarUrl);
            logger.info("头像下载完成...");

            //处理图片将其压缩成正方形的小图
            BufferedImage  avatarImage = imageUtil.scaleByPercentage(bi, AVATAR_WIDTH_HEIGHT, AVATAR_WIDTH_HEIGHT);
            //裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
            avatarImage = imageUtil.convertCircular(avatarImage);
            logger.info("裁剪压缩");
            //生成的图片位置
//            ImageIO.write(avatarImage, PIC_PNG, new File(targetFilePath));
//            System.out.println("ok");


            File file = new File(targetFilePath);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);

            g.setColor(DEFAULT_FONT_COLOR);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            g.setFont(new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, NICKNAME_FONT_SIZE));
//            int nicknameWidth = NICKNAME_FONT_SIZE * imageUtils.getLength(nickName);
//            int nicknameWidthDiff = width - nicknameWidth;
//            int nicknameHeightDiff = height - NICKNAME_FONT_SIZE;
            g.drawString(nickName, NICKNAME_MARGIN_LEFT, NICKNAME_MARGIN_TOP + NICKNAME_FONT_SIZE);

            g.setFont(new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, SHARECODE_FONT_SIZE));
//            int sharecodeWidth = NICKNAME_FONT_SIZE * imageUtils.getLength(shareCode);
//            int sharecodeWidthDiff = width - sharecodeWidth;
//            int sharecodeHeightDiff = height - SHARECODE_FONT_SIZE;
            g.drawString(shareCode, SHARE_CODE_MARGIN_LEFT, SHARE_CODE_MARGIN_TOP + SHARECODE_FONT_SIZE);


            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
            g.drawImage(avatarImage, AVATAR_MARGIN_LEFT, AVATAR_MARGIN_TOP, AVATAR_WIDTH_HEIGHT, AVATAR_WIDTH_HEIGHT, null); // 水印文件结束
            g.dispose();

            ImageIO.write(bufferedImage, PIC_PNG, file);

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private void makeAvatarCircle(String url, String targetFilePath){
        try {
            logger.info("开始下载头像...");
            BufferedImage bi = imageUtil.getUrlByBufferedImage(url);
            logger.info("头像下载完成...");

            //处理图片将其压缩成正方形的小图
            BufferedImage  convertImage = imageUtil.scaleByPercentage(bi, 85,85);
            //裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
            convertImage = imageUtil.convertCircular(convertImage);
            //生成的图片位置
            ImageIO.write(convertImage, targetFilePath.substring(targetFilePath.lastIndexOf(".") + 1), new File(targetFilePath));
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
