package com.crazyandcoder.android.module.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.crazyandcoder.android.lib.common.utils.collections.CrazyList;
import com.crazyandcoder.android.lib.common.utils.imageloader.ImageLoadUtils;
import com.crazyandcoder.android.lib.common.utils.screen.CrazyDensityUtils;
import com.crazyandcoder.android.lib.common.utils.thread.CrazyCountDownTimer;
import com.crazyandcoder.android.lib.common.widget.gridview.GridSpacingItemDecoration;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyBaseMoreAdapter;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyRecyclerView;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyViewHolder;
import com.crazyandcoder.android.module.test.ActivityHome;
import com.crazyandcoder.android.module.test.ActivityHomeItem;
import com.crazyandcoder.android.module.test.BannerHomeItem;
import com.crazyandcoder.android.module.test.CardHomeItem;
import com.crazyandcoder.android.module.test.HomeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HomeAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 6:19 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 6:19 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeAdapter extends CrazyBaseMoreAdapter<HomeItem> {


    private Context context;
    private CountDownTimer countDownTimer;
    private List<ActivityHome> activityHomeList = new ArrayList<>();
    private HomeRecyclerViewAdapter adapter;
    private TextView countDownTimeTv;

    public HomeAdapter(List<HomeItem> list, Context context) {
        super(list, R.layout.home_recyclerview_item, R.layout.home_banner_item, R.layout.home_grid_item);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindView(CrazyViewHolder holder, HomeItem data, int i, int viewType) {
        switch (viewType) {
            //ActivityHome
            case 0:
                adapter = new HomeRecyclerViewAdapter(activityHomeList);
                countDownTimeTv= holder.getView(R.id.text);
                CrazyRecyclerView crazyRecyclerView = holder.getView(R.id.home_item_recyclerview);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                gridLayoutManager.setOrientation(GridLayout.VERTICAL);
                crazyRecyclerView.setLayoutManager(gridLayoutManager);
                crazyRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, CrazyDensityUtils.dp2px(context, 12), false));
                crazyRecyclerView.setAdapter(adapter);

                break;

            //BannerHome
            case 1:
                BannerHomeItem bannerHomeItem = (BannerHomeItem) data;
                ImageView bannerView = holder.getView(R.id.banner_image);
                ImageLoadUtils.init(context).setUrl((bannerHomeItem).getList().get(0).getBannerUrl()).setImageView(bannerView).loadUrl();
                break;

            //CardHome
            case 2:
                CardHomeItem item = (CardHomeItem) data;
                TextView textView = holder.getView(R.id.title_tv);
                textView.setText("" + item.getList().get(0).getCardName());
                break;


            default:
                break;
        }
    }

    public void updateTime(int times){
        if (countDownTimeTv!=null){
            countDownTimeTv.setText("倒计时："+times+" s");
        }
    }

    public void update5Shop(List<ActivityHome> list) {
        if (activityHomeList != null) {
            activityHomeList.clear();
        }
        activityHomeList.addAll(list);

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}
