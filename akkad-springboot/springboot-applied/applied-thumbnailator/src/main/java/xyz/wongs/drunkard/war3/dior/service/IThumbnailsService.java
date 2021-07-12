package xyz.wongs.drunkard.war3.dior.service;

import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author WCNGS@QQ.COM
 * @ClassName IThumbnailsService$
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 21/1/6$ 16:22$
 * @Version 1.0.0
 */
public interface IThumbnailsService {

    void changeSize(File resource, int width, int height, String tofile);

    void changeScale(File resource, double scale, String tofile);

    void watermark(File resource, Positions p, File shuiyin, float opacity, String tofile);

    void rotate(File resource, double rotate, String tofile);
    void region(File resource, Positions p, int width, int height, String tofile);
}
