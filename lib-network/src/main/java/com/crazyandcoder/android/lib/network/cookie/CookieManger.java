package com.crazyandcoder.android.lib.network.cookie;

import android.content.Context;

import java.util.ArrayList;
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
public class CookieManger implements CookieJar {

    private static Context mContext;
    private static PersistentCookieStore cookieStore;

    public CookieManger(Context context) {
        mContext = context;
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(mContext);
        }
    }

    public void addCookies(List<Cookie> cookies) {
        cookieStore.addCookies(cookies);
    }

    public void saveFromResponse(HttpUrl url, Cookie cookie) {
        if (cookie != null) {
            cookieStore.add(url, cookie);
        }
    }

    public PersistentCookieStore getCookieStore() {
        return cookieStore;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }


    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public void remove(HttpUrl url, Cookie cookie) {
        cookieStore.remove(url, cookie);
    }

    public void removeAll() {
        cookieStore.removeAll();
    }

}
