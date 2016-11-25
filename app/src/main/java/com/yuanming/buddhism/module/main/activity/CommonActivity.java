package com.yuanming.buddhism.module.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseActivity;
import com.yuanming.buddhism.constant.Constants;
import com.yuanming.buddhism.http.img.PictureLoader;
import com.yuanming.buddhism.module.mine.fragment.CollectionFragment;
import com.yuanming.buddhism.module.mine.fragment.FilterPopup;
import com.yuanming.buddhism.module.mine.fragment.MineMsgFragment;
import com.yuanming.buddhism.module.mine.fragment.SettingFragment;
import com.yuanming.buddhism.util.FileUtil;

import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public class CommonActivity extends BaseActivity {
    protected int mPageValue = -1;
    protected Bundle mBundle = null;
    protected WeakReference<Fragment> mFragment;

    public static void startActivity(Context context,CommonPage page){
        startActivity(context,page,null);
    }

    public static void startActivity(Context context,CommonPage page,Bundle bundle){
        Intent intent = new Intent(context,CommonActivity.class);
        intent.putExtra(Constants.BUNDLE_COMMON_PAGE,page.getValue());
        intent.putExtra(Constants.BUNDLE_COMMON_EXTRA,bundle);
        context.startActivity(intent);
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            mPageValue = savedInstanceState.getInt(Constants.BUNDLE_COMMON_PAGE);
            mBundle = savedInstanceState.getBundle(Constants.BUNDLE_COMMON_EXTRA);
        }
    }

    @Override
    protected boolean hasActionBar() {
        if(mPageValue == 10){
            return false;
        }else{
            return true;
        }
    }

    boolean isShow = true;
    @Override
    public void initView() {
        CommonPage page = CommonPage.getPageByValue(mPageValue);
        if (page == null) {
            throw new IllegalArgumentException("can not find page by value:"
                    + mPageValue);
        }
        if(mTvActionTitle!=null){
            mTvActionTitle.setText(page.getTitle());
            switch (page.getValue()){
                case 1:
                    getRightContiner().setVisibility(View.VISIBLE);
                    TextView textView = new TextView(this);
                    textView.setText(R.string.right_title_counts_log);
                    textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                    textView.setTextSize(17);
                    getRightContiner().addView(textView);
                    getRightContiner().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CommonActivity.startActivity(CommonActivity.this,CommonPage.COUNTLOGS);
                        }
                    });
                    break;
                case 6:
                    getRightContiner().setVisibility(View.VISIBLE);
                    TextView fiter = new TextView(this);
                    fiter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    fiter.setText("筛选");
                    fiter.setGravity(Gravity.CENTER);
                    fiter.setPadding(0,0,16,0);
                    fiter.setTextColor(ContextCompat.getColor(this, R.color.white));
                    fiter.setTextSize(17);
                    fiter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FilterPopup filterPopup = new FilterPopup(view.getContext(),FilterPopup.COLLECTION_FILTER);
                            filterPopup.show(view);
                        }
                    });
                    getRightContiner().addView(fiter,0);
                    TextView edit = new TextView(this);
                    edit.setPadding(16,0,0,0);
                    edit.setGravity(Gravity.CENTER);
                    edit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    edit.setText("编辑");
                    edit.setTextColor(ContextCompat.getColor(this, R.color.white));
                    edit.setTextSize(17);
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(mFragment!=null&&(mFragment.get() instanceof CollectionFragment)){
                                ((CollectionFragment)mFragment.get()).setShowDelet(isShow);
                            }
                            isShow = !isShow;
                        }
                    });
                    getRightContiner().addView(edit,1);
                    break;
                case 7:
                    getRightContiner().setVisibility(View.VISIBLE);
                    TextView textView1 = new TextView(this);
                    textView1.setText("+");
                    textView1.setTextColor(ContextCompat.getColor(this, R.color.white));
                    textView1.setTextSize(28);
                    getRightContiner().addView(textView1);
                    getRightContiner().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FilterPopup filterPopup = new FilterPopup(view.getContext(),FilterPopup.PLUS_FRIENDS);
                            filterPopup.show(view);
                        }
                    });
                    break;
            }
        }
        try {
            Fragment fragment = (Fragment) page.getClz().newInstance();
            if (mBundle != null) {
                fragment.setArguments(mBundle);
            }
            addFramentMethod(fragment);
            mFragment = new WeakReference<>(fragment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:" + mPageValue);
        }

    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private ArrayList<String> mSelectPath;

    public void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(me.nereo.multi_image_selector.R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {
            int maxNum = 1;
            MultiImageSelector selector = MultiImageSelector.create();
            selector.showCamera(true);
            selector.count(maxNum);
            selector.single();
//            selector.multi();
            selector.origin(mSelectPath);
            selector.start(this, REQUEST_IMAGE);
        }
    }
    private final int REQUEST_IMAGE = 1,REQUEST_CUT = 2;
    private void requestPermission(final String permission, String rationale, final int requestCode){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
            new AlertDialog.Builder(this)
                    .setTitle(me.nereo.multi_image_selector.R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(me.nereo.multi_image_selector.R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CommonActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(me.nereo.multi_image_selector.R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE:
                    mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                    StringBuilder sb = new StringBuilder();
                    for (String p : mSelectPath) {
                        sb.append(p);
                    }
                    String picPath = "file://" + sb.toString();
                    cutImg(picPath);
                    break;
                case REQUEST_CUT:
                    if (mFragment != null && (mFragment.get() instanceof MineMsgFragment)) {
                        Uri uri = data.getData();
                        MineMsgFragment mineMsgFragment = (MineMsgFragment) mFragment.get();
                        PictureLoader.getInstance().displayFromSDCard(uri.getPath(), mineMsgFragment.iv_user);
                    }
                    break;
            }
        }
    }

    private void cutImg(String path){
        Uri imageUri = Uri.parse(path);
        FileUtil.bigPhotoZoom(this, imageUri, 1, 1, 720, 720,REQUEST_CUT);
    }
}
