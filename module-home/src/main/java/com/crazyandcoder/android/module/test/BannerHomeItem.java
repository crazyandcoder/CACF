package com.crazyandcoder.android.module.test;

import java.util.List;

/**
 * @ClassName: BannerHomeItem
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BannerHomeItem extends HomeItem {

    List<BannerHome> list;

    public BannerHomeItem(int type, int sort, List<BannerHome> list) {
        super(type, sort);
        this.list = list;
    }

    public List<BannerHome> getList() {
        return list;
    }

    public void setList(List<BannerHome> list) {
        this.list = list;
    }
}
