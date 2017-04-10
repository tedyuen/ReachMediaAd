package com.reachmedia.ad.reachmediaad.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.reachmedia.ad.reachmediaad.R;

/**
 * Created by tedyuen on 10/04/2017.
 */

public class BetaAdActivity extends BaseActivity {

    ImageView iv_one;
    ImageView iv_two;

    String macAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_beta_ad);

        System.out.println("mac address: 1==> "+getLocalMacAddressFromWifiInfo(this));
        macAddress = getLocalMacAddressFromWifiInfo(this).replace(":","");
        System.out.println("mac address: 2==> "+macAddress);

        iv_one = (ImageView) findViewById(R.id.iv_one);
        iv_two = (ImageView) findViewById(R.id.iv_two);


    }




}
