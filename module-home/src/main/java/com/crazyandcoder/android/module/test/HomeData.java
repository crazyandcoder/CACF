package com.crazyandcoder.android.module.test;

/**
 * @ClassName: HomeData
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/28 6:42 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/28 6:42 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeData extends HomeItem {

    private String title;
    private String url;

    public HomeData(int type, int sort, String title, String url) {
        super(type, sort);
        this.title = title;
        this.url = url;
    }

    public HomeData(int type, int sort) {
        super(type, sort);
    }

    public HomeData(int type, int sort, String title) {
        super(type, sort);
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HomeData{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
