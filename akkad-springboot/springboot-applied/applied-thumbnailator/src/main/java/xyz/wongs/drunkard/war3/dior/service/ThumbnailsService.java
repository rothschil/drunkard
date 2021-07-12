package xyz.wongs.drunkard.war3.dior.service;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ThumbnailsService$
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 21/1/6$ 16:24$
 * @Version 1.0.0
 */
@Component("")
public class ThumbnailsService implements IThumbnailsService {

    /**
     *  指定大小缩放 若图片横比width小，高比height小，放大
     *  若图片横比width小，高比height大，高缩小到height，图片比例不变
     *  若图片横比width大，高比height小，横缩小到width，图片比例不变
     *  若图片横比width大，高比height大，图片按比例缩小，横为width或高为height
     * @Description
     * @param resource 源文件路径
     * @param width 宽
     * @param height    高
     * @param tofile    生成文件路径
     * @return void
     * @throws
     * @date 21/1/6 16:29
     */
    @Override
    public void changeSize(File resource, int width, int height, String tofile) {
        try {
            Thumbnails.of(resource)
                    .size(width, height)
                    .toFile(new File(tofile+resource.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 指定比例缩放 scale(),参数小于1,缩小;大于1,放大
     * @Description
     * @param resource  源文件路径
     * @param scale 指定比例
     * @param tofile    生成文件路径
     * @return void
     * @throws
     * @date 21/1/6 17:00
     */
    @Override
    public void changeScale(File resource, double scale, String tofile) {
        try {
            Thumbnails.of(resource).scale(scale).toFile(tofile+resource.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 添加水印 watermark(位置,水印,透明度)
     * @Description
     * @param resource  源文件路径
     * @param p     水印位置
     * @param shuiyin   水印文件路径
     * @param opacity   水印透明度
     * @param tofile    生成文件路径
     * @return void
     * @throws
     * @date 21/1/6 17:08
     */
    @Override
    public void watermark(File resource, Positions p, File shuiyin, float opacity, String tofile) {

        try {
            Thumbnails.of(resource).scale(1).watermark(p, ImageIO.read(shuiyin), opacity).toFile(tofile+resource.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 图片旋转 rotate(度数),顺时针旋转
     * @Description
     * @param resource  源文件路径
     * @param rotate   旋转度数
     * @param tofile    生成文件路径
     * @return void
     * @throws
     * @date 21/1/6 17:08
     */
    @Override
    public void rotate(File resource, double rotate, String tofile) {

        try {
            Thumbnails.of(resource).scale(1).rotate(rotate).toFile(tofile+resource.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 图片裁剪 sourceRegion()有多种构造方法，示例使用的是sourceRegion(裁剪位置,宽,高)
     * @Description
     * @param resource  源文件路径
     * @param p     水印位置
     * @param width 宽
     * @param height    高
     * @param tofile    生成文件路径
     * @return void
     * @throws
     * @date 21/1/6 17:08
     */
    @Override
    public void region(File resource, Positions p, int width, int height, String tofile) {
        try {
            Thumbnails.of(resource).scale(1).sourceRegion(p, width, height).toFile(tofile+resource.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
