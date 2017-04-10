package com.reachmedia.ad.reachmediaad.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.reachmedia.ad.reachmediaad.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tedyuen on 10/04/2017.
 */

public class BetaAdActivity extends BaseActivity {

    ImageView iv_one;
    ImageView iv_two;

    String macAddress = "";
    int[] resourceId;

    int index = 0;


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



        resourceId = new int[]{
                R.mipmap.img_1,
                R.mipmap.img_2,
                R.mipmap.img_3,
                R.mipmap.img_4,
                R.mipmap.img_5,
                R.mipmap.img_6
        };

        Timer timer = new Timer();
        timer.schedule(new ChangeImgTask(),0, 5000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            iv_one.setImageResource(resourceId[index]);
            if(index<resourceId.length-1){
                index++;
            }else{
                index = 0;
            }
        }
    };


    class ChangeImgTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what=1;
            handler.sendMessage(message);
        }
    }



}
