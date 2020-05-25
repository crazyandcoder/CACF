package com.crazyandcoder.android.lib.network.cache.converter;

import com.crazyandcoder.android.lib.network.utils.HttpLog;
import com.crazyandcoder.android.lib.network.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * @ClassName: SerializableDiskConverter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 4:54 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 4:54 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SerializableDiskConverter implements IDiskConverter {

    @Override
    public <T> T load(InputStream source, Type type) {
        //序列化的缓存不需要用到clazz
        T value = null;
        ObjectInputStream oin = null;
        try {
            oin = new ObjectInputStream(source);
            value = (T) oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            HttpLog.e(e);
        } finally {
            Utils.close(oin);
        }
        return value;
    }

    @Override
    public boolean writer(OutputStream sink, Object data) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(sink);
            oos.writeObject(data);
            oos.flush();
            return true;
        } catch (IOException e) {
            HttpLog.e(e);
        } finally {
            Utils.close(oos);
        }
        return false;
    }

}