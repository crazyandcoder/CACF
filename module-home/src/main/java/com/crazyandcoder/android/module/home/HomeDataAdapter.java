package com.crazyandcoder.android.module.home;

import android.widget.ImageView;
import android.widget.TextView;

import com.crazyandcoder.android.lib.common.utils.imageloader.ImageLoadUtils;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recyclerview.CrazyViewHolder;
import com.crazyandcoder.android.module.test.HomeData;

import java.util.List;

/**
 * @ClassName: HomeDataAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/28 7:12 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/28 7:12 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeDataAdapter extends CrazyBaseAdapter<HomeData> {

    public HomeDataAdapter(List<HomeData> list) {
        super(list, R.layout.home_title_item);
    }

    @Override
    public void onBindView(CrazyViewHolder holder, HomeData data, int i) {
        ImageView imageView = holder.getView(R.id.banner_image);
        TextView textView = holder.getView(R.id.title);
        textView.setText(""+data.getTitle());
        ImageLoadUtils.init(imageView.getContext()).setUrl(data.getUrl()).setPlaceholderImg(R.drawable.people3).setImageView(imageView).loadUrl();
    }
}
