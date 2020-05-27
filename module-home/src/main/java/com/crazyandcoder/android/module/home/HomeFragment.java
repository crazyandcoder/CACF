package com.crazyandcoder.android.module.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crazyandcoder.android.lib.base.base.CrazyBaseFragment;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.common.data.ARouterPathConstant;
import com.crazyandcoder.android.lib.common.utils.log.CrazyLog;


/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterPathConstant.Home.PAGE_HOME)
public class HomeFragment extends CrazyBaseFragment {

    public HomeFragment() {
        CrazyLog.d("据香港“东网”报道，当地时间5月26日，港澳知名爱国企业家、第9届至第11届全国政协常委何鸿燊逝世，享年98岁。\n");
    }


    @Override
    protected int getLayoutID() {
        return R.layout.home_fragment_blank;
    }

    @Override
    protected void initViews(View v) {

    }

    @Override
    protected void onFragemntHide() {
        super.onFragemntHide();
        CrazyLog.d("HomeFragment ---> onFragemntHide");
    }

    @Override
    protected void onFragmentShow() {
        super.onFragmentShow();
        CrazyLog.d("HomeFragment ---> onFragmentShow");
    }

    @Override
    public ICrazyPresenter createCrazyPresenter() {
        return null;
    }
}
