package com.crazyandcoder.android.module.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.module.test.HomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HomeDataRecyclerViewAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/28 6:46 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/28 6:46 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeDataRecyclerViewAdapter extends RecyclerView.Adapter<HomeDataRecyclerViewAdapter.HomeDataViewHolder> {

    private List<HomeData> homeDataList = new ArrayList<>();
    private Context context;

    public HomeDataRecyclerViewAdapter(List<HomeData> homeDataList, Context context) {
        this.homeDataList = homeDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeDataViewHolder(LayoutInflater.from(context).inflate(R.layout.home_title_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDataViewHolder holder, int position) {
        HomeData homeData=homeDataList.get(position);
        if (homeData!=null){
            holder.textView.setText(""+homeData.getTitle());
        }
    }


    @Override
    public int getItemCount() {
        return homeDataList.size();
    }

    public class HomeDataViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public HomeDataViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.title);
        }
    }
}
