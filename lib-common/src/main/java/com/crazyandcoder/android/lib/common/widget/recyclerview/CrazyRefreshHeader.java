package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.R;
import com.crazyandcoder.android.lib.common.utils.screen.CrazyDensityUtils;

/**
 * @ClassName: CrazyRefreshHeader
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:28 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:28 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyRefreshHeader extends AbsRefreshHeader {

    private RotateAnimation upAnim, downAnim;
    private boolean isUp = true;
    private AutoPlayView playView;
    private ImageView iv_head_people_anim;
    private AnimationDrawable animation_people;
    private int width;
    private int height;

    public CrazyRefreshHeader(Context context) {
        super(context);
    }

    public CrazyRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CrazyRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_srv_refresh_header, this, false);


        width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        height = view.getMeasuredHeight();
        width = view.getMeasuredWidth();
        playView = (AutoPlayView) view.findViewById(R.id.playView);
        iv_head_people_anim = (ImageView) view.findViewById(R.id.iv_head_people_anim);
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
            case PREPARE_REFRESH:
                break;
            default:
                break;
        }
    }

    @Override
    public int getRefreshHeight() {
        return CrazyDensityUtils.dp2px(getContext(), 55);
    }
}
