package com.crazyandcoder.android.module.main.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crazyandcoder.android.lib.base.mvp.base.CrazyBaseMvpActivity;
import com.crazyandcoder.android.lib.common.data.ARouterPathConstant;
import com.crazyandcoder.android.module.main.R;

/**
 * @author admin
 */
@Route(path = ARouterPathConstant.ROUTER_PTTH_TEST_ACTIVITY)
public class TestMvpActivity extends CrazyBaseMvpActivity<ITestView, TestPresenter> implements ITestView {

    private EditText mUserNameEt;
    private EditText mPasswordNameEt;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_test_mvp);

        mUserNameEt = (EditText) findViewById(R.id.username_et);
        mPasswordNameEt = (EditText) findViewById(R.id.password_et);
        mLoginBtn = (Button) findViewById(R.id.logint_btn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCrazyPresenter.login(mUserNameEt.getText().toString(), mPasswordNameEt.getText().toString());
            }
        });
    }

    @Override
    public TestPresenter createCrazyPresenter() {
        return new TestPresenter();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "login Success", Toast.LENGTH_LONG).show();
    }
}
