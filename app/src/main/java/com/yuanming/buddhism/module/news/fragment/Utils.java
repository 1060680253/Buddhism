package com.yuanming.buddhism.module.news.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanming.buddhism.R;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class Utils {

    public static void setupItem(final View view, final LibraryObject libraryObject) {
        final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        img.setImageResource(libraryObject.getRes());
    }

    public static class LibraryObject {

        private String mTitle;
        private int mRes;

        public LibraryObject(final int res, final String title) {
            mRes = res;
            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }
}
