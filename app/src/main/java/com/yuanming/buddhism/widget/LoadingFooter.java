package com.yuanming.buddhism.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.app.App;
import com.yuanming.buddhism.util.TDevice;


/**
 * Created by cundong on 2015/10/9.
 * <p/>
 * ListView/GridView/RecyclerView 分页加载时使用到的FooterView
 */
public class LoadingFooter extends RelativeLayout {

    protected int mState = STATE_LOAD_MORE;
    private ProgressBar mLoadingProgress;
    private TextView mLoadingText;
    protected int _loadmoreText;
    protected int _loadFinishText;
    protected int _noDateText;
    private LinearLayout emptyContainer;

    public LoadingFooter(Context context) {
        this(context,null);
    }

    public LoadingFooter(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.view_cell_footer, this);
        mLoadingProgress = (ProgressBar)findViewById(R.id.progressbar) ;
        mLoadingText = (TextView)findViewById(R.id.text);
        emptyContainer = (LinearLayout)findViewById(R.id.ll_empty_container);
        _loadmoreText = R.string.loading;
        _loadFinishText = R.string.loading_no_more;
        _noDateText = R.string.loading_no_more;
        setState(STATE_OTHER);
    }

    private View mEmptyView;
    public void setEmptyView(View emptyView){
        this.mEmptyView = emptyView;
    }

    public int getState() {
        return mState;
    }

    public void setState(int status) {
        if (mState == status) {
            return;//如果状态已经相同 不做修改
        }
        mState = status;
        setVisibility(VISIBLE);
        emptyContainer.setVisibility(GONE);
        switch (status) {

            case STATE_LOAD_MORE:
                setOnClickListener(null);
                mLoadingText.setText(_loadmoreText);
                mLoadingProgress.setVisibility(VISIBLE);
                break;
            case STATE_NO_MORE:
                if(mEmptyView==null){
                    mLoadingProgress.setVisibility(View.GONE);
                    mLoadingText.setVisibility(VISIBLE);
                    mLoadingText.setText("没有数据");
                }else{
                    emptyContainer.setVisibility(VISIBLE);
                    mLoadingProgress.setVisibility(View.GONE);
                    mLoadingText.setVisibility(GONE);
                    emptyContainer.addView(mEmptyView);
                }
                break;
            case STATE_EMPTY_ITEM:
                mLoadingProgress.setVisibility(View.GONE);
                mLoadingText.setVisibility(VISIBLE);
                mLoadingText.setText(_noDateText);
                break;
            case STATE_NO_DATA:
                mLoadingProgress.setVisibility(View.GONE);
                mLoadingText.setVisibility(VISIBLE);
                mLoadingText.setText("没有数据");
                break;
            case STATE_NETWORK_ERROR:
                mLoadingProgress.setVisibility(View.GONE);
                mLoadingText.setVisibility(View.VISIBLE);
                if (TDevice.isConnected()) {
                    mLoadingText.setText("加载出错了");
                } else {
                    mLoadingText.setText("没有可用的网络");
                }
                break;
            default:
                setVisibility(GONE);
                break;
        }
    }

    public static final int STATE_EMPTY_ITEM = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_NO_MORE = 2;
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_HIDE = 4;
    public static final int STATE_NETWORK_ERROR = 5;
    public static final int STATE_OTHER = 6;

}