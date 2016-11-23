package com.yuanming.buddhism.module.mine.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.yuanming.buddhism.R;

/**
 * Created by chenghuan on 2016/11/18.
 * on phyt company
 */

public class QrDialog extends Dialog {

    private Context context;

    public QrDialog(Context context)
    {
        this(context, R.style.Theme_Dialog_From_Bottom);

    }

    public QrDialog(Context context, int theme)
    {
        super(context, theme);
        // TODO Auto-generated constructor stub
        this.context = context;
        init();
    }

    private void init()
    {
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_qr);

        initViews();
        initValues();
    }
    private void initViews(){

    }

    private void initValues()
    {
        // 不能写在init()中
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;//出现在底部
        window.setAttributes(lp);

    }

}
