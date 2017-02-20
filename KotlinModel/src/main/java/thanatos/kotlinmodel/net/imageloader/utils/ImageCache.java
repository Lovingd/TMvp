package thanatos.kotlinmodel.net.imageloader.utils;

import android.graphics.Bitmap;

/**
 * 缓存接口
 */

public interface ImageCache {

    Bitmap getCache(String path);
    void putCache(String path, Bitmap bitmap);
}
