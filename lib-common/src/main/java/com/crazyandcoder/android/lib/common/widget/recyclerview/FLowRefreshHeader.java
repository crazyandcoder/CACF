package com.crazyandcoder.android.lib.common.widget.recyclerview;

/**
 * @ClassName: FLowRefreshHeader
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:49 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:49 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazyandcoder.android.lib.common.R;
import com.crazyandcoder.android.lib.common.utils.rxjava.RxJavaUtils;
import com.crazyandcoder.android.lib.common.utils.screen.CrazyDensityUtils;

import io.reactivex.functions.Consumer;


/**
 * 功能：SRecyclerView默认的刷新头部,信息流页面,刷新玩有提示
 */
public class FLowRefreshHeader extends AbsRefreshHeader {

    private RotateAnimation upAnim, downAnim;
    private boolean isUp = true;
    private AutoPlayView playView;
    private ImageView iv_head_people_anim;
    private AnimationDrawable animation_people;
    private int width;
    private int height;
    private TextView tv_refresh_tip;
    private int start;
    private int end;
    final int tipsHeight = CrazyDensityUtils.dp2px(getContext(), 30);
    private String tipsText;

    public FLowRefreshHeader(Context context) {
        super(context);
    }

    public FLowRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FLowRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_flow_refresh_header, this,
                false);


        width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        height = view.getMeasuredHeight();
        width = view.getMeasuredWidth();
        playView = (AutoPlayView) view.findViewById(R.id.playView);
        iv_head_people_anim = (ImageView) view.findViewById(R.id.iv_head_people_anim);
        tv_refresh_tip = (TextView) view.findViewById(R.id.tv_refresh_tip);
        animation_people = (AnimationDrawable) iv_head_people_anim.getDrawable();
        addView(view);
        upAnim = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downAnim = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upAnim.setDuration(200);
        upAnim.setFillAfter(true);
        downAnim.setDuration(200);
        downAnim.setFillAfter(true);
    }

    @Override
    public void refresh(int state, int height) {
        switch (state) {
            case NORMAL:

                isUp = true;
                if (animation_people != null)
                    animation_people.stop();
                if (playView != null)
                    playView.stopPlay();
                break;
            case REFRESH:

                if (animation_people != null)
                    animation_people.start();
                if (playView != null)
                    playView.startPlay();
                break;
            case PREPARE_NORMAL:

                break;
            case PREPARE_REFRESH:
                break;
        }
    }

    @Override
    public int getRefreshHeight() {
        return CrazyDensityUtils.dp2px(getContext(), 55);
    }


    @Override
    protected void heightChangeAnim() {
        if (animator != null && animator.isRunning()) return;
        start = 0;
        end = 0;
        //需要高度动画的状态有：PREPARE_REFRESH，PREPARE_NORMAL，REFRESH
        if (currentState == REFRESH) {
            //刷新结束变为正常
            start = currentHeight;
            end = tipsHeight;//
        } else if (currentState == PREPARE_NORMAL) {
            //在低于刷新高度的位置松开拖动，准备回归初始状态
            start = currentHeight;
            end = 0;
        } else if (currentState == PREPARE_REFRESH) {
            //在高于刷新高度的位置松开拖动，准备开始刷新
            start = currentHeight;
            end = refreshHeight;
        } else if (currentState == NORMAL) {
            //代码调用开始刷新，准备开始刷新
            start = 0;
            end = refreshHeight;
        } else return;
        if (animator == null) {
            animator = ValueAnimator.ofInt(start, end);
            animator.setDuration(duration).setInterpolator(new DecelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int height = (int) animation.getAnimatedValue();
                    setHeight(height);
                    //高度自动更新的两个状态：刷新结束后的状态，未达到刷新高度而松手的状态
                    if (currentState == PREPARE_NORMAL || currentState == NORMAL) {
                        refresh(PREPARE_NORMAL, height);
                    }
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (end != 0) {
                        heightChangeAnimEnd();
                    } else {
                        tv_refresh_tip.setVisibility(GONE);
                        currentState = REFRESH;
                        heightChangeAnimEnd();
                    }
                }
            });
        } else {
            animator.setIntValues(start, end);
        }
        if (end == tipsHeight) {
            if (!TextUtils.isEmpty(tipsText)) {
                tv_refresh_tip.setText(tipsText);
            }
            tv_refresh_tip.setVisibility(VISIBLE);
            animator.start();
            RxJavaUtils.delay(1.3f, new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    start = tipsHeight;
                    end = 0;//
                    animator.setIntValues(start, end);
                    animator.start();
                }
            });

        } else {
            animator.start();
        }
    }

    public void refreshComplete(String text) {
        this.tipsText = text;
        refreshComplete();
    }
}
