package com.crazyandcoder.android.lib.base.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.crazyandcoder.android.lib.base.mvp.base.CrazyBaseMvpActivity;

/**
 * BaseActivity，各个module中的activity需要集成该 BaseActivity
 */
public abstract class CrazyBaseActivity extends CrazyBaseMvpActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //是否需要全屏显示
        if (needFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(getInflateView());
        initView();
    }

    /**
     * 是否需要全屏显示
     *
     * @return
     */
    protected boolean needFullScreen() {
        return false;
    }

    /**
     * 返回activity的布局
     *
     * @return
     */
    protected abstract int getInflateView();

    /**
     * 初始化View
     */
    protected abstract void initView();


}
