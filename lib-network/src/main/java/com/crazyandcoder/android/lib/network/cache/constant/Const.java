package com.crazyandcoder.android.lib.network.cache.constant;

/**
 * @ClassName: Const
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/22 4:52 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/22 4:52 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Const {

    /**
     * 默认的超时时间，ms
     */
    public static final int DEFAULT_TIME_MILLISECONDS = 60 * 1000;

    /**
     * 默认的失败重试次数
     */
    public static final int DEFAULT_RETRY_COUNT = 3;

    /**
     * 默认重试叠加时间
     */
    public static final int DEFAULT_RETRY_INCREASEDELAY = 0;

    /**
     * 默认重试延时
     */
    public static final int DEFAULT_RETRY_DELAY = 500;

    /**
     * 缓存过期时间，默认永久缓存
     */
    public static final int DEFAULT_CACHE_NEVER_EXPIRE = -1;


}
