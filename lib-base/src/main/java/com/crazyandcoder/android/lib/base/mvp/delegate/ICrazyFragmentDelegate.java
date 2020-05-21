package com.crazyandcoder.android.lib.base.mvp.delegate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;

/**
 * @ClassName: ICrazyFragmentDelegate
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 11:08 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 11:08 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyFragmentDelegate<V extends ICrazyView, P extends ICrazyPresenter<V>> {

    /**
     * Must be called from {@link Fragment#onCreate(Bundle)}
     *
     * @param saved The bundle
     */
    void onCreate(Bundle saved);

    /**
     * Must be called from {@link Fragment#onDestroy()}
     */
    void onDestroy();

    /**
     * Must be called from {@link Fragment#onViewCreated(View, Bundle)}
     *
     * @param view               The inflated view
     * @param savedInstanceState the bundle with the viewstate
     */
    void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    /**
     * Must be called from {@link Fragment#onDestroyView()}
     */
    void onDestroyView();

    /**
     * Must be called from {@link Fragment#onPause()}
     */
    void onPause();

    /**
     * Must be called from {@link Fragment#onResume()}
     */
    void onResume();

    /**
     * Must be called from {@link Fragment#onStart()}
     */
    void onStart();

    /**
     * Must be called from {@link Fragment#onStop()}
     */
    void onStop();

    /**
     * Must be called from {@link Fragment#onActivityCreated(Bundle)}
     *
     * @param savedInstanceState The saved bundle
     */
    void onActivityCreated(Bundle savedInstanceState);

    /**
     * Must be called from {@link Fragment#onAttach(Activity)}
     *
     * @param activity The activity the fragment is attached to
     */
    @Deprecated
    void onAttach(Activity activity);

    /**
     * Must be called from {@link Fragment#onAttach(Context)}
     *
     * @param context The context the fragment is attached to
     */
    void onAttach(Context context);

    /**
     * Must be called from {@link Fragment#onDetach()}
     */
    void onDetach();

    /**
     * Must be called from {@link Fragment#onSaveInstanceState(Bundle)}
     */
    void onSaveInstanceState(Bundle outState);

}
