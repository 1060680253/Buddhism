package com.yuanming.buddhism.http.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.constant.Constants;
import com.yuanming.buddhism.util.StringUtils;

import java.io.File;

public class PictureLoader {

    private static PictureLoader instance;

    public static PictureLoader getInstance() {
        if (instance == null) {
            instance = new PictureLoader();
        }
        return instance;
    }
/*
    String imageUri = "http://site.com/image.png"; // from Web
    String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
    String imageUri = "content://media/external/audio/albumart/13"; // from content provider
    String imageUri = "assets://image.png"; // from assets
    String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)*/

    public void displayFromDrawable(int imageId, ImageView imageView) {
        getImageLoader().displayImage("drawable://" + imageId,
                imageView,getRoundOptions(R.drawable.default_img));
    }

    public void displayFromDrawable(int imageId, ImageView imageView,ImageLoadingListener imageLoadingListener) {
        getImageLoader().displayImage("drawable://" + imageId,
                imageView,getOptions(R.drawable.default_img),imageLoadingListener);
    }

    public void displayFromSDCard(String filepath, ImageView imageView) {
        getImageLoader().displayImage("file://" + filepath,
                imageView,getRoundOptions(R.drawable.default_img));
    }

    public void displayFromContentProvider(String imageUri, ImageView imageView) {
        getImageLoader().displayImage("content://" + imageUri,
                imageView,getRoundOptions(R.drawable.default_img));
    }

    public void displayFromAssets(String imageUri, ImageView imageView) {
        getImageLoader().displayImage("assets://" + imageUri,
                imageView,getRoundOptions(R.drawable.default_img));
    }

    public void displayImgOriginal(ImageView mImageView, String urlImage) {
        getImageLoader().displayImage(urlImage, mImageView, getOptions(R.drawable.default_img));
    }

    public void displayImgOriginal(ImageView mImageView, String urlImage, ImageLoadingListener imageLoadingListener) {
        getImageLoader().displayImage(urlImage, mImageView, getOptions(R.drawable.default_img),imageLoadingListener);
//        getImageLoader().cancelDisplayTask();
    }

    public void displayImg(ImageView mImageView, String urlImage) {
        urlImage = Constants.HTTP_BASE_URL+urlImage;
        displayImg(mImageView, urlImage, false);
    }

    public void displayImg(ImageView mImageView, String urlImage, boolean isRound) {
        if(isRound){
            displayImg(mImageView, urlImage, isRound, R.drawable.round_default_img);
        }else{
            displayImg(mImageView, urlImage, isRound, R.drawable.default_img);
        }

    }

    public void displayImg(ImageView mImageView, String urlImage, boolean isRound, int defaultImg) {
        String urlImg = urlImage;
        int defaultVar = defaultImg;
        if (urlImage != null) {
            if (urlImg.contains(";")) {
                urlImg = urlImg.substring(0, urlImg.indexOf(";"));
            }
        }
        DisplayImageOptions options;
        if (isRound) {
            options = getRoundOptions(defaultImg);
        } else {
            options = getOptions(defaultImg);
        }
        if (!StringUtils.isEmpty(urlImg)) {
            getImageLoader().displayImage(Constants.IMG_PREFIX + urlImg, mImageView, options);
        } else {
            if (defaultVar == R.drawable.default_img) {
                if (isRound) {
                    mImageView.setImageResource(R.drawable.round_default_img);
                } else {
                    mImageView.setImageResource(defaultVar);
                }
            } else {
                mImageView.setImageResource(defaultVar);
            }

        }
    }


    public void clearMemoryCache()     // 清除内存缓存
    {
        getImageLoader().clearMemoryCache();
    }

    public void clearDiskCache()     // 清除内存缓存
    {
        getImageLoader().clearDiskCache();
    }

    @SuppressWarnings("deprecation")
    public void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, Constants.DEFAULT_SAVE_IMAGE_PATH);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) // maxwidth, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//	               .discCacheFileCount(100) //缓存的文件数量   
                .discCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for releaseapp
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }

    private DisplayImageOptions options;

    public DisplayImageOptions getOptions(int defalutImg) {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(defalutImg)            //加载图片时的图片
                .showImageForEmptyUri(defalutImg)         //没有图片资源时的默认图片
                .showImageOnFail(defalutImg)              //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

    public DisplayImageOptions getRoundOptions(int defalutImg) {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(defalutImg)            //加载图片时的图片
                .showImageForEmptyUri(defalutImg)         //没有图片资源时的默认图片
                .showImageOnFail(defalutImg)              //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .displayer(new CircleBitmapDisplayer())
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

    private static ImageLoader imageLoader;

    public static ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = ImageLoader.getInstance();
        }
        return imageLoader;
    }
}
