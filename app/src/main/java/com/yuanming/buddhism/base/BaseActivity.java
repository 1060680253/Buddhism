package com.yuanming.buddhism.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.app.AppManager;
import com.yuanming.buddhism.interf.DialogControl;
import com.yuanming.buddhism.interf.PermissionsResultListener;
import com.yuanming.buddhism.util.TDevice;
import com.yuanming.buddhism.widget.WaitDialog;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Activity基类
 */
//SwipeBackActivity
public abstract class BaseActivity extends AppCompatActivity implements DialogControl {

    protected ActionBar mActionBar;
    protected Context mContext;
    private Unbinder mUnbinder;
    protected FragmentManager fragmentManager;
    protected FragmentTransaction ft;
    protected int level = 0;
    private SparseArray<Fragment> hashMapFragments ;
    protected boolean _isVisible = false;
    private WaitDialog _waitDialog;
    protected LayoutInflater mInflater;
    protected View actionBarView;
    protected TextView mTvActionTitle;
    @Override
    public String toString() {
        return getClass().getSimpleName() + " @" + Integer.toHexString(hashCode());
    }

    protected int getLayoutId(){
        return R.layout.fragment_container;
    }

    protected String getTAG(){
        return getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mContext = this;
        if(null != savedInstanceState){
            initBundle(savedInstanceState);
        }else{
            if(getIntent()!=null&&getIntent().getExtras()!=null){
                initBundle(getIntent().getExtras());
            }
        }
        onBeforeSetContentLayout();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        mActionBar = getSupportActionBar();
        mInflater = getLayoutInflater();
        if (hasActionBar()) {
            initActionBar(mActionBar);
        }else{
            if(mActionBar!=null){
                mActionBar.hide();
            }
        }
        initView();
        initData();
        _isVisible = true;
    }


    protected boolean hasActionBar(){
        return true;
    }

