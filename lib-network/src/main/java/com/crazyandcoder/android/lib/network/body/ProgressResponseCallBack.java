package com.crazyandcoder.android.lib.network.body;

/**
 * @ClassName: ProgressResponseCallBack
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 6:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 6:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ProgressResponseCallBack {
    /**
     * 回调进度
     *
     * @param bytesWritten  当前读取响应体字节长度
     * @param contentLength 总长度
     * @param done          是否读取完成
     */
    void onResponseProgress(long bytesWritten, long contentLength, boolean done);
}
