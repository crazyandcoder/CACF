package com.crazyandcoder.android.module.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crazyandcoder.android.lib.common.data.ARouterPathConstant;


/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterPathConstant.FragmentName.FRAGMENT_USER)
public class MyFragment extends Fragment {

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_fragment_my, container, false);
    }
}
