# 1. 背景

这几天碰巧手头上事情不是很多，开始整理宝宝们的图片，由于平常比较勤快，所以宝宝的图片太多，遇到好些重复的图片处理，具体实现可以查看
[手把手Java多线程实战(1)](../../war3-moon/README.md)，现在遇到另一个问题，就是在上传空间的过程中我想裁剪以及添加水印，有点犯难，那么多图片，挨个弄的话，这是要崩溃的节奏。

网上找了一圈，我看到一个开源插件 `thumbnailator` 比较符合我当前的需求，关于thumbnailator大家可以看下  [thumbnailator官网](https://github.com/coobird/thumbnailator)  ，先了解它的基本特点，准备开始撸。

# 2. 基本使用

 `thumbnailator` 使用非常简单，它核心方法都在它的 `Builder` 中。我们重点从它构造方法和  `Builder` 中一窥究竟。

## 2.1. 构造方法

 ![构造方法](https://abram.oss-cn-shanghai.aliyuncs.com/blog/drunkard/20210127102613.png)

## 2.2. 方法列表

 常用的有基本方法，详细见以下列表：

方法名 | 用途 |
-- | -- |
size(int width, int height) | 按照高 宽裁剪图片 |
width(int width) | 按照 宽裁剪图片 |
height(int height) | 按照 高裁剪图片 |
rotate(double angle) | 设置应用于缩略图的旋转量 |
scale(double scale) | 按照 比例 缩放 图片 |
scale(double scaleWidth, double scaleHeight) | 按照 高度 宽度 比例 缩放 图片 |
sourceRegion(Region sourceRegion) | 从指定的源区域 创建缩略图 |
sourceRegion(Position position, Size size) | 从指定的源区域 创建缩略图 |
sourceRegion(int x, int y, int width, int height) | 从指定的源区域 创建缩略图 |
watermark(BufferedImage image) | 设置水印的图像以应用于缩略图 |
watermark(BufferedImage image, float opacity) | 设置水印的图像和不透明度以应用于缩略图 |
watermark(Position position, BufferedImage image, float opacity) | 设置图像水印的不透明度和位置以应用于缩略图 |
watermark(Watermark w)| 设置水印以应用于缩略图 |

## 2.3. IDEA集成

我演示用的是利用 `SpringBoot` 集成的 `Web`，模块构建我选用的是 `gradle` 大家根据自己选择适用。

下来添加基本依赖即可。

~~~gradle
archivesBaseName = 'dior-thumbnailator'
dependencies {
    compile('org.springframework.boot:spring-boot-starter-web:2.3.4.RELEASE')
    compile group: 'net.coobird', name: 'thumbnailator', version: '0.4.13'
}

~~~

# 3. 案例

这一章节主要利用一些例子来展示在项目中如何使用，主要有缩放、添加水印、旋转、裁剪。

- 简单包装了下，对外开放能力，所以写一个接口。

~~~java
public interface IThumbnailsService {

    void changeSize(File resource, int width, int height, String tofile);

    void changeScale(File resource, double scale, String tofile);

    void watermark(File resource, Positions p, File shuiyin, float opacity, String tofile);

    void rotate(File resource, double rotate, String tofile);
    void region(File resource, Positions p, int width, int height, String tofile);
}
~~~

- 实现类

~~~java
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

~~~

# 4. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://img-blog.csdnimg.cn/img_convert/c256592943f0dad054561a6a6bbe419c.png)

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-dior/dior-thumbnailator)

[Gitee源码地址](https://gitee.com/rothschil/drunkard/tree/master/akkad-war3/war3-dior/dior-thumbnailator)