    protected void initActionBar(ActionBar actionBar){
        if (actionBar == null)
            return;
        if (!isActionBarCustom()) {
            mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            int layoutRes = getActionBarCustomView();
            actionBarView = inflateView(layoutRes == 0 ? R.layout.actionbar_custom_backtitle : layoutRes);

            View back = actionBarView.findViewById(R.id.iv_back);
            back.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TDevice.hideSoftKeyboard(getCurrentFocus());
                    onBackPressed();
                }
            });
            if (hasBackButton()) {
                if (back == null) {
                    throw new IllegalArgumentException("can not find R.id.btn_back in customView");
                }
                back.setVisibility(View.VISIBLE);
            } else {
                back.setVisibility(View.GONE);
            }
            mTvActionTitle = (TextView) actionBarView.findViewById(R.id.tv_actionbar_title);
            if (mTvActionTitle == null) {
                throw new IllegalArgumentException("can not find R.id.tv_actionbar_title in customView");
            }
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                mTvActionTitle.setText(titleRes);
            }
            ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(actionBarView, params);
            if (layoutRes == 0) {
                LinearLayout rightContanier = getRightContiner();
                if (hasRightArea()) {
                    rightContanier.setVisibility(View.VISIBLE);
                    initRightArea(rightContanier);
                } else {
                    rightContanier.setVisibility(View.GONE);
                }
            }
            mActionBar = actionBar;
        } else {
            mActionBar = actionBar;
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                actionBar.setTitle(titleRes);
            }
        }
    }

    protected LinearLayout getRightContiner(){
        return (LinearLayout) actionBarView.findViewById(R.id.ll_right);
    }

    protected void initRightArea(LinearLayout contanier){
        if(getRightTextResouce()!=-1){
            TextView textView = new TextView(this);
            textView.setText(getRightTextResouce());
            textView.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
            textView.setTextSize(17);
            contanier.addView(textView);
        }
    }

    protected int getRightTextResouce(){
        return -1;
    }

    protected boolean hasShareButton(){
        return  false;
    }

    protected boolean hasRightArea(){
        return false;
    }

    protected int getActionBarTitle(){
        return 0;
    }

    protected boolean hasBackButton(){
        return false;
    }

    public View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected int getActionBarCustomView() {
        return 0;
    }

    protected boolean isActionBarCustom() {
        return false;
    }

    protected void onBeforeSetContentLayout(){}

    //在bundle取数据一定在该方法中获取（一定）
    protected void initBundle(Bundle savedInstanceState) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        hideWaitDialog();
        AppManager.getAppManager().finishActivity(this);
        TDevice.hideSoftKeyboard(getCurrentFocus());
    }

    public void initView() {

    }

    public void initData() {

    }

    protected boolean isNeedSwipBack(){
        return true;
    }

    protected void onSaveIntent(Bundle savedInstanceState) {
        if(getIntent() != null){
            Bundle bundle = getIntent().getExtras();
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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        onSaveIntent(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initBundle(savedInstanceState);
    }

    public void addFramentMethod(Fragment fragment) {
        addFramentMethod(fragment, false);
    }

    public void addFramentMethod(Fragment fragment, boolean istrue) {
        addFramentMethod(fragment, istrue, R.id.container);
    }

    public void addFramentMethod(Fragment fragment, boolean ifHide, int LayoutId) {
        addFramentMethod(fragment, ifHide, LayoutId, R.anim.enter_in_right, R.anim.exit_in_left,
                R.anim.pop_enter_in_left, R.anim.pop_exit_in_right);
    }

    public void addFramentMethod(Fragment fragment, boolean ifHide, int LayoutId, int anim_arg0, int anim_arg1,
                                 int anim_arg2, int anim_arg3) {
        if(hashMapFragments==null){
            hashMapFragments = new SparseArray<>();
        }
        ft = fragmentManager.beginTransaction();
        if(!(fragmentManager.getFragments()==null||fragmentManager.getFragments().size()==0)){
             ft.setCustomAnimations(anim_arg0, anim_arg1, anim_arg2, anim_arg3);
        }
        if (level > 0) {
            if (ifHide) {
                for (int i = 0; i <= level - 1; i++) {
                    if (null != hashMapFragments.get(level - 1 - i)) {
                        if (hashMapFragments.get(level - 1 - i).isHidden()) {
                            break;
                        }
                        ft.hide(hashMapFragments.get(level - 1 - i));
                        break;
                    }
                }
            }
            addFragment(LayoutId,fragment);
            ft.addToBackStack(level + "").commitAllowingStateLoss();
        } else {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            addFragment(LayoutId,fragment);
            ft.commitAllowingStateLoss();
        }
        hashMapFragments.put(level++, fragment);
    }

    private void addFragment(int LayoutId,Fragment fragment){
        Fragment oldFragment = fragmentManager.findFragmentByTag(getTAG());
        if(fragment!=null&&oldFragment!=null){
            ft.remove(oldFragment);
        }
        if (fragment != null) {
            ft.add(LayoutId, fragment,getTAG());
        }else{
            if(oldFragment!=null){
                ft.replace(LayoutId, oldFragment,getTAG());
            }

        }
    }


    @Override
    public WaitDialog showWaitDialog() {
        if (_isVisible&&!isFinishing()) {
            if (_waitDialog == null) {
                _waitDialog = getWaitDialog(this);
            }
            if (_waitDialog != null) {
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    public WaitDialog getWaitDialog(Activity activity) {
        if(!_isVisible&&activity==null||activity.isFinishing()){
            return null;
        }
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null&&!isFinishing()) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    protected boolean isNeedRemoveFragment(){
        return true;
    }
    @Override
    public void onBackPressed() {
        if(isNeedRemoveFragment()){
            if(hashMapFragments==null||hashMapFragments.size()==0){

            }else{
                hashMapFragments.remove(level);
                level--;
            }
            super.onBackPressed();
        }else{
            finish();
        }

    }

        @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private PermissionsResultListener mListener;

    private int mRequestCode;

    /**
     *http://www.jianshu.com/p/aa1d4b4360ed
     *
     * performRequestPermissions(getString(R.string.permission_desc), new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION}
         , PER_REQUEST_CODE, new PermissionsResultListener() {
        @Override
        public void onPermissionGranted() {
        Toast.makeText(MainActivity.this, "已申请权限", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPermissionDenied() {
        Toast.makeText(MainActivity.this, "拒绝申请权限", Toast.LENGTH_LONG).show();
        }
        });
     * 其他 Activity 继承 BaseActivity 调用 performRequestPermissions 方法
     *
     * @param desc        首次申请权限被拒绝后再次申请给用户的描述提示
     * @param permissions 要申请的权限数组
     * @param requestCode 申请标记值
     * @param listener    实现的接口
     */
    protected void performRequestPermissions(String desc, String[] permissions, int requestCode, PermissionsResultListener listener) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
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

    /**
     * 申请权限前判断是否需要声明
     *
     * @param desc
     * @param permissions
     * @param requestCode
     */
    private void requestEachPermissions(String desc, String[] permissions, int requestCode) {
        if (shouldShowRequestPermissionRationale(permissions)) {// 需要再次声明
            showRationaleDialog(desc, permissions, requestCode);
        } else {
            ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
        }
    }

    /**
     * 弹出声明的 Dialog
     *
     * @param desc
     * @param permissions
     * @param requestCode
     */
    private void showRationaleDialog(String desc, final String[] permissions, final int requestCode) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.tips))
                .setMessage(desc)
                .setPositiveButton(getResources().getString(R.string.confrim), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
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
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
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
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 申请权限结果的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
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

    /**
     * 检查回调结果
     *
     * @param grantResults
     * @return
     */
    private boolean checkEachPermissionsGranted(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
