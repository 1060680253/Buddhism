package com.yuanming.buddhism.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/11/2.
 */

public class Constants {

    public static final String IS_LOGIN_TAG = "is_login_tag";
    public static final String BUNDLE_COMMON_PAGE = "bundle_common_page";
    public static final String BUNDLE_COMMON_EXTRA = "bundle_common_extra";

    public final static String IMG_PREFIX = "";
    public final static String IMG_CACHE_PATH_INVISBLE = "ym/imagecache/";
    public static final String HTTP_BASE_URL = "http://pre.phyt88.com/";
    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "ym"
            + File.separator + "ym_img" + File.separator;


    //http error state
    /**
     * -1:无网络连接
     * -2:暂无数据
     * -3:返回数据格式不正确
     */
}
