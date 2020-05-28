package com.crazyandcoder.android.module.test;

import java.util.List;

/**
 * @ClassName: ActivityHomeItem
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:57 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:57 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ActivityHomeItem extends HomeItem {

    List<ActivityHome> list;

    public ActivityHomeItem(int type, int sort, List<ActivityHome> list) {
        super(type, sort);
        this.list = list;
    }

    public List<ActivityHome> getList() {
        return list;
    }

    public void setList(List<ActivityHome> list) {
        this.list = list;
    }

}

