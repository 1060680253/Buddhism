package com.yuanming.buddhism.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanming.buddhism.interf.BaseView;
import com.yuanming.buddhism.interf.DialogControl;
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


}
