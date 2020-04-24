package cn.shimmer.appcore.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.shimmer.appcore.core.BaseApp;

public class GlideUtil {

    private static volatile GlideUtil instance;

    private GlideUtil() {
    }

    public static GlideUtil getInstance() {
        if (instance == null) {
            synchronized (GlideUtil.class) {
                if (instance == null) {
                    instance = new GlideUtil();
                    Glide.with(BaseApp.getInstance());
                }
            }
        }
        return instance;
    }

    public static void loadingImage(String url, ImageView loadImage) {
        Glide.with(BaseApp.getInstance())
                .load(url)
                .error(cn.shimmer.appcore.R.mipmap.empty)
                .into(loadImage);
    }
}
