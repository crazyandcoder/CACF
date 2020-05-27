package com.crazyandcoder.android.lib.base.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazyandcoder.android.lib.base.R;
import com.crazyandcoder.android.lib.base.mvp.base.CrazyBaseMvpFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class CrazyBaseFragment extends CrazyBaseMvpFragment {

    public CrazyBaseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutID(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onFragemntHide();
        } else {
            onFragmentShow();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 当前fragment被显示掉时调用该方法
     */
    protected void onFragmentShow() {

    }

    /**
     * 当前fragment被隐藏掉时调用该方法
     */
    protected void onFragemntHide() {

    }

    /**
     * <p>
     * #.获取layout id,主要给{@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}方法用
     * #.与{@link #getRootView()}选择实现一个方法即可
     * </p>
     *
     * @return 返回当前Fragment所需要显示的layout id
     */
    @LayoutRes
    protected abstract int getLayoutID();

    /**
     * 初始化视图
     * @param v
     */
    protected abstract void initViews(View v);

}
