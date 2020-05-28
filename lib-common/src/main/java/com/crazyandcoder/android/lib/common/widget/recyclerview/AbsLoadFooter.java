package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
/**
 * @ClassName: AbsLoadFooter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:20 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:20 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public abstract class AbsLoadFooter extends LinearLayout {

    public AbsLoadFooter(Context context) {
        super(context);
    }

    public AbsLoadFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsLoadFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    final void initFooter() {
        setVisibility(GONE);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setGravity(Gravity.CENTER);
        init();
    }

    /**
     * 刷新结束后如果有需要，可重写此方法重置LoadFooter
     */
    public void reset() {
    }

    final void loading() {
        setVisibility(VISIBLE);
        loadBegin();
    }

    /**
     * 加载结束
     */
    final void loadingOver() {
        setVisibility(GONE);
        loadEnd();
    }

    /**
     * SRecyclerView的onDetachedFromWindow被调用，可能SRecyclerView所在的界面要被销毁，
     * 如果子类中有动画等未完成，可以重写此方法取消动画等耗时操作，避免造成内存泄露
     */
    public void srvDetachedFromWindow() {
    }

    /**
     * 加载尾部初始化
     */
    public abstract void init();

    /**
     * 开始加载
     */
    public abstract void loadBegin();

    /**
     * 加载结束
     */
    public abstract void loadEnd();

    /**
     * 没有更多的加载数据
     */
    public abstract void loadingNoMoreData();


}
