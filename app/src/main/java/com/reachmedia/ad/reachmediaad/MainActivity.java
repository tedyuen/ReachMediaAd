package com.reachmedia.ad.reachmediaad;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.reachmedia.ad.reachmediaad.model.GetIdModel;
import com.reachmedia.ad.reachmediaad.network.callback.UiDisplayListener;
import com.reachmedia.ad.reachmediaad.network.controller.GetIdController;

public class MainActivity extends Activity {

    private VideoView vv;
    String[] uri;
    int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_main);
        vv = (VideoView) findViewById(R.id.vv);


        String rootUri = "android.resource://" + getPackageName() + "/";

        uri = new String[]{
                rootUri + R.raw.img_1,
                rootUri + R.raw.img_2,
                rootUri + R.raw.img_3,
                rootUri + R.raw.img_4,
                rootUri + R.raw.img_5,
        };
        vv.setOnCompletionListener(new MyPlayerOnCompletionListener());
        play();

        http();
    }


    private void http(){
        GetIdController getIdController = new GetIdController(new UiDisplayListener<GetIdModel>() {
            @Override
            public void onSuccessDisplay(GetIdModel data) {

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

    private void play(){
        if(current>=uri.length){
            current = 0;
        }
    vv.setVideoURI(Uri.parse(uri[current]));
        vv.start();
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            current++;
            play();
        }
    }
}
