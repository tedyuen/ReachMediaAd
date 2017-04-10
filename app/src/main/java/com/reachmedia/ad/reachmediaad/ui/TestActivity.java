package com.reachmedia.ad.reachmediaad.ui;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.reachmedia.ad.reachmediaad.R;

/**
 * Created by tedyuen on 10/04/2017.
 */

public class TestActivity extends BaseActivity {

    TextView tv_info;
    TextView tv_desc;

    ScrollView sv_one;
    ScrollView sv_two;

    String macAddress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_test);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        sv_one = (ScrollView) findViewById(R.id.sv_one);
        sv_two = (ScrollView) findViewById(R.id.sv_two);
        getInfo();
        getDesc();
    }


    private void getInfo(){
        macAddress = getLocalMacAddressFromWifiInfo(this).replace(":","");
        tv_info.setText("macAddress: "+macAddress+"\n");
    }
    private void getDesc(){
        ViewTreeObserver vto = sv_one.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                int height = sv_one.getMeasuredHeight();
                int width = sv_one.getMeasuredWidth();
                tv_desc.append("one: "+height+","+width+"\n");
                return true;
            }
        });
        ViewTreeObserver vto2 = sv_two.getViewTreeObserver();
        vto2.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                int height = sv_two.getMeasuredHeight();
                int width = sv_two.getMeasuredWidth();
                tv_desc.append("two: "+height+","+width+"\n");
                tv_desc.append("-----------------\n");
                return true;
            }
        });
    }
}
