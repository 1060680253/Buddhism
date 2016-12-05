package com.yuanming.buddhism.widget.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by John on 2014/10/15.
 */
class Solid extends View {

    private Paint aboveWavePaint;
    private Paint blowWavePaint;
    private int mBlowWaveColor,mAboveWaveColor;
    private LinearGradient mShader ; //线性渐变
    private Paint mBlowWavePaint = new Paint();

    public Solid(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Solid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        setLayoutParams(params);
//        mBlowWavePaint.setColor(mBlowWaveColor);
        mBlowWavePaint.setStyle(Paint.Style.FILL);
        mBlowWavePaint.setAntiAlias(true);

    }
    public void setAboveWaveColor(int aboveWaveColor) {
        this.mAboveWaveColor = aboveWaveColor;
    }

    public void setBlowWaveColor(int blowWaveColor) {
        this.mBlowWaveColor = blowWaveColor;
    }

    public void setAboveWavePaint(Paint aboveWavePaint) {
        this.aboveWavePaint = aboveWavePaint;
    }

    public void setBlowWavePaint(Paint blowWavePaint) {
        this.blowWavePaint = blowWavePaint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mShader = new LinearGradient(0, 0, 0, getBottom(),mBlowWaveColor,mAboveWaveColor, Shader.TileMode.MIRROR) ;
        mBlowWavePaint.setShader(mShader) ;
        canvas.drawRect(getLeft(), 0, getRight(), getBottom(), mBlowWavePaint);

//        canvas.drawRect(getLeft(), 0, getRight(), getBottom(), aboveWavePaint);
    }
}
