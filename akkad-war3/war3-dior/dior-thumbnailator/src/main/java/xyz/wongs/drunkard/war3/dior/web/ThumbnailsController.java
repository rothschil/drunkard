package xyz.wongs.drunkard.war3.dior.web;

import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.wongs.drunkard.war3.dior.service.IThumbnailsService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ThumbnailsController$
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 21/1/6$ 16:16$
 * @Version 1.0.0
 */
@RestController
public class ThumbnailsController {

    @Resource
    private IThumbnailsService thumbnailsService;

    private static final String OUT_FILE ="D:\\data\\";

    /**
     * 指定大小缩放
     * @param resource
     * @param width
     * @param height
     * @return
     */
    @GetMapping("/changeSize")
    public void changeSize(MultipartFile resource, int width, int height) {
        try {
            thumbnailsService.changeSize(ThumbnailsController.multipartFileToFile(resource),width,height,OUT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 指定比例缩放
     * @param resource
     * @param scale
     * @return
     */
    @GetMapping("/changeScale")
    public void changeScale(MultipartFile resource, double scale) {
        try {
            thumbnailsService.changeScale(ThumbnailsController.multipartFileToFile(resource),scale,OUT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加水印 watermark(位置,水印,透明度)
     * @param resource
     * @param shuiyin
     * @param opacity   透明度
     * @return
     */
    @GetMapping("/watermark")
    public void watermark(MultipartFile resource, MultipartFile shuiyin, float opacity) {
        try {
            thumbnailsService.watermark(ThumbnailsController.multipartFileToFile(resource),Positions.CENTER_LEFT,ThumbnailsController.multipartFileToFile(shuiyin),opacity,OUT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片旋转 rotate(度数),顺时针旋转
     * @param resource
     * @param rotate
     * @return
     */
    @GetMapping("/rotate")
    public void rotate(MultipartFile resource, double rotate) {
        try {
            thumbnailsService.rotate(ThumbnailsController.multipartFileToFile(resource),rotate,OUT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片裁剪
     * @param resource
     * @param p
     * @param width
     * @param height
     * @return
     */
    @GetMapping("/region")
    public void region(MultipartFile resource,int width, int height) {
        try {
            thumbnailsService.region(ThumbnailsController.multipartFileToFile(resource),Positions.CENTER_LEFT,width,height,OUT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}