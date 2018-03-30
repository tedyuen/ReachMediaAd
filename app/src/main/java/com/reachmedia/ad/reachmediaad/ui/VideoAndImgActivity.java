package com.reachmedia.ad.reachmediaad.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import com.reachmedia.ad.reachmediaad.R;

/**
 * Created by tedyuen on 2018/3/29.
 */

public class VideoAndImgActivity extends Activity {
    private VideoView vv;
    String[] uri;

    int[] resourceId;
    private ImageView iv_one;

    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_static_video_img);

        vv = (VideoView) findViewById(R.id.vv);
        iv_one = (ImageView) findViewById(R.id.iv_one);

        String rootUri = "android.resource://" + getPackageName() + "/";
        resourceId = new int[]{
                R.mipmap.imgvideo_1,
                R.mipmap.imgvideo_2,
                R.mipmap.imgvideo_3,
        };
        uri = new String[]{
                rootUri + R.raw.video_1,
                rootUri + R.raw.video_2,
                rootUri + R.raw.video_3,
        };

        play("-1");

    }

    private void play(String id){
        if(vv!=null && vv.isPlaying()){
            vv.stopPlayback();
        }
        vv.setOnCompletionListener(new VideoAndImgActivity.MyPlayerOnCompletionListener());
        if(current>=uri.length){
            current = 0;
        }
        iv_one.setImageResource(resourceId[current]);
        vv.setVideoURI(Uri.parse(uri[current]));
        vv.start();

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            current++;

            play("-1");
        }
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
}
