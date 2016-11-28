package com.yuanming.buddhism.widget.imagecycle;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.widget.indictor.CirclePagerIndicator;

import java.util.ArrayList;

/**
 * 广告图片自动轮播控件</br>
 * 
 * <pre>
 *   集合ViewPager和指示器的一个轮播控件，主要用于一般常见的广告图片轮播，具有自动轮播和手动轮播功能 
 *   使用：只需在xml文件中使用{@code <com.minking.imagecycleview.ImageCycleView/>} ，
 *   然后在页面中调用  {@link #setImageResources(ArrayList, ImageCycleViewListener) }即可!
 *   
 *   另外提供{@link #startImageCycle() } \ {@link #pushImageCycle() }两种方法，用于在Activity不可见之时节省资源；
 *   因为自动轮播需要进行控制，有利于内存管理
 * </pre>
 * 
 */
public class ImageCycleView extends LinearLayout {

	/**
	 * 上下文
	 */
	private Context mContext;

	/**
	 * 图片轮播视图
	 */
	private CycleViewPager mBannerPager = null;

	/**
	 * 图片滚动当前图片下标
	 */
	private int mImageIndex = 1;

	/**
	 * 手机密度
	 */
	private float mScale;

	public ImageCycleView(Context context) {
		super(context);
	}

	private SwipeRefreshLayout refreshLayout;
	public void setRefreshLayout(SwipeRefreshLayout swipeRefreshLayout){
		this.refreshLayout = swipeRefreshLayout;
	}
	private CirclePagerIndicator mIndicator;
	/**
	 * @param context
	 * @param attrs
	 */
	public ImageCycleView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContext = context;
		mScale = context.getResources().getDisplayMetrics().density;
		LayoutInflater.from(context).inflate(R.layout.view_banner_content, this);
		mBannerPager = (CycleViewPager) findViewById(R.id.pager_banner);
		mIndicator = (CirclePagerIndicator)findViewById(R.id.indicator);

		mBannerPager.addOnPageChangeListener(new GuidePageChangeListener());
		mBannerPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
						// 开始图片滚动
						startImageTimerTask();
						if(refreshLayout!= null){
							refreshLayout.setEnabled(true);
						}

						stopImageTimerTask();
						break;
					case MotionEvent.ACTION_CANCEL:
						if(refreshLayout!= null) {
							refreshLayout.setEnabled(true);
						}
						stopImageTimerTask();
						break;
					case MotionEvent.ACTION_MOVE:
						if(refreshLayout!= null) {
							refreshLayout.setEnabled(false);
						}
						stopImageTimerTask();
						break;
					default:
						// 停止图片滚动
						stopImageTimerTask();
						break;
				}
				return false;
			}
		});

	}
	private int total;
	public void setImageResources(ArrayList<ADInfo> infoList, ImageCycleViewListener imageCycleViewListener) {
		total = infoList.size();
		ImageCycleAdapter mAdvAdapter = new ImageCycleAdapter(mContext, infoList, imageCycleViewListener);
		mBannerPager.setAdapter(mAdvAdapter);
		mIndicator.bindViewPager(mBannerPager);
		startImageTimerTask();
	}


	/**
	 * 开始轮播(手动控制自动轮播与否，便于资源控制)
	 */
	public void startImageCycle() {
		startImageTimerTask();
	}

	/**
	 * 暂停轮播——用于节省资源
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 开始图片滚动任务
	 */
	private void startImageTimerTask() {
		stopImageTimerTask();
		// 图片每3秒滚动一次
		mHandler.postDelayed(mImageTimerTask, 3000);
	}

	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {

		@Override
		public void run() {
			if (mBannerPager != null) {
				// 下标等于图片列表长度说明已滚动到最后一张图片,重置下标
				if ((++mImageIndex) == total) {
					mImageIndex = 1;
				}
				mBannerPager.setCurrentItem(mImageIndex);
			}
		}
	};

	/**
	 * 轮播图片状态监听器
	 * 
	 * @author minking
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE) {
				startImageTimerTask(); // 开始下次计时
			}
			if(refreshLayout!=null){
				refreshLayout.setEnabled(state == ViewPager.SCROLL_STATE_IDLE);
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			if(refreshLayout!=null) {
				refreshLayout.setEnabled(true);
			}
			if (index == 0 || index == total) {
				return;
			}
			// 设置图片滚动指示器背景
			mImageIndex = index;
		}

	}

	private class ImageCycleAdapter extends PagerAdapter {

		/**
		 * 图片视图缓存列表
		 */
		private ArrayList<ImageView> mImageViewCacheList;

		/**
		 * 图片资源列表
		 */
		private ArrayList<ADInfo> mAdList = new ArrayList<>();

		/**
		 * 广告图片点击监听器
		 */
		private ImageCycleViewListener mImageCycleViewListener;

		private Context mContext;

		public ImageCycleAdapter(Context context, ArrayList<ADInfo> adList, ImageCycleViewListener imageCycleViewListener) {
			mContext = context;
			mAdList = adList;
			mImageCycleViewListener = imageCycleViewListener;
			mImageViewCacheList = new ArrayList<>();
		}

		@Override
		public int getCount() {
			return mAdList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String imageUrl = mAdList.get(position).getUrl();
			ImageView imageView = null;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				
			} else {
				imageView = mImageViewCacheList.remove(0);
			}
			// 设置图片点击监听
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(mImageCycleViewListener != null) {
						mImageCycleViewListener.onImageClick(mAdList.get(position), position, v);
					}
				}
			});
			imageView.setTag(imageUrl);
			container.addView(imageView);
			if(mImageCycleViewListener != null) {
				mImageCycleViewListener.displayImage(imageUrl, imageView);
			}
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			container.removeView(view);
			mImageViewCacheList.add(view);
		}

	}

	/**
	 * 轮播控件的监听事件
	 * 
	 * @author minking
	 */
	public interface ImageCycleViewListener {

		/**
		 * 加载图片资源
		 * 
		 * @param imageURL
		 * @param imageView
		 */
		void displayImage(String imageURL, ImageView imageView);

		/**
		 * 单击图片事件
		 * 
		 * @param postion
		 * @param imageView
		 */
		void onImageClick(ADInfo info, int postion, View imageView);
	}

}
