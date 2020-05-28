package com.crazyandcoder.android.module.test;

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
public class ActivityHome {

    private String activityName;
    private String activityUrl;

    public ActivityHome(String activityName, String activityUrl) {
        this.activityName = activityName;
        this.activityUrl = activityUrl;
    }

    public ActivityHome() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }
}

