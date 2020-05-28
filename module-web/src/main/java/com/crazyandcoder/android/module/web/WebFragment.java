package com.crazyandcoder.android.module.web;

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
@Route(path = ARouterPathConstant.Web.PAGE_WEB)
public class WebFragment extends Fragment {

    public WebFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.web_fragment_web, container, false);
    }
}
