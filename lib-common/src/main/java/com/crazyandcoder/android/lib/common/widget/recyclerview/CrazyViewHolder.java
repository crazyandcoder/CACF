package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.common.utils.imageloader.ImageLoadUtils;

/**
 * @ClassName: CrazyViewHolder
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:04 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:04 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    public View itemView;

    protected CrazyViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    public static CrazyViewHolder getViewHolder(View itemView) {
        CrazyViewHolder holder = (CrazyViewHolder) itemView.getTag();
        if (holder == null) {
            holder = new CrazyViewHolder(itemView);
            itemView.setTag(holder);
        }
        return holder;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        View childView = views.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            views.put(id, childView);
        }
        return (T) childView;
    }

    /**
     * 返回ItemView
     * @return
     */
    public View getItemView() {
        return itemView;
    }

    /**
     * 封装返回常用的控件
     * @param id
     * @return
     */
    public TextView getTextView(int id) {
        return getView(id);
    }

    public Button getButton(int id) {
        return getView(id);
    }

    public ImageView getImageView(int id) {
        return getView(id);
    }

    /**
     * 封装设置常用的控件
     * @param id
     * @param text
     * @return
     */
    public CrazyViewHolder setTextView(int id, CharSequence text) {
        getTextView(id).setText(text);
        return this;
    }

    public CrazyViewHolder setButton(int id, CharSequence title) {
        getButton(id).setText(title);
        return this;
    }

    public CrazyViewHolder setImageResource(int id, int resource) {
        getImageView(id).setImageResource(resource);
        return this;
    }


    public CrazyViewHolder setImageUrl(int id, String imageUrl) {
        ImageLoadUtils.init(getView(id).getContext()).setImageView(getImageView(id)).setUrl(imageUrl).loadUrl();
        return this;
    }

    /***
     * 默认圆角2dp
     * @param imageView
     * @param imageUrl
     * @return
     */
    public CrazyViewHolder setRoundImageUrl(ImageView imageView, String imageUrl, int dpRound) {
        ImageLoadUtils.init(imageView.getContext()).setImageView(imageView).setRaadius(true).setRadius(dpRound).setUrl
                (imageUrl).loadUrl();
        return this;

    }

    public CrazyViewHolder setRoundImageUrl(int id, String url) {
        return setRoundImageUrl(getImageView(id), url, 2);
    }

    public CrazyViewHolder setRoundImageUrl(ImageView imageView, String url) {
        return setRoundImageUrl(imageView, url, 80);
    }


    public CrazyViewHolder setVisible(int id, int visibility) {
        getView(id).setVisibility(visibility);
        return this;
    }

}
