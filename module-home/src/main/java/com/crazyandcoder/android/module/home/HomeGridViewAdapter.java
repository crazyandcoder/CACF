package com.crazyandcoder.android.module.home;

import android.widget.ImageView;
import android.widget.TextView;

import com.crazyandcoder.android.lib.common.utils.imageloader.ImageLoadUtils;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyViewHolder;
import com.crazyandcoder.android.module.test.CardHome;

import java.util.List;

/**
 * @ClassName: HomeGridViewAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 8:19 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 8:19 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeGridViewAdapter extends CrazyBaseAdapter<CardHome> {
    public HomeGridViewAdapter(List<CardHome> list) {
        super(list, R.layout.home_grid_item_item);
    }

    @Override
    public void onBindView(CrazyViewHolder holder, CardHome data, int i) {
        ImageView imageView = holder.getView(R.id.ivImg);
        TextView textView = holder.getView(R.id.tvTitle);
        textView.setText("" + data.getCardName());
        ImageLoadUtils.init(imageView.getContext()).setUrl(data.getCardUrl()).setImageView(imageView).loadUrl();
    }
}
