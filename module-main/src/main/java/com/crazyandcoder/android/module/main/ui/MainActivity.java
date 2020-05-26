package com.crazyandcoder.android.module.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.crazyandcoder.android.lib.common.data.ARouterPathConstant;
import com.crazyandcoder.android.lib.common.widget.bottomtab.PageBottomTabView;
import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.NavigationController;
import com.crazyandcoder.android.lib.common.widget.bottomtab.item.AbsBaseTabItem;
import com.crazyandcoder.android.lib.common.widget.bottomtab.item.NormalItemView;
import com.crazyandcoder.android.lib.common.widget.viewpager.CustomNoTouchViewPager;
import com.crazyandcoder.android.module.main.R;
import com.crazyandcoder.android.module.main.adapter.MainPageFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterPathConstant.ActivityName.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {

    private CustomNoTouchViewPager mCustomNoTouchViewPager;
    private PageBottomTabView mPageBottomTabView;
    private MainPageFragmentAdapter mPageFragmentAdapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        mCustomNoTouchViewPager = (CustomNoTouchViewPager) findViewById(R.id.viewpager);
        mPageBottomTabView = (PageBottomTabView) findViewById(R.id.bottomTabView);

        initFragment();

        mPageFragmentAdapter = new MainPageFragmentAdapter(getSupportFragmentManager(), 3);
        mPageFragmentAdapter.setData(fragments);


        NavigationController mNavigationController = mPageBottomTabView.custom()
                .addItem(createTabItem(R.drawable.main_ic_restore_gray_24dp, R.drawable.main_ic_restore_teal_24dp, "首页"))
                .addItem(createTabItem(R.drawable.main_ic_nearby_gray_24dp, R.drawable.main_ic_nearby_teal_24dp, "附近"))
                .addItem(createTabItem(R.drawable.main_ic_favorite_gray_24dp, R.drawable.main_ic_favorite_teal_24dp, "收藏"))
                .build();

        mCustomNoTouchViewPager.setAdapter(mPageFragmentAdapter);
        mNavigationController.setupWithViewPager(mCustomNoTouchViewPager);

    }

    private AbsBaseTabItem createTabItem(int drawable, int selectedDrawable, String title) {
        NormalItemView itemView = new NormalItemView(this);
        itemView.initialize(drawable, selectedDrawable, title);
        itemView.setHasMessage(true);
        itemView.setMessageNumber(100);
        return itemView;

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        //通过ARouter 获取其他组件提供的fragment
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(ARouterPathConstant.FragmentName.FRAGMENT_HOME).navigation();
        Fragment webFragment = (Fragment) ARouter.getInstance().build(ARouterPathConstant.FragmentName.FRAGMENT_WEB).navigation();
        Fragment userFragment = (Fragment) ARouter.getInstance().build(ARouterPathConstant.FragmentName.FRAGMENT_USER).navigation();
        fragments.add(homeFragment);
        fragments.add(webFragment);
        fragments.add(userFragment);
    }
}
