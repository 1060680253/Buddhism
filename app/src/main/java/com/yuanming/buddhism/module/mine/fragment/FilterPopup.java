package com.yuanming.buddhism.module.mine.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.yuanming.buddhism.R;

public class FilterPopup extends PopupWindow {
	public final static int COLLECTION_FILTER = 0;
	public final static int PLUS_FRIENDS = 1;
	private Context mContext;

	public FilterPopup(Context context,int type) {
		// 设置布局的参数
		this(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,type);
	}

	public FilterPopup(Context context, int width, int height,int mType) {
		this.mContext = context;

		// 设置可以获得焦点
		setFocusable(true);
		// 设置弹窗内可点击
		setTouchable(true);
		// 设置弹窗外可点击
		setOutsideTouchable(true);

		// 设置弹窗的宽度和高度
		setWidth(width);
		setHeight(height);
		setAnimationStyle(R.style.popu_top_style);
		ColorDrawable cd = new ColorDrawable(0x000000);
		WindowManager.LayoutParams lp=((Activity)mContext).getWindow().getAttributes();
		lp.alpha = 0.4f;
		((Activity)mContext).getWindow().setAttributes(lp);
		setBackgroundDrawable(cd);
		View view;
		switch (mType){
			case COLLECTION_FILTER:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.popu_filter_collection, null);
				break;
			case PLUS_FRIENDS:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.popu_my_friends, null);
				break;
			default:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.popu_filter_collection, null);
				break;

		}
		setContentView(view);

	}

	@Override
	public void dismiss() {
		super.dismiss();
		WindowManager.LayoutParams lp=((Activity)mContext).getWindow().getAttributes();
		lp.alpha = 1f;
		((Activity)mContext).getWindow().setAttributes(lp);
	}

	/**
	 * 显示弹窗列表界面
	 */
	public void show(View c) {
		showAsDropDown(c);
	}
}
