package com.crazyandcoder.android.module.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crazyandcoder.android.lib.common.data.ARouterPathConstant;
import com.crazyandcoder.android.lib.common.utils.log.CrazyLog;


/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterPathConstant.Home.PAGE_HOME)
public class HomeFragment extends Fragment {

    public HomeFragment() {
        CrazyLog.d("据香港“东网”报道，当地时间5月26日，港澳知名爱国企业家、第9届至第11届全国政协常委何鸿燊逝世，享年98岁。\n");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment_blank, container, false);
    }
}
