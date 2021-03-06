package com.yuanming.buddhism.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.util.TDevice;

public class EmptyLayout extends LinearLayout implements
        View.OnClickListener {// , ISkinUIObserver {

    public static final int HIDE_LAYOUT = 4;
    public static final int NETWORK_ERROR = 1;
    public static final int NETWORK_LOADING = 2;
    public static final int NODATA = 3;
    public static final int NO_LOGIN = 6;
    public static final int INVALID_LOGIN = 7;

    private LoadingView animProgress;
    private boolean clickEnable = true;
    private final Context context;
    public ImageView img;
    private OnClickListener listener;
    private int mErrorState;
    private RelativeLayout mLayout;
    private String strNoDataContent = "";
    private TextView tv;

    public EmptyLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private int mType = 0;
    public void setType(int type){
        mType = type;
        if(mType==1){
            if(mLayout!=null){
                mLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.segment));
            }
        }
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    ScrollView empty_contanier;
    private void init() {
        View view = View.inflate(context, R.layout.view_error_layout, null);
        img = (ImageView) view.findViewById(R.id.img_error_layout);
        tv = (TextView) view.findViewById(R.id.tv_error_layout);
        mLayout = (RelativeLayout) view.findViewById(R.id.pageerrLayout);
        if(mType==1){
            mLayout.setBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.segment));
        }
        empty_contanier = (ScrollView)view.findViewById(R.id.empty_contanier);
        animProgress = (LoadingView) view.findViewById(R.id.loading_process_dialog_progressBar);
        setBackgroundColor(-1);
        setOnClickListener(this);
        img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clickEnable) {
                    // setErrorType(NETWORK_LOADING);
                    if (listener != null)
                        setErrorType(NETWORK_LOADING);
                        listener.onClick(v);
                }
            }
        });
        addView(view);
    }
    private boolean isCustomeEmptyView = false;
    public void setEmptyView(View view){
        empty_contanier.addView(view);
        isCustomeEmptyView = true;
    }

    private int mEmptyDrawable = -1;
    public void setEmptyDrawable(int emptyDrawable){
        this.mEmptyDrawable = emptyDrawable;
    }

    public int getErrorState() {
        return mErrorState;
    }

    public boolean isLoadError() {
        return mErrorState == NETWORK_ERROR;
    }

    public boolean isLoading() {
        return mErrorState == NETWORK_LOADING;
    }

    @Override
    public void onClick(View v) {
        if (clickEnable) {
            // setErrorType(NETWORK_LOADING);
            if (listener != null)
                setErrorType(NETWORK_LOADING);
                listener.onClick(v);
        }
    }


    public void setErrorMessage(String msg) {
        tv.setText(msg);
    }

    public void setErrorImag(int imgResource) {
        try {
            img.setImageResource(imgResource);
        } catch (Exception e) {
        }
    }

    public void setErrorType(int i) {
        setVisibility(View.VISIBLE);
        tv.setVisibility(VISIBLE);
        switch (i) {
        case NETWORK_ERROR:
            mErrorState = NETWORK_ERROR;
            if (TDevice.isConnected()) {
                img.setBackgroundResource(R.drawable.data_error);
            } else {
                img.setBackgroundResource(R.drawable.network_error);
            }
            tv.setVisibility(GONE);
            img.setVisibility(View.VISIBLE);
            animProgress.setVisibility(GONE);
            clickEnable = true;
            break;
        case NETWORK_LOADING:
            mErrorState = NETWORK_LOADING;
            animProgress.setVisibility(VISIBLE);
            img.setVisibility(View.GONE);
            tv.setVisibility(GONE);
            clickEnable = false;
            break;
        case NODATA:
            mErrorState = NODATA;
            if(isCustomeEmptyView){
                empty_contanier.setVisibility(View.VISIBLE);
            }else{
                if(mEmptyDrawable==-1){
                    img.setBackgroundResource(R.drawable.no_data_error);
                }else{
                    img.setBackgroundResource(mEmptyDrawable);
                }
                img.setVisibility(View.VISIBLE);
                tv.setVisibility(GONE);
                clickEnable = true;
            }
            tv.setVisibility(GONE);
            animProgress.setVisibility(GONE);
            break;
        case HIDE_LAYOUT:
            setVisibility(View.GONE);
            break;
        case NO_LOGIN:
            mErrorState = NETWORK_LOADING;
            animProgress.setVisibility(VISIBLE);
            img.setVisibility(View.GONE);
            tv.setText("暂未登录");
            clickEnable = false;

            break;
        case INVALID_LOGIN:
            mErrorState = INVALID_LOGIN;
            animProgress.setVisibility(GONE);
            tv.setVisibility(GONE);
            img.setVisibility(VISIBLE);
            tv.setText("登录无效，点击验证");
            img.setBackgroundResource(R.drawable.data_error);
            clickEnable = true;
            break;
        default:
            break;
        }
    }

    public void setNoDataContent(String noDataContent) {
        strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE)
            mErrorState = HIDE_LAYOUT;
        super.setVisibility(visibility);
    }
}
