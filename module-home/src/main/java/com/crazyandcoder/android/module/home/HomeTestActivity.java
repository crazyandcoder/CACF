package com.crazyandcoder.android.module.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crazyandcoder.android.lib.common.data.ARouterPathConstant;

@Route(path = ARouterPathConstant.ActivityName.ACTIVITY_HOME_TEST)
public class HomeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);
    }
}
