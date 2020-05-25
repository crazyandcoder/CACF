package com.crazyandcoder.android.lib.network.exception;

/**
 * @ClassName: ServerException
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 6:26 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 6:26 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ServerException extends RuntimeException {
    private int errCode;
    private String message;

    public ServerException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.message = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}