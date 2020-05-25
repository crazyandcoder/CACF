package com.crazyandcoder.android.lib.network.utils;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @ClassName: HttpUtil
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 8:03 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 8:03 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HttpUtil {
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static String createUrlFromParams(String url, Map<String, String> params) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            if (url.indexOf('&') > 0 || url.indexOf('?') > 0) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            for (Map.Entry<String, String> urlParams : params.entrySet()) {
                String urlValues = urlParams.getValue();
                //对参数进行 utf-8 编码,防止头信息传中文
                //String urlValue = URLEncoder.encode(urlValues, UTF8.name());
                sb.append(urlParams.getKey()).append("=").append(urlValues).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (Exception e) {
            HttpLog.e(e.getMessage());
        }
        return url;
    }
}