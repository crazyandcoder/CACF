package com.crazyandcoder.android.lib.common.widget.recyclerview;

/**
 * @ClassName: ILoadListener
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:23 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:23 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ILoadListener {

    /**
     * 刷新的监听
     */
    void refresh();

    /**
     * 加载的监听
     */
    void loading();

}
