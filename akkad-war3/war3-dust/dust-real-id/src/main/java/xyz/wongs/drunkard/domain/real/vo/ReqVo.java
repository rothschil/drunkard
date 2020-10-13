package xyz.wongs.drunkard.domain.real.vo;

import java.io.Serializable;

/**
 * @ClassName ReqVo
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/17 11:45
 * @Version 1.0.0
*/
public class ReqVo {

    /**
     * requestObjects : {"gmsfhm":"340122198510270915","xm":"王勇"}
     */
    private RequestObjectsBean requestObjects;

    public RequestObjectsBean getRequestObjects() {
        return requestObjects;
    }

    public void setRequestObjects(RequestObjectsBean requestObjects) {
        this.requestObjects = requestObjects;
    }

    public static class RequestObjectsBean implements Serializable {

        public RequestObjectsBean(String gmsfhm, String xm) {
            this.gmsfhm = gmsfhm;
            this.xm = xm;
        }

        /**
         * gmsfhm : 340122198510270915
         * xm : 王勇
         */

        private String gmsfhm;
        private String xm;

        public String getGmsfhm() {
            return gmsfhm;
        }

        public void setGmsfhm(String gmsfhm) {
            this.gmsfhm = gmsfhm;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }
    }
}
