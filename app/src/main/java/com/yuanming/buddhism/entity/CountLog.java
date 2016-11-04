package com.yuanming.buddhism.entity;

import com.yuanming.buddhism.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenghuan on 2016/11/4.
 * on phyt company
 */

public class CountLog extends BaseEntity {

    private String time;
    private String mantra_type;
    private int recite_count;
    private String personal_pledge;
    private String recite_type;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMantra_type() {
        return mantra_type;
    }

    public void setMantra_type(String mantra_type) {
        this.mantra_type = mantra_type;
    }

    public int getRecite_count() {
        return recite_count;
    }

    public void setRecite_count(int recite_count) {
        this.recite_count = recite_count;
    }

    public String getPersonal_pledge() {
        return personal_pledge;
    }

    public void setPersonal_pledge(String personal_pledge) {
        this.personal_pledge = personal_pledge;
    }

    public String getRecite_type() {
        return recite_type;
    }

    public void setRecite_type(String recite_type) {
        this.recite_type = recite_type;
    }

    public CountLog(int recite_count, String time, String personal_pledge, String recite_type) {
        this.recite_count = recite_count;
        this.time = time;
        this.personal_pledge = personal_pledge;
        this.recite_type = recite_type;
    }

    public static List<CountLog> getDatas(){
        List<CountLog> countLogs = new ArrayList<>();
        countLogs.add(new CountLog(1,"2016-09-08","开开信息，为了世界祈福","尼玛石"));
        countLogs.add(new CountLog(2,"2016-01-08","开开信息，为了世界祈福","尼玛石2"));
        countLogs.add(new CountLog(5,"2016-02-08","开开信息，为了世界祈福dsd","尼玛石"));
        countLogs.add(new CountLog(9,"2016-05-08","开开信息，为了世界祈福","尼玛石3"));
        countLogs.add(new CountLog(9,"2016-03-08","开开信息，为了世界dsd祈福","尼玛石e"));
        countLogs.add(new CountLog(3,"2016-02-08","开开信息，为了世界祈福","尼玛石"));
        countLogs.add(new CountLog(21,"2016-01-08","开开信息，为了世ss界祈福","尼玛f石"));
        countLogs.add(new CountLog(1,"2016-01-08","开开信息，为了世界祈福","尼玛石s"));
        return countLogs;
    }
}
