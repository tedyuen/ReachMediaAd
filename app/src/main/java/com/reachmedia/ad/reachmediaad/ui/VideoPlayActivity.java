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

import com.reachmedia.ad.reachmediaad.MainActivity;
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

public class VideoPlayActivity extends Activity {

    private VideoView vv;
    private ImageView iv;
    private TextView tv;
    String[] uri;
    int[] resourceId;
    long lastTime;
    long httpTime;

    Map<String,Integer> targetImage = new HashMap<>();
    Map<String,String> targetMp4 = new HashMap<>();
    String uriRoot;
    int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_video_play);

//        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);

        targetImage.put("gonggao11",R.mipmap.gonggao11);
        targetImage.put("xiaodaizhijia",R.mipmap.xiaodaizhijia);

        resourceId = new int[]{
//                R.mipmap.gonggao11,
                R.mipmap.kaidilake,
                R.mipmap.laogui,
                R.mipmap.weilushi,
//                R.mipmap.xiaodaizhijia,
                R.mipmap.yingfu
        };


        lastTime = 0;
        httpTime = System.currentTimeMillis();
//        setImage("-1");



        vv = (VideoView) findViewById(R.id.vv);


        String rootUri = "android.resource://" + getPackageName() + "/";

        uri = new String[]{
//                rootUri + R.raw.puxipingan,
//                rootUri + R.raw.saiou,
                rootUri + R.raw.shihualuoshiqi,
                rootUri + R.raw.yyzhiru,
        };

        targetMp4.put("puxipingan",rootUri + R.raw.puxipingan);
        targetMp4.put("saiou",rootUri + R.raw.saiou);


        play("-1");

    }

    int delay = 6000;

    private void setImage(String id){
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

            getServerId(1000);
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
//                        play();
                        play(data.getData().getVideoId());
                        long now = System.currentTimeMillis();
                        tv.setText((now-httpTime)+":"+data.getData().getVideoId()+":"+data.getData().getMac());
                        httpTime =  now;
                    }else{
//                        setImage("-1");
                        play("-1");
                    }
                }
            }

            @Override
            public void onFailDisplay(String errorMsg) {
//                setImage("-1");
                play("-1");

            }
        });
        getIdController.getId();
    }



    private void hide(){
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉虚拟按键全屏显示
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void playRoot(String id){
        vv.setOnCompletionListener(new VideoPlayActivity.MyPlayerOnCompletionListener());
        vv.setVideoURI(Uri.parse(targetMp4.get(id)));
        vv.start();
    }

    private void play(String id){
        if(!"-1".equals(id) && targetMp4.containsKey(id)) {
            playRoot(id);
        }else{
            if(vv!=null && vv.isPlaying()){
                vv.stopPlayback();
            }
            vv.setOnCompletionListener(new VideoPlayActivity.MyPlayerOnCompletionListener());
            if(current>=uri.length){
                current = 0;
            }
            vv.setVideoURI(Uri.parse(uri[current]));
            vv.start();
        }

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
