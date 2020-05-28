package com.crazyandcoder.android.module.test;

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
public class BannerHome   {


    private String bannerName;
    private String bannerUrl;

    public BannerHome(String bannerName, String bannerUrl) {
        this.bannerName = bannerName;
        this.bannerUrl = bannerUrl;
    }

    public BannerHome( ) {
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
