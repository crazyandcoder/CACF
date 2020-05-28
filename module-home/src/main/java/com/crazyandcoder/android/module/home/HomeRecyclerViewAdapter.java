package com.crazyandcoder.android.module.home;

import android.widget.ImageView;

import com.crazyandcoder.android.lib.common.utils.imageloader.ImageLoadUtils;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyViewHolder;
import com.crazyandcoder.android.module.test.ActivityHome;
import com.crazyandcoder.android.module.test.ActivityHomeItem;

import java.util.List;

/**
 * @ClassName: HomeRecyclerViewAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 8:08 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 8:08 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeRecyclerViewAdapter extends CrazyBaseAdapter<ActivityHome> {

    public HomeRecyclerViewAdapter(List<ActivityHome> list) {
        super(list, R.layout.home_recyclerview_item_item);
    }

    @Override
    public void onBindView(CrazyViewHolder holder, ActivityHome data, int i) {
        ImageView imageView = holder.getView(R.id.image);
        ImageLoadUtils.init(imageView.getContext()).setUrl(data.getActivityUrl()).setImageView(imageView).loadUrl();
    }
}
