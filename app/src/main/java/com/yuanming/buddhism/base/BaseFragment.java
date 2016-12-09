package com.yuanming.buddhism.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.interf.BaseView;
import com.yuanming.buddhism.interf.DialogControl;
import com.yuanming.buddhism.interf.PermissionsResultListener;
import com.yuanming.buddhism.util.TDevice;
import com.yuanming.buddhism.widget.WaitDialog;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment{

    private Unbinder mUnbinder;
    protected T mPresenter;
    protected LayoutInflater mInflater;
    protected View mView;
    protected abstract int getLayoutId();
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        if(this instanceof BaseView){
            if(mPresenter==null){
                mPresenter = TDevice.getT(this);
            }
            if(mPresenter!=null){
                mPresenter.subscribe();
            }
        }
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null)
                parent.removeView(mView);
        } else {
            mView = inflater.inflate(getLayoutId(), container, false);
            inJectChildView(mView);
            mUnbinder = ButterKnife.bind(this, mView);
            initView(mView);
        }
        return mView;
    }

    protected Activity getFActivity(){
        Activity activity = getActivity();
        if(activity==null){
            activity = (Activity) mView.getContext();
        }
        return activity;
    }

    protected WaitDialog showWaitDialog() {
        FragmentActivity activity = getActivity();
        if (activity!=null&&(activity instanceof DialogControl)) {
            return ((DialogControl) activity).showWaitDialog();
        }
        return null;
    }

    protected void hideWaitDialog() {
        FragmentActivity activity = getActivity();
        if (activity!=null&&(activity instanceof DialogControl))  {
            ((DialogControl) activity).hideWaitDialog();
        }
    }

    protected void inJectChildView(View viewGroup){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
        initBundle(mBundle);
    }

    protected void initBundle(Bundle bundle){}

    protected void onSaveIntent(Bundle savedInstanceState) {
        if(getArguments() != null){
            Bundle bundle = getArguments();
            if(bundle != null){
                Set<String> keySet = bundle.keySet();
                for(String key : keySet) {
                    Object value = bundle.get(key);
                    if (value != null) {
                        if (value instanceof String) {
                            savedInstanceState.putString(key, (String) value);
                        } else if (value instanceof Integer) {
                            savedInstanceState.putInt(key, (Integer) value);
                        } else if (value instanceof Boolean) {
                            savedInstanceState.putBoolean(key, (Boolean) value);
                        } else if (value instanceof Float) {
                            savedInstanceState.putFloat(key, (Float) value);
                        } else if (value instanceof Long) {
                            savedInstanceState.putLong(key, (Long) value);
                        } else {
                            savedInstanceState.putString(key, value.toString());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null){
            mUnbinder.unbind();
            mUnbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.unsubscribe();//V层失去焦点 取消订阅
        }
    }

    public void initData() {

    }

    public void initView(View view) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        onSaveIntent(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        initBundle(savedInstanceState);
    }

    public void runOnUIThread(Runnable r) {
        final Activity activity = getActivity();
        if (activity != null && r != null)
            activity.runOnUiThread(r);
    }

    private PermissionsResultListener mListener;

    private int mRequestCode;

    protected void performRequestPermissions(String desc, String[] permissions, int requestCode, PermissionsResultListener listener) {
        if (permissions == null || permissions.length == 0) return;
        mRequestCode = requestCode;
        mListener = listener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkEachSelfPermission(permissions)) {// 检查是否声明了权限
                requestEachPermissions(desc, permissions, requestCode);
            } else {// 已经申请权限
                if (mListener != null) {
                    mListener.onPermissionGranted();
                }
            }
        } else {
            if (mListener != null) {
                mListener.onPermissionGranted();
            }
        }
    }

    private void requestEachPermissions(String desc, String[] permissions, int requestCode) {
        if (shouldShowRequestPermissionRationale(permissions)) {// 需要再次声明
            showRationaleDialog(desc, permissions, requestCode);
        } else {
            requestPermissions(permissions, requestCode);
        }
    }

    private void showRationaleDialog(String desc, final String[] permissions, final int requestCode) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.tips))
                .setMessage(desc)
                .setPositiveButton(getResources().getString(R.string.confrim), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermissions(permissions, requestCode);
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancle), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }


    /**
     * 再次申请权限时，是否需要声明
     *
     * @param permissions
     * @return
     */
    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        for (String permission : permissions) {
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检察每个权限是否申请
     *
     * @param permissions
     * @return true 需要申请权限,false 已申请权限
     */

    private boolean checkEachSelfPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == mRequestCode) {
            if (checkEachPermissionsGranted(grantResults)) {
                if (mListener != null) {
                    mListener.onPermissionGranted();
                }
            } else {// 用户拒绝申请权限
                if (mListener != null) {
                    mListener.onPermissionDenied();
                }
            }
        }
    }

    private boolean checkEachPermissionsGranted(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }



}
