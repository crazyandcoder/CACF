package com.crazyandcoder.android.lib.network.cache.converter;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * @ClassName: IDiskConverter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 4:46 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 4:46 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IDiskConverter {
    /**
     * 读取
     *
     * @param source 输入流
     * @param type  读取数据后要转换的数据类型
     *               这里没有用泛型T或者Tyepe来做，是因为本框架决定的一些问题，泛型会丢失
     * @return
     */
    <T> T load(InputStream source, Type type);

    /**
     * 写入
     *
     * @param sink
     * @param data 保存的数据
     * @return
     */
    boolean writer(OutputStream sink, Object data);
}
