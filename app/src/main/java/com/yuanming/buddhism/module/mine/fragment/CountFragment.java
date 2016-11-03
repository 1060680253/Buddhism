package com.yuanming.buddhism.module.mine.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public class CountFragment extends BaseFragment {

    @BindView(R.id.tv_counting_type)
    MaterialSpinner spinner;
    @BindView(R.id.et_counts)
    EditText etCounts;
    @BindView(R.id.et_pledge)
    EditText etPledge;
    @BindView(R.id.btn_counting)
    CircularProgressButton btnCounting;
    @BindView(R.id.tv_date)
    TextView tvDate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_counts;
    }

    @OnClick({R.id.tv_counting_type, R.id.btn_counting,R.id.tv_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_counting_type:
                break;
            case R.id.btn_counting:
                break;
            case R.id.tv_date:
                showDatePickDlg(view.getContext());
                break;
        }
    }

    protected void showDatePickDlg(Context context) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tvDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @Override
    public void initView(View view) {
        spinner.setItems("尼玛石", "尼玛石1", "尼玛石2", "尼玛石3", "尼玛石4");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

            }
        });
    }
}
