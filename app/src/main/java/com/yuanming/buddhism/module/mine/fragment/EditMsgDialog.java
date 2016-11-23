package com.yuanming.buddhism.module.mine.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yuanming.buddhism.R;

/**
 * Created by chenghuan on 2016/11/18.
 * on phyt company
 */

public class EditMsgDialog extends Dialog {

    private Context context;

    public EditMsgDialog(Context context)
    {
        this(context, R.style.Theme_Dialog_From_Bottom);

    }
    private String title,msg;
    public void show(String mtitle,String mMsg){
        if(tv_edit_title==null){
            title = mtitle;
            msg = mMsg;
        }else{
            tv_edit_title.setText(mtitle);
            edit_pwd.setText(mMsg);
        }

        show();
    }

    public EditMsgDialog(Context context, int theme)
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
        setContentView(R.layout.dialog_edit_msg);

        initViews();
        initValues();
    }
    private EditText edit_pwd;
    private TextView tv_edit_title;
    private void initViews(){
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        edit_pwd.setText(msg);
        tv_edit_title = (TextView)findViewById(R.id.tv_edit_title);
        tv_edit_title.setText(title);
    }

    private void initValues()
    {
        // 不能写在init()中
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;//让dialog的宽占满屏幕的宽
        lp.gravity = Gravity.BOTTOM;//出现在底部
        window.setAttributes(lp);

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(edit_pwd!=null){
                edit_pwd.requestFocus();
                InputMethodManager imm = (InputMethodManager) edit_pwd.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        }
    };

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHandler.sendEmptyMessageDelayed(0,400);
    }

}
