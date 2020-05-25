package com.crazyandcoder.android.lib.network.cache.converter;

import com.crazyandcoder.android.lib.network.utils.HttpLog;
import com.crazyandcoder.android.lib.network.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ConcurrentModificationException;

/**
 * @ClassName: GsonDiskConverter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 4:55 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 4:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GsonDiskConverter implements IDiskConverter {
    private Gson gson = new Gson();

    public GsonDiskConverter() {
        this.gson = new Gson();
    }

    public GsonDiskConverter(Gson gson) {
        Utils.checkNotNull(gson, "gson ==null");
        this.gson = gson;
    }

    @Override
    public <T> T load(InputStream source, Type type) {
        T value = null;
        try {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            JsonReader jsonReader = gson.newJsonReader(new InputStreamReader(source));
            value = (T) adapter.read(jsonReader);
            //value = gson.fromJson(new InputStreamReader(source), type);
        } catch (JsonIOException | IOException | ConcurrentModificationException | JsonSyntaxException e) {
            HttpLog.e(e.getMessage());
        } catch (Exception e) {
            HttpLog.e(e.getMessage());
        } finally {
            Utils.close(source);
        }
        return value;
    }

    @Override
    public boolean writer(OutputStream sink, Object data) {
        try {
            String json = gson.toJson(data);
            byte[] bytes = json.getBytes();
            sink.write(bytes, 0, bytes.length);
            sink.flush();
            return true;
        } catch (JsonIOException | JsonSyntaxException | ConcurrentModificationException | IOException e) {
            HttpLog.e(e.getMessage());
        } catch (Exception e) {
            HttpLog.e(e.getMessage());
        } finally {
            Utils.close(sink);
        }
        return false;
    }
}
