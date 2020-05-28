package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.content.Context;

/**
 * @ClassName: IRecyclerViewModule
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:18 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:18 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IRecyclerViewModule {
    AbsRefreshHeader getRefreshHeader(Context context);

    AbsLoadFooter getLoadingFooter(Context context);
}
