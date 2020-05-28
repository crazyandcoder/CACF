package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.R;

/**
 * @ClassName: CrazyLoadFooter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:02 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:02 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyLoadFooter extends AbsLoadFooter {

    private View load, noMore;

    public CrazyLoadFooter(Context context) {
        super(context);
    }

    public CrazyLoadFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CrazyLoadFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_srv_load_footer, this, false);
        noMore = view.findViewById(R.id.tv_src_loadNoMore);
        load = view.findViewById(R.id.v_srv_loading);
        addView(view);
    }

    @Override
    public void loadBegin() {
        load.setVisibility(VISIBLE);
        noMore.setVisibility(GONE);
    }

    @Override
    public void loadEnd() {
        load.setVisibility(GONE);
        noMore.setVisibility(GONE);
    }

    @Override
    public void loadingNoMoreData() {
        load.setVisibility(GONE);
        noMore.setVisibility(VISIBLE);
    }

}
