package xyz.wongs.drunkard.base.persistence.mybatis.loader;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.thread.ThreadPoolUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author WCNGS@QQ.COM
 * @ClassName MapperAutoRefresh
 * @Description Mybatis的mapper文件中的sql语句被修改后, 只能重启服务器才能被加载, 非常耗时,所以就写了一个自动加载的类,
 * 配置后检查xml文件更改,如果发生变化,重新加载xml里面的内容.
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/17 10:29
 * @Version 1.0.0
 */
@Slf4j
@Component
public class MapperAutoRefresh implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static Properties prop = new Properties();
    /**
     * 是否启用Mapper刷新线程功能
     */
    private static boolean enabled;
    /**
     * 刷新启用后，是否启动了刷新线程
     */
    private static boolean refresh;

    /**
     * Mapper实际资源路径
     */
    private Set<String> location;
    /**
     * Mapper资源路径
     */
    private Resource[] mapperLocations;
    /**
     * MyBatis配置对象
     */
    private Configuration configuration;
    /**
     * 上一次刷新时间
     */
    private Long beforeTime = 0L;
    /**
     * 延迟刷新秒数
     */
    private static int delaySeconds;
    /**
     * 休眠时间
     */
    private static int sleepSeconds;
    /**
     * xml文件夹匹配字符串，需要根据需要修改
     */
    private static String mappingPath;

    private static final String XML_RESOURCE_PATTERN = "**/*.xml";
    private String basePackage = "/mapper";
    private static String FILE_NAME = "/conf/mybatis-refresh.properties";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    static {
        try {
            prop.load(MapperAutoRefresh.class.getResourceAsStream(FILE_NAME));
        } catch (Exception e) {
            log.error("Load mybatis-refresh “" + FILE_NAME + "” file error.");
        }

        enabled = ConstMapper.ENABLED_TRUE.equalsIgnoreCase(getPropString(ConstMapper.ENABLED));

        delaySeconds = getPropInt(ConstMapper.DELAY_SECONDS);
        sleepSeconds = getPropInt(ConstMapper.SLEEP_SECONDS);
        mappingPath = getPropString(ConstMapper.MAPPING_PATH);

        delaySeconds = delaySeconds == 0 ? 50 : delaySeconds;
        sleepSeconds = sleepSeconds == 0 ? 3 : sleepSeconds;
        mappingPath = StringUtils.isBlank(mappingPath) ? "mappings" : mappingPath;

        log.debug("[enabled] " + enabled);
        log.debug("[delaySeconds] " + delaySeconds);
        log.debug("[sleepSeconds] " + sleepSeconds);
        log.debug("[mappingPath] " + mappingPath);
    }


    @PostConstruct
    public void start() throws IOException{
        SqlSessionFactory sessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        Configuration configuration = sessionFactory.getConfiguration();
        this.configuration = configuration;
        mapperLocations = getResource(basePackage,XML_RESOURCE_PATTERN);
        exeTask();
    }

    /**
     * @Description 根据路径获取XML 的Resource
     * @param basePackage
     * @param pattern
     * @return javax.annotation.Resource[]
     * @throws
     * @date 20/11/17 10:48
     */
    public Resource[] getResource(String basePackage, String pattern) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+ ClassUtils.convertClassNameToResourcePath(applicationContext.getEnvironment().resolveRequiredPlaceholders(
                basePackage)) + "/" + pattern;
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources(packageSearchPath);
    }

    class MyBatisThreadRefresh implements Runnable{

        private MapperAutoRefresh mapperAutoRefresh;

        MyBatisThreadRefresh(MapperAutoRefresh mapperAutoRefresh){
            this.mapperAutoRefresh = mapperAutoRefresh;
        }

        @Override
        public void run() {
            // 解析资源
            if(null==location){
                location = Sets.newHashSet();
                log.debug("MapperLocation's length:" + mapperLocations.length);
                for (Resource mapperLocation : mapperLocations) {
                    String s = mapperLocation.toString().replaceAll("\\\\", "/");
                    s = s.substring("file [".length(), s.lastIndexOf(mappingPath) + mappingPath.length());
                    if (!location.contains(s)) {
                        location.add(s);
                        log.info("Location:" + s);
                    }
                }
                log.info("Locarion's size:" + location.size());
            }

            // 暂定时间
            try {
                TimeUnit.SECONDS.sleep(delaySeconds);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }

            refresh = true;
            log.info("========= Enabled refresh mybatis mapper =========");

            // 开始执行刷新操作
            while (true) {
                try {
                    for (String s : location) {
                        mapperAutoRefresh.refresh(s, beforeTime);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(sleepSeconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /** 执行资源刷新任务
     * @Description
     * @param
     * @return void
     * @throws
     * @date 20/11/17 11:04
     */
    public void exeTask() {
        if(null==mapperLocations || mapperLocations.length==0){
            return ;
        }
        beforeTime = System.currentTimeMillis();
        if(enabled){
            // 启动刷新线程
            final MapperAutoRefresh runnable = this;

            ExecutorService es = ThreadPoolUtils.doCreate(1,1,"Mybatis-Refresh");
            MyBatisThreadRefresh mtr = new MyBatisThreadRefresh(this);
            es.execute(mtr);
        }
    }

    /** 刷新资源的操作
     * @Description
     * @param filePath 资源的路径
     * @param beforeTime    开始时间
     * @return void
     * @throws
     * @date 20/11/17 11:06
     */
    public void refresh(String filePath,long beforeTime) throws FileNotFoundException {
        // 本次刷新时间
        Long refrehTime = System.currentTimeMillis();
        // 获取需要刷新的Mapper文件列表
        List<File> fileList = this.getRefreshFile(new File(filePath), beforeTime);

        if (fileList.isEmpty()) {
            return;
        }
        log.info("Refresh file: " + fileList.size());

        for (File file : fileList) {
            try{
                InputStream inputStream = new FileInputStream(file);
                String resource = file.getAbsolutePath();

                // 清理原有资源，更新为自己的StrictMap方便，增量重新加载
                String[] mapFieldNames = new String[]{
                        "mappedStatements", "caches",
                        "resultMaps", "parameterMaps",
                        "keyGenerators", "sqlFragments"
                };

                for (String fieldName : mapFieldNames){
                    Field field = configuration.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Map map = ((Map)field.get(configuration));
                    if (!(map instanceof StrictMap)){
                        Map newMap = new StrictMap(StringUtils.capitalize(fieldName) + "collection");
                        for (Object key : map.keySet()){
                            try {
                                newMap.put(key, map.get(key));
                            }catch(IllegalArgumentException ex){
                                newMap.put(key, ex.getMessage());
                            }
                        }
                        field.set(configuration, newMap);
                    }
                }

                // 清理已加载的资源标识，方便让它重新加载。
                Field loadedResourcesField = configuration.getClass().getDeclaredField("loadedResources");
                loadedResourcesField.setAccessible(true);
                Set loadedResourcesSet = ((Set)loadedResourcesField.get(configuration));
                loadedResourcesSet.remove(resource);

                //重新编译加载资源文件。
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration,
                        resource, configuration.getSqlFragments());
                xmlMapperBuilder.parse();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ErrorContext.instance().reset();
            }
            if (log.isDebugEnabled()) {
                log.info("Refresh file: " + file.getAbsolutePath());
                log.info("Refresh filename: " + file.getName());
            }

            if (!fileList.isEmpty()) {
                this.beforeTime = refrehTime;
            }
        }
    }

    /** 获取需要刷新的文件列表，返回 刷新文件列表
     * @Description
     * @param dir   目录
     * @param beforeTime    上次刷新时间
     * @return java.util.List<java.io.File>
     * @throws
     * @date 20/11/17 11:18
     */
    private List<File> getRefreshFile(File dir, Long beforeTime) {

        List<File> fileList = new ArrayList<File>();

        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isDirectory()) {
                    fileList.addAll(this.getRefreshFile(file, beforeTime));
                } else if (file.isFile()) {
                    if (this.checkFile(file, beforeTime)) {
                        fileList.add(file);
                    }
                } else {
                    log.error("Error file." + file.getName());
                }
            }
        }
        return fileList;
    }


    /**
     * 重写 org.apache.ibatis.session.Configuration.StrictMap 类
     * 来自 MyBatis3.4.0版本，修改 put 方法，允许反复 put更新。
     */
    public static class StrictMap<V> extends HashMap<String, V> {

        private static final long serialVersionUID = -4950446264854982944L;
        private String name;

        public StrictMap(String name, int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
            this.name = name;
        }

        public StrictMap(String name, int initialCapacity) {
            super(initialCapacity);
            this.name = name;
        }

        public StrictMap(String name) {
            super();
            this.name = name;
        }

        public StrictMap(String name, Map<String, ? extends V> m) {
            super(m);
            this.name = name;
        }

        @SuppressWarnings("unchecked")
        @Override
        public V put(String key, V value) {
            // ThinkGem 如果现在状态为刷新，则刷新(先删除后添加)
            if (MapperAutoRefresh.isRefresh()) {
                remove(key);
            }
            // ThinkGem end
            if (containsKey(key)) {
                throw new IllegalArgumentException(name + " already contains value for " + key);
            }
            if (key.contains(Constant.POINT)) {
                final String shortKey = getShortName(key);
                if (super.get(shortKey) == null) {
                    super.put(shortKey, value);
                } else {
                    super.put(shortKey, (V) new Ambiguity(shortKey));
                }
            }
            return super.put(key, value);
        }

        @Override
        public V get(Object key) {
            V value = super.get(key);
            if (value == null) {
                throw new IllegalArgumentException(name + " does not contain value for " + key);
            }
            if (value instanceof Ambiguity) {
                throw new IllegalArgumentException(((Ambiguity) value).getSubject() + " is ambiguous in " + name
                        + " (try using the full name including the namespace, or rename one of the entries)");
            }
            return value;
        }

        private String getShortName(String key) {
            final String[] keyparts = key.split("\\.");
            return keyparts[keyparts.length - 1];
        }

        protected static class Ambiguity {
            private String subject;

            public Ambiguity(String subject) {
                this.subject = subject;
            }

            public String getSubject() {
                return subject;
            }
        }

    }

    public static boolean isRefresh() {
        return refresh;
    }

    /** 判断文件是否需要刷新,需要刷新返回true，否则返回false
     * @Description
     * @param file  文件
     * @param beforeTime    上次刷新时间
     * @return boolean
     * @throws
     * @date 20/11/17 11:17
     */
    private boolean checkFile(File file, Long beforeTime) {
        if (file.lastModified() > beforeTime) {
            return true;
        }
        return false;
    }


    /**
     * 获取整数属性
     *
     * @param key
     * @return int
     * @throws
     * @Description
     * @date 20/11/17 10:37
     */
    private static int getPropInt(String key) {
        int i = 0;
        try {
            i = Integer.parseInt(getPropString(key));
        } catch (Exception e) {
        }
        return i;
    }

    /**
     * 获取字符串属性
     *
     * @param key
     * @return int
     * @throws
     * @Description
     * @date 20/11/17 10:37
     */
    private static String getPropString(String key) {
        return prop == null ? null : prop.getProperty(key);
    }

}
