package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.R;

/**
 * @ClassName: AutoPlayView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:28 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:28 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AutoPlayView extends View {

    Drawable mDrawableLeft, mDrawableRight;
    int drawableWidth, drawableHeight;
    ValueAnimator valueAnimator;

    public AutoPlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDrawableLeft = getResources().getDrawable(R.drawable.common_refresh_city);
        drawableWidth = mDrawableLeft.getMinimumWidth();
        drawableHeight = mDrawableLeft.getMinimumHeight();
        mDrawableLeft.setBounds(0, 0, drawableWidth, drawableHeight);

        mDrawableRight = getResources().getDrawable(R.drawable.common_refresh_city);
        mDrawableRight.setBounds(drawableWidth, 0, drawableWidth * 2, drawableHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wdMode = MeasureSpec.getMode(widthMeasureSpec);
        int hdMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量布局大小，默认为图片的宽高
        if (wdMode == MeasureSpec.EXACTLY && hdMode == MeasureSpec.EXACTLY) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            int wd = MeasureSpec.makeMeasureSpec(drawableWidth, MeasureSpec.AT_MOST);
            int hd = MeasureSpec.makeMeasureSpec(drawableHeight, MeasureSpec.AT_MOST);
            setMeasuredDimension(wd, hd);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mDrawableLeft.draw(canvas);
        mDrawableRight.draw(canvas);
    }

    public void startPlay() {
        valueAnimator = ValueAnimator.ofInt(0, drawableWidth);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                scrollTo(value, 0);
            }
        });
        valueAnimator.start();
    }

    public void stopPlay() {
        if (valueAnimator != null && valueAnimator.isRunning() && valueAnimator.isStarted()) {
            valueAnimator.cancel();
        }
    }
}

