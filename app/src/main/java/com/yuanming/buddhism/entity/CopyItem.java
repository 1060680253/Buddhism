package com.yuanming.buddhism.entity;

import com.yuanming.buddhism.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenghuan on 2016/12/16.
 * on phyt company
 */

public class CopyItem extends BaseEntity {

    private String key;
    private String value;
    private boolean isFocus = false;

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public CopyItem() {
    }

    public CopyItem(char key) {
        this.key = String.valueOf(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static List<CopyItem> strTransferList(String str){
        List<CopyItem> copyItems = new ArrayList<>();
        for(char c:str.toCharArray()){
            if(copyItems.size()==0){
                CopyItem copyItem = new CopyItem(c);
                copyItem.setFocus(true);
                copyItems.add(copyItem);
            }else{
                copyItems.add(new CopyItem(c));
            }

        }
        return copyItems;
    }
}
