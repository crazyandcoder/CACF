package com.crazyandcoder.android.module.test;

import java.util.List;

/**
 * @ClassName: CardHomeItem
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:55 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CardHomeItem extends HomeItem {

    List<CardHome> list;
    public CardHomeItem(int type, int sort, List<CardHome> list) {
        super(type, sort);
        this.list = list;
    }

    public List<CardHome> getList() {
        return list;
    }

    public void setList(List<CardHome> list) {
        this.list = list;
    }

}
