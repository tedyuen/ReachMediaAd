package com.reachmedia.ad.reachmediaad;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.reachmedia.ad.reachmediaad.app.AppApiContact;
import com.reachmedia.ad.reachmediaad.model.GetIdModel;
import com.reachmedia.ad.reachmediaad.network.callback.UiDisplayListener;
import com.reachmedia.ad.reachmediaad.network.controller.GetIdController;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private VideoView vv;
    private ImageView iv;
    private TextView tv;
    String[] uri;
    int[] resourceId;
    long lastTime;
    long httpTime;

    Map<String,Integer> targetImage = new HashMap<>();
    String uriRoot;
    int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);

        targetImage.put("gonggao11",R.mipmap.gonggao11);
        targetImage.put("xiaodaizhijia",R.mipmap.xiaodaizhijia);

        resourceId = new int[]{
//                R.mipmap.gonggao11,
                R.mipmap.kaidilake,
                R.mipmap.laogui,
                R.mipmap.weilushi,
                R.mipmap.xiaodaizhijia,
                R.mipmap.yingfu
        };


        lastTime = 0;
        httpTime = System.currentTimeMillis();
        setImage("-1");



//        vv = (VideoView) findViewById(R.id.vv);


        String rootUri = "android.resource://" + getPackageName() + "/";

//        uriRoot = rootUri + R.raw.img_1;
//        uri = new String[]{
//                rootUri + R.raw.img_2,
//                rootUri + R.raw.img_3,
//                rootUri + R.raw.img_4,
//                rootUri + R.raw.img_5,
//        };
//        playRoot();

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
                        setImage(data.getData().getVideoId());
                        long now = System.currentTimeMillis();

                        tv.setText((now-httpTime)+":"+data.getData().getVideoId()+":"+data.getData().getMac());
                        httpTime =  now;
                    }
                }
            }

            @Override
            public void onFailDisplay(String errorMsg) {

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

    private void playRoot(){
        vv.setOnCompletionListener(new MyPlayerOnCompletionRootListener());
        vv.setVideoURI(Uri.parse(uriRoot));
        vv.start();
    }

    private void play(){
        if(vv!=null && vv.isPlaying()){
            vv.stopPlayback();
        }
        vv.setOnCompletionListener(new MyPlayerOnCompletionListener());
        if(current>=uri.length){
            current = 0;
        }
        vv.setVideoURI(Uri.parse(uri[current]));
        vv.start();
    }

    class MyPlayerOnCompletionRootListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            playRoot();
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
