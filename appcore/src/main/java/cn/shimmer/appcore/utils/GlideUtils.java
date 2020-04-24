package cn.shimmer.appcore.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import cn.shimmer.appcore.core.BaseApp;

public class GlideUtils {

    private static volatile GlideUtils instance;

    private RequestManager manager;

    private GlideUtils() {
        manager = Glide.with(BaseApp.getInstance());
    }

    public static GlideUtils getInstance() {
        if (instance == null) {
            synchronized (GlideUtils.class) {
                if (instance == null) {
                    instance = new GlideUtils();
                }
            }
        }
        return instance;
    }

    public RequestManager getManager() {
        return manager;
    }

    public void loadingImage(String url, ImageView loadImage) {
        Glide.with(BaseApp.getInstance())
                .load(url)
                .error(cn.shimmer.appcore.R.mipmap.empty)
                .into(loadImage);
    }
}
