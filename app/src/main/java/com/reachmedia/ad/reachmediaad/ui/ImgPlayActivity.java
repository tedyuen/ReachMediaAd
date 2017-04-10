package com.reachmedia.ad.reachmediaad.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.reachmedia.ad.reachmediaad.R;
import com.reachmedia.ad.reachmediaad.app.AppApiContact;
import com.reachmedia.ad.reachmediaad.model.GetIdModel;
import com.reachmedia.ad.reachmediaad.network.callback.UiDisplayListener;
import com.reachmedia.ad.reachmediaad.network.controller.GetIdController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tedyuen on 2017/2/20.
 */

public class ImgPlayActivity extends BaseActivity {
    private ImageView iv;
    int[] resourceId;
    long lastTime;
    long httpTime;
    int delay = 5000;
    String macAddress = "";


    Map<String,Integer> targetImage = new HashMap<>();
    Map<String,String> targetMp4 = new HashMap<>();
    String uriRoot;
    int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_img_play);
        macAddress = getLocalMacAddressFromWifiInfo(this).replace(":","");
        iv = (ImageView) findViewById(R.id.iv);

        targetImage.put("img_5",R.mipmap.img_5);
        targetImage.put("img_6",R.mipmap.img_6);

        int type = getIntent().getIntExtra("type",0);
        switch (type){
            case 0:
                resourceId = new int[]{
                        R.mipmap.img_1,
                        R.mipmap.img_2,
                        R.mipmap.img_3,
                        R.mipmap.img_4,
                        R.mipmap.img_5,
                        R.mipmap.img_6
                };
                break;
            case 1:
                resourceId = new int[]{
                        R.mipmap.img_1,
                        R.mipmap.img_2,
                        R.mipmap.img_3,
                        R.mipmap.img_4,
                };
                break;
            case 2:
                resourceId = new int[]{
                        R.drawable.black
                };
                delay = 1000;
                break;
        }



        lastTime = 0;
        httpTime = System.currentTimeMillis();


        setImage("-1");


    }


    private void setImage(String id){
        System.out.println("delay:"+delay);
        if(!"-1".equals(id) && targetImage.containsKey(id)){
            iv.setImageResource(targetImage.get(id));
            getServerId(delay);
        }else{
            long currentTime = System.currentTimeMillis();
            if((currentTime-lastTime)>=delay){
                lastTime = currentTime;
                if(current>=resourceId.length){
                    current = 0;
                }
                iv.setImageResource(resourceId[current]);
                current++;
            }

            getServerId(delay);
        }
    }

    private void getServerId(int delay){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                http();
            }
        },delay);
    }



    private void http(){
        GetIdController getIdController = new GetIdController(new UiDisplayListener<GetIdModel>() {
            @Override
            public void onSuccessDisplay(GetIdModel data) {
                if (data != null) {
                    if (AppApiContact.ErrorCode.SUCCESS.equals(data.rescode)) {
                        setImage(data.getData().getVideoId());
                        long now = System.currentTimeMillis();
                        httpTime =  now;
                    }else{
                        setImage("-1");
                    }
                }else {
                    setImage("-1");
                }
            }

            @Override
            public void onFailDisplay(String errorMsg) {
                setImage("-1");
            }
        });
        getIdController.getId(macAddress);
    }


    class MyPlayerOnCompletionRootListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
//            playRoot();
        }
    }
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            current++;
            http();
        }
    }
}
