package com.crazyandcoder.android.module.test;

import java.util.Objects;

/**
 * @ClassName: HomeItem
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:51 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:51 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeItem implements Comparable<HomeItem> {

    private int type;
    private int sort;

    public HomeItem(int type, int sort) {
        this.type = type;
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeItem homeItem = (HomeItem) o;
        return type == homeItem.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public int compareTo(HomeItem b) {
        if (b.sort > sort) {
            return 1;
        } else {
            return -1;
        }
    }

}
