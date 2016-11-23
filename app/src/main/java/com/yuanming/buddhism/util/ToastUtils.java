package com.yuanming.buddhism.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.app.App;

/**
 * Created by chenghuan on 2016/7/26.
 * on phyt company
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showShort(String message) {
        showIconToast(App.getInstance().getContext(), message, R.drawable.jingg, R.color.white);
    }

    public static void showIconToast(Context context, String text, int iconId,
                                     int colorId) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.view_toast, null);
        ((TextView) layout).setText(text);
        ((TextView) layout).setCompoundDrawablePadding((int)TDevice.dpToPixel(12.0f));
        ((TextView) layout).setTextColor(ContextCompat.getColor(context,colorId));
        ((TextView) layout).setCompoundDrawablesWithIntrinsicBounds(iconId, 0,
                0, 0);
        if(mToast==null){
            mToast = new Toast(context);
            mToast.setGravity(Gravity.BOTTOM, 0, 152);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.setView(layout);
        mToast.show();
    }

}
