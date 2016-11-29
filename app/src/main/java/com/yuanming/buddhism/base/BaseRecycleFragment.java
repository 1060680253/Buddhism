package com.yuanming.buddhism.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.interf.HttpRequestListener;
import com.yuanming.buddhism.interf.RecyclerItemClickListener;
import com.yuanming.buddhism.util.TDevice;
import com.yuanming.buddhism.widget.EmptyLayout;
import com.yuanming.buddhism.widget.LoadingFooter;
import com.yuanming.buddhism.widget.recycler.HeaderAndFooterRecyclerViewAdapter;
import com.yuanming.buddhism.widget.recycler.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * BaseRecyclerHeadFragment 抽象出来作为模板类
 * 采用泛型 约束条件是RecyclerView.Adapter
 * 作用在于：固定通用方法确定整体结构 然后具体算法实现在子类中实现扩展 还确保了子类的拓展
 * 定义成抽象类：既要约束子类的行为，又为子类提供公共功能
 * 所以：模板方法的基类只提供通用功能和确定骨架，而不应该决定逻辑跳转等具体子类的功能
 */
public abstract class BaseRecycleFragment
        <T extends BaseRecyclerAdapter, K_parent extends BaseEntity,K_son extends BaseEntity,T_presenter extends BasePresenter> extends BaseLazyFragment<T_presenter> implements SwipeRefreshLayout.OnRefreshListener,HttpRequestListener<K_parent>,RecyclerItemClickListener{
    public static final int STATE_NONE = 0; //无状态
    public static final int STATE_REFRESH = 1; //刷新状态
    public static final int STATE_LOADMORE = 2; //加载 还有更多状态
    public static final int STATE_NOMORE = 3; //加载，没有更多状态
    public int mState = STATE_NONE;
    protected int mCurrentPage = 0;
    @BindView(R.id.recycler_list)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swiperefreshlayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.error_layout)
    EmptyLayout mErrorLayout;
    protected T mAdapter;
    public static final int STATE_ERROR = 4; //加载，没有更多状态
    //能显示三种状态的 footView
    protected LoadingFooter mFooterView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }


    @Override
    public void initView(final View view) {
        if (isSetEmptyView()) {
            View emptyView = LayoutInflater.from(view.getContext()).inflate(
                    getEmptyView(), null);
            initEmptyView(emptyView);
            mErrorLayout.setEmptyView(emptyView);
        }
        if(getEmptyDrawable()!=-1){
            mErrorLayout.setEmptyDrawable(getEmptyDrawable());
        }
        mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        mSwipeRefreshLayout.setEnabled(false);
        if(isNeedRefresh()){
            mSwipeRefreshLayout.setEnabled(true);
        }
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                if(!TDevice.isConnected()){
                    mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                    return;
                }
                onRefresh();
            }
        });
        initRecyclerView(view);
        onRefresh();
    }

    protected boolean isNeedRefresh(){
        return true;
    }

    protected boolean isNeedLoad(){
        return true;
    }

    protected abstract void requestData();

    protected int getEmptyView() {
        return 0;
    }

    protected void initEmptyView(View view){}

    protected boolean isSetEmptyView(){
        return false;
    }

    protected View getFootView() {
        if (mFooterView == null) {
            mFooterView = new LoadingFooter(getActivity());
            if(getFooterEmptyView()!=null){
                mFooterView.setEmptyView(getFooterEmptyView());
            }
        }
        return mFooterView;
    }

    protected View getFooterEmptyView(){
        return null;
    }

    protected int getEmptyDrawable(){
        return -1;
    }

    protected View getHeadView(){
        return null;
    }


    protected abstract T getAdapter(); //这里初始化 adapter


    private RecyclerView.LayoutManager manager;
    protected RecyclerView.LayoutManager getLayoutManager() {
        if(manager==null){
            manager = new StaggeredGridLayoutManager(getLineNum(), StaggeredGridLayoutManager.VERTICAL);
        }
        return manager;
    }

    protected int getLineNum(){
        return 1;
    }
    private void initRecyclerView(final View view) {
        mAdapter = getAdapter();
        mAdapter.setOnItemClickListener(this);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(headAdapter);
        mRecyclerView.setLayoutManager(getLayoutManager());
        View headView = getHeadView();
        if (headView != null) {
            RecyclerViewUtils.addHearView(mRecyclerView, headView);
        }
        View footView = getFootView();
        if (footView != null) {
            RecyclerViewUtils.addFootView(mRecyclerView, footView);
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!isNeedLoad()){
                    return;
                }
                scrollChange();
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    if(isNeedRefresh()){
                        mSwipeRefreshLayout.setEnabled(getLayoutManager().getChildAt(0).getY()==0);
                    }
                    int size = (int) (mAdapter.getItemCount() * 0.8f);
                    if (mAdapter.getAdapterPosition() >= --size && mState!=STATE_LOADMORE&&mState!=STATE_NOMORE&&mState!=STATE_REFRESH) {
                        mCurrentPage++;
                        mState = STATE_LOADMORE;
                        mFooterView.setState(LoadingFooter.STATE_LOAD_MORE);
                        requestData();
                    }
                } else if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    //用户正在滑动
                    if(isNeedRefresh()){
                        mSwipeRefreshLayout.setEnabled(getLayoutManager().getChildAt(0).getY()==0);
                    }
                } else {
                    //惯性滑动
                }
            }
        });
    }

    protected void scrollChange(){

    }

    protected boolean isShowRefresh(){
        return true;
    }

    @Override
    public void onLazyLoad() {
        if(isShowRefresh()){
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        }else{
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            showWaitDialog();
        }
        mState = STATE_NONE;
        requestData();
    }

    public void onRefresh() {
        // 刷新
        if (mState == STATE_REFRESH || mRecyclerView == null) {
            return;
        }
        // 设置顶部正在刷新
        mRecyclerView.smoothScrollToPosition(0);
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
        mState = STATE_REFRESH;
        requestData();
    }

    public void listEmptyView() {
        mErrorLayout.setErrorType(EmptyLayout.NODATA);
    }

    /**
     * 是否需要隐藏listview，显示无数据状态
     */
    protected boolean needShowEmptyNoData() {
        return true;
    }

    /**
     * 设置顶部正在加载的状态
     */
    private void setSwipeRefreshLoadingState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
            // 防止多次重复刷新
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置顶部加载完毕的状态
     */
    private void setSwipeRefreshLoadedState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void onSuccess(K_parent result) {
        if (this == null) {
            return;
        }
        if (isAdded()) {
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            executeOnLoadDataSuccess(handleReciveData(result));
            executeOnLoadFinish();
        }
    }

    protected abstract List<K_son> handleReciveData(K_parent result);

    protected void executeOnLoadDataSuccess(List<K_son> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        if (mCurrentPage == 0) {
            mAdapter.clear();
        }
        int adapterState;
        if ((mAdapter.getItemCount() + data.size()) == 0) {
            adapterState = LoadingFooter.STATE_EMPTY_ITEM;
            mState = STATE_NOMORE;
        } else if (data.size() < getPageSize()) {
            adapterState = LoadingFooter.STATE_NO_MORE;
            mAdapter.notifyDataSetChanged();
            mState = STATE_NOMORE;
        } else {
            adapterState = LoadingFooter.STATE_OTHER;
            mState = STATE_NONE;
        }
        mFooterView.setState(adapterState);
        mAdapter.addListNotify(data);
        // 判断等于是因为最后有一项是listview的状态
        if (mAdapter.getItemCount() == 0) {

            if (needShowEmptyNoData()) {
                listEmptyView();
            } else {
                mFooterView.setState(LoadingFooter.STATE_NO_DATA);
                mAdapter.notifyDataSetChanged();
            }
        }
        if(!isNeedLoad()){
            mFooterView.setState(LoadingFooter.STATE_HIDE);
        }
    }

    protected int getPageSize(){
        return 10;
    }

    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();

    }

    @Override
    public void onError(int state,String error) {
        if(isAdded()&&isVisible()){
            TDevice.showSnackBar(mView, error);
        }
        executeOnLoadDataError(error);
    }


    protected void executeOnLoadDataError(String errorMsg) {
        if(mErrorLayout==null||!isVisible()){
            return;
        }
        executeOnLoadFinish();
        mState = STATE_ERROR;
        if ( mCurrentPage == 0) {
            if(!isShowRefresh()){
                hideWaitDialog();
            }
            if(mErrorLayout.isShown()){
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
            }else{
                mFooterView.setState(LoadingFooter.STATE_NETWORK_ERROR);
                mAdapter.notifyDataSetChanged();
            }
        }
        if (mCurrentPage != 0) {
            mCurrentPage--;
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            mFooterView.setState(LoadingFooter.STATE_NETWORK_ERROR);
            mAdapter.notifyDataSetChanged();
        }
    }
}
