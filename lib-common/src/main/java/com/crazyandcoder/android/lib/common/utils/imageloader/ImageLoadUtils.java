package com.crazyandcoder.android.lib.common.utils.imageloader;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.crazyandcoder.android.lib.common.R;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName: ImageLoadUtils
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:06 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:06 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ImageLoadUtils {

    private Builder builder;

    /**
     * 初始化图片加载Utils
     *
     * @param context 上下文
     * @return
     */
    public static Builder init(Context context) {
        Builder builder = new Builder(context);
        return builder;
    }

    private ImageLoadUtils(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {

        /**
         * 上下文
         */
        Context context;

        /**
         * 图片BitmapDrawable
         */
        int drawable;

        /**
         * 网络图片Url地址
         */
        String url;

        /**
         * 拍照返回Uri地址
         */
        Uri uri;

        /**
         * 本地图片路径
         */
        String photoFilePath;

        /**
         * 是否需要缓存
         */
        boolean isNeedCache = true;

        /**
         * 默认底图
         */
        int placeholderImg = R.drawable.common_post_photogra;

        /**
         * 加载错误图片
         */
        int errorImg = R.drawable.common_post_photogra;

        /**
         * 记载图片回调
         */
        ImageLoadCallBack imageLoadCallBack;

        /**
         * 图片
         */
        ImageView imageView;

        /**
         * 是否圆形图标
         */
        boolean isCircle = false;

        /**
         * 是否圆角
         */
        boolean isRaadius = false;

        /**
         * 圆角度数
         */
        int radius = 4;

        Builder(Context context) {
            this.context = context;
        }

        public Builder setDrawable(int drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder setPhotoFilePath(String photoFilePath) {
            this.photoFilePath = photoFilePath;
            return this;
        }

        public Builder setCircle(boolean circle) {
            isCircle = circle;
            return this;
        }

        public Builder setRaadius(boolean raadius) {
            isRaadius = raadius;
            return this;
        }

        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder setNeedCache(boolean needCache) {
            isNeedCache = needCache;
            return this;
        }

        public Builder setPlaceholderImg(int placeholderImg) {
            this.placeholderImg = placeholderImg;
            return this;
        }

        public Builder setErrorImg(int errorImg) {
            this.errorImg = errorImg;
            return this;
        }

        public Builder setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder setImageLoadCallBack(ImageLoadCallBack imageLoadCallBack) {
            this.imageLoadCallBack = imageLoadCallBack;
            return this;
        }

        /**
         * 加载Drawable
         */
        public void loadDrawable() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadDrawable();
        }

        /**
         * 加载Url
         */
        public void loadUrl() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadUrl();
        }

        /**
         * 加载svg图片的Url
         */
        public void loadSvgUrl() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadSVGUrl();
        }


        /**
         * 加载本地图片文件
         */
        public void loadLocalImageFile() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadLocalImageFile();
        }

        /**
         * 加载Uri
         */
        public void loadUri() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadUri();
        }

        public interface ImageLoadCallBack {

            void onSuccess(Drawable bitmap);

            void onFail();
        }

        public void getBitmapForUrl(final OnGetBitmapListener onGetBitmapListener) {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.getBitmapForUrl(onGetBitmapListener);

        }

        public Drawable getDrawableForlocalPath() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            return imageLoadUtils.getDrawableForlocalPath();
        }
    }

    private Drawable getDrawableForlocalPath() {
        File file = new File(builder.photoFilePath);
        if (file != null && file.exists() && file.getName().endsWith(".png")) {
            return Drawable.createFromPath(builder.photoFilePath);
        } else {
            return null;
        }
    }

    private void getBitmapForUrl(final OnGetBitmapListener onGetBitmapListener) {
        Glide.with(builder.context).asBitmap().load(builder.url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                onGetBitmapListener.onBitmap(bitmap);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }

    /**
     * 加载Drawable资源
     */
    private void loadDrawable() {
        if (builder.imageView == null || builder.context == null) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            loadImage(builder.drawable);
        }
    }

    /**
     * 加载网络svg图片地址
     */
    private void loadSVGUrl() {
        if (builder.imageView == null || builder.context == null || TextUtils.isEmpty(builder
                .url)) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            RequestBuilder<PictureDrawable> requestBuilder = Glide.with(builder.context).as(PictureDrawable.class).load(builder.url);
            requestBuilder.into(builder.imageView);
        }
    }

    /**
     * 加载网络图片地址
     */
    private void loadUrl() {
        if (builder.imageView == null || builder.context == null || TextUtils.isEmpty(builder
                .url)) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            loadImage(builder.url);
        }
    }

    /**
     * 加载本地路径存储图片
     */
    private void loadLocalImageFile() {
        if (builder.imageView == null || builder.context == null) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            File file = new File(builder.photoFilePath);
            if (!file.exists()) {
                builder.imageLoadCallBack.onFail();
            } else {
                loadImage(file);
            }
        }
    }

    /**
     * 加载Uri
     */
    private void loadUri() {
        if (builder.imageView == null || builder.context == null || builder.uri == null) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            loadImage(builder.uri);
        }
    }

    /**
     * 加载图片并展示
     *
     * @param loadType 图片的类型(Drawable资源、网络图片、本地file、Uri)
     * @param <T>
     */
    @SuppressLint("CheckResult")
    private <T> void loadImage(T loadType) {
        RequestBuilder<Drawable> drawableRequestBuilder = Glide.with(builder.context).load
                (loadType);
        if (builder.isNeedCache) {
            drawableRequestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
        }
        if (builder.isCircle) {
            drawableRequestBuilder.transform(new GlideCircleTransform(builder.context));
        } else if (builder.isRaadius) {
            drawableRequestBuilder.transform(new CenterCrop(), new GlideRoundTransform(builder.context, builder
                    .radius));
        }
        drawableRequestBuilder.listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (builder.imageLoadCallBack != null) {
                    builder.imageLoadCallBack.onFail();
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (builder.imageLoadCallBack != null) {
                    builder.imageLoadCallBack.onSuccess(resource);
                }
                return false;
            }
        });
        drawableRequestBuilder.into(builder.imageView);
    }


    public interface OnGetBitmapListener {
        void onBitmap(Bitmap bitmap);
    }

    public static String getFPUriToPath(Context context, Uri uri) {
        try {
            List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
            if (packs != null) {
                String fileProviderClassName = FileProvider.class.getName();
                for (PackageInfo pack : packs) {
                    ProviderInfo[] providers = pack.providers;
                    if (providers != null) {
                        for (ProviderInfo provider : providers) {
                            if (uri.getAuthority().equals(provider.authority)) {
                                if (provider.name.equalsIgnoreCase(fileProviderClassName)) {
                                    Class<FileProvider> fileProviderClass = FileProvider.class;
                                    try {
                                        Method getPathStrategy = fileProviderClass.getDeclaredMethod("getPathStrategy", Context.class, String.class);
                                        getPathStrategy.setAccessible(true);
                                        Object invoke = getPathStrategy.invoke(null, context, uri.getAuthority());
                                        if (invoke != null) {
                                            String pathStrategyStringClass = FileProvider.class.getName() + "$PathStrategy";
                                            Class<?> PathStrategy = Class.forName(pathStrategyStringClass);
                                            Method getFileForUri = PathStrategy.getDeclaredMethod("getFileForUri", Uri.class);
                                            getFileForUri.setAccessible(true);
                                            Object invoke1 = getFileForUri.invoke(invoke, uri);
                                            if (invoke1 instanceof File) {
                                                String filePath = ((File) invoke1).getAbsolutePath();
                                                return filePath;
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
