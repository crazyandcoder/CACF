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
public abstract class CrazyBaseMoreAdapter<T> extends RecyclerView.Adapter<CrazyViewHolder> {

    private int[] itemLayoutId;
    private List<T> mList;

    public CrazyBaseMoreAdapter(List<T> list, int... itemLayoutId) {
        this.mList = list;
        this.itemLayoutId = itemLayoutId;
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
    public CrazyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CrazyViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId[viewType],
                parent, false);
        holder = CrazyViewHolder.getViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final CrazyViewHolder holder, int position) {
        onBindView(holder, mList.get(position), position, getItemViewType(position));

    }

    public abstract void onBindView(CrazyViewHolder holder, T data, int i, int viewType);

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
