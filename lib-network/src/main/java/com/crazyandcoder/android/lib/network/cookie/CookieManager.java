package com.crazyandcoder.android.lib.network.cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @ClassName: CookieManager
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/22 5:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/22 5:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CookieManager implements CookieJar {
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
}
