package com.yuanming.buddhism.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import com.yuanming.buddhism.R.anim;
import com.yuanming.buddhism.R.styleable;

/**
 * Created by chenghuan on 2016/12/3.
 * on phyt company
 */

public class SwitcherView extends TextSwitcher implements ViewSwitcher.ViewFactory, View.OnTouchListener {
    private static final String TAG = "--------------";
    private Handler handler;
    private Timer timer;
    private ArrayList<String> dataSource;
    private int currentIndex;
    private int textSize;
    private static final int defaultTextSize = 16;
    private int textColor;
    private int timePeriod;
    private boolean flag;
    private TextView tView;
    TimerTask timerTask;

    public SwitcherView(Context context) {
        this(context, (AttributeSet)null);
    }

    public SwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.handler = new Handler();
        this.dataSource = new ArrayList();
        this.currentIndex = 0;
        this.textSize = 0;
        this.textColor = -16777216;
        this.timePeriod = 3000;
        this.flag = true;
        this.timerTask = new TimerTask() {
            public void run() {
                SwitcherView.this.handler.post(new Runnable() {
                    public void run() {
                        if(SwitcherView.this.flag) {
                            SwitcherView.this.updateTextSwitcher();
                        }

                    }
                });
            }
        };
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, styleable.SwitcherView);
        this.textColor = ta.getColor(styleable.SwitcherView_switcherTextColor, this.textColor);
        this.timePeriod = ta.getInt(styleable.SwitcherView_switcherRollingTime, this.timePeriod);
        this.textSize = ta.getDimensionPixelSize(styleable.SwitcherView_switcherTextSize, this.sp2px(16));
        Log.i("----", this.textSize + "");
        this.textSize = this.px2sp((float)this.textSize);
        Log.i("----", this.textSize + "");
        ta.recycle();
        this.setOnTouchListener(this);
    }

    public View makeView() {
        this.tView = new TextView(this.getContext());
        this.tView.setTextSize(2, (float)this.textSize);
        this.tView.setTextColor(this.textColor);
        this.tView.setSingleLine();
        this.tView.setPadding(10, 5, 10, 5);
        this.tView.setEllipsize(TextUtils.TruncateAt.END);
        return this.tView;
    }

    public void setResource(ArrayList<String> dataSource) {
        this.dataSource = dataSource;
    }

    private void updateTextSwitcher() {
        if(this.dataSource != null && this.dataSource.size() > 0) {
            this.setText((CharSequence)this.dataSource.get(this.currentIndex++));
            if(this.currentIndex > this.dataSource.size() - 1) {
                this.currentIndex = 0;
            }
        }

    }

    public void startRolling() {
        if(this.timer == null) {
            this.setFactory(this);
            this.setInAnimation(this.getContext(), anim.m_switcher_vertical_in);
            this.setOutAnimation(this.getContext(), anim.m_switcher_vertical_out);
            this.timer = new Timer();
            this.timer.schedule(this.timerTask, 0L, (long)this.timePeriod);
        }

    }

    public String getCurrentItem() {
        return this.dataSource != null && this.dataSource.size() > 0?(String)this.dataSource.get(this.getCurrentIndex()):"";
    }

    public int getCurrentIndex() {
        int index = this.currentIndex - 1;
        if(index < 0) {
            index = this.dataSource.size() - 1;
        }

        return index;
    }

    public void destroySwitcher() {
        this.handler.removeCallbacksAndMessages((Object)null);
        this.handler = null;
        if(this.timer != null) {
            this.timer.cancel();
            this.timerTask.cancel();
            this.timer = null;
            this.timerTask = null;
        }

        if(this.dataSource != null && this.dataSource.size() > 0) {
            this.dataSource.clear();
            this.dataSource = null;
        }

    }

    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == 0) {
            this.flag = false;
        }

        if(event.getAction() == 1) {
            this.flag = true;
        }

        return false;
    }

    public int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(2, (float)spVal, this.getResources().getDisplayMetrics());
    }

    public int dp2px(int dpVal) {
        return (int)TypedValue.applyDimension(1, (float)dpVal, this.getResources().getDisplayMetrics());
    }

    public int px2sp(float pxValue) {
        float fontScale = this.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5F);
    }
}
