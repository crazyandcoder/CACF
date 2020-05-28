package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @ClassName: CrazyBaseAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:15 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:15 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class CrazyBaseAdapter<T> extends RecyclerView.Adapter<CrazyViewHolder> {


    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_SECTION = 1;
    private int itemLayoutId, sectionLayoutId;
    private List<T> mList;


    public CrazyBaseAdapter(List<T> list, int itemLayoutId) {
        this.mList = list;
        this.itemLayoutId = itemLayoutId;
    }

    /**
     * 分组构造器
     *
     * @param itemLayoutId    正常ItemId
     * @param sectionLayoutId 分组ItemId
     */
    public CrazyBaseAdapter(List<T> list, int itemLayoutId, int sectionLayoutId) {
        this.mList = list;
        this.itemLayoutId = itemLayoutId;
        this.sectionLayoutId = sectionLayoutId;
    }

    /***
     * 获取position对应的item
     * @param position
     * @return
     */

    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }

    /**
     * 当有分组时，用于判断当前Item的类型
     *
     * @return 返回Item类型是正常类型还是分组类型
     */
    public int getItemType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public CrazyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CrazyViewHolder holder;
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent,
                    false);
            holder = CrazyViewHolder.getViewHolder(view);
            onCreateView(parent, holder);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(sectionLayoutId, parent,
                    false);
            holder = CrazyViewHolder.getViewHolder(view);
            onCreateSectionView(parent, holder);
        }
        return holder;
    }

    /**
     * 用于初始化view
     */
    public void onCreateView(ViewGroup parent, CrazyViewHolder holder) {
    }

    /**
     * 用于初始化分组的View
     */
    public void onCreateSectionView(ViewGroup parent, CrazyViewHolder holder) {
    }

    @Override
    public void onBindViewHolder(final CrazyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            onBindView(holder, mList.get(position), position);
        } else if (getItemViewType(position) == TYPE_SECTION) {
            onBindSectionView(holder, mList.get(position), position);
        }
    }

    public abstract void onBindView(CrazyViewHolder holder, T data, int i);

    public void onBindSectionView(CrazyViewHolder holder, T data, int position) {
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
