package xyz.wongs.drunkard.common.core.page;


import xyz.wongs.drunkard.base.utils.ServletUtils;
import xyz.wongs.drunkard.war3.constant.Constants;

/**
 * @ClassName TableSupport
 * @Description 表格数据处理
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/9 15:53
 * @Version 1.0.0
*/
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
