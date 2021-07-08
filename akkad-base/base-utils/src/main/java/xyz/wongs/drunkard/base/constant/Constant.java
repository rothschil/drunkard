package xyz.wongs.drunkard.base.constant;

/** 通用常量信息
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:00
 * @Version 1.0.0
*/
public class Constant {

    public static String DB_TYPE ="db.type";

    public static final String RANDOM_STR="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static final String IP_LOCAL_ADDRESS = "unknown";
    public static final String UNKNOWN = "unknown";
    public static final String HEADER_X_FORWARDED_FOR ="x-forwarded-for";

    /**
     * BEGIN  JAVA基础类型
     * **/
    public static final String BASIC_TYPE_INTEGER = "Integer";
    public static final String BASIC_TYPE_BIG_DECIMAL = "BigDecimal";
    public static final String BASIC_TYPE_LONG = "Long";
    public static final String BASIC_TYPE_DATE = "Date";
    public static final String BASIC_TYPE_INT = "int";

    /**
     *
     */
    public static final String POINT=".";
    /**
     * 问号
     */
    public static final String HF_QUESTION_MARK="?";

    /**
     * 逗号
     */
    public static final String HF_COMMA = ",";

    /**
     *
     */
    public static final String SLASH="/";

    /**
     *
     */
    public static final String STATUS_EFF = "Y";

    /**
     *
     */
    public static final String STATUS_EXP = "N";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 参数管理 cache name
     */
    public static final String SYS_CONFIG_CACHE = "sys-config";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache name
     */
    public static final String SYS_DICT_CACHE = "sys-dict";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     *  下划线
     */
    public static final String UNDERSCORE = "_";

    /**
     *  application/json
     */
    public static final String APPLICATION_JSON = "application/json";

    /**
     *  X-Requested-With
     */
    public static final String REQ_HEADER = "X-Requested-With";

    /**
     *  __ajax
     */
    public static final String REQ_HEADER_AJAX = "__ajax";

    /**
     *  accept
     */
    public static final String REQ_HEADER_ACCEPT = "accept";

    /**
     *  json
     */
    public static final String SUFFIX_JSON = ".json";

    /**
     *  xml
     */
    public static final String SUFFIX_XML = ".xml";


    public static final String RESULT="result";

    public static final String SUCCESS_SKILL ="恭喜您，秒杀成功。";
}
