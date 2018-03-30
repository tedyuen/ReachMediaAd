package com.reachmedia.ad.reachmediaad;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.reachmedia.ad.reachmediaad.app.AppApiContact;
import com.reachmedia.ad.reachmediaad.model.GetIdModel;
import com.reachmedia.ad.reachmediaad.network.callback.UiDisplayListener;
import com.reachmedia.ad.reachmediaad.network.controller.GetIdController;
import com.reachmedia.ad.reachmediaad.ui.BetaAdActivity;
import com.reachmedia.ad.reachmediaad.ui.ImgPlayActivity;
import com.reachmedia.ad.reachmediaad.ui.StaticDownloadVideoActivity;
import com.reachmedia.ad.reachmediaad.ui.StaticImgActivity;
import com.reachmedia.ad.reachmediaad.ui.TestActivity;
import com.reachmedia.ad.reachmediaad.ui.VideoAndImgActivity;
import com.reachmedia.ad.reachmediaad.ui.VideoPlayActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    Button bt_video;
    Button bt_six;
    Button bt_four;
    Button bt_ad;
    Button bt_test;
//    Button bt_distance;

    Button bt_hongbao;
    Button bt_guanggao;
    Button bt_download_video;
    Button bt_test_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_video = (Button) findViewById(R.id.bt_video);
        bt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),VideoPlayActivity.class));
            }
        });

        bt_six = (Button) findViewById(R.id.bt_six);
        bt_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ImgPlayActivity.class);
                intent.putExtra("type",0);

                startActivity(intent);
            }
        });

        bt_four = (Button) findViewById(R.id.bt_four);
        bt_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ImgPlayActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

//        bt_distance = (Button) findViewById(R.id.bt_distance);
//        bt_distance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),ImgPlayActivity.class);
//                intent.putExtra("type",2);
//                startActivity(intent);
//            }
//        });
        bt_hongbao = (Button) findViewById(R.id.bt_hongbao);
        bt_hongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StaticImgActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
            }
        });

        bt_guanggao = (Button) findViewById(R.id.bt_guanggao);
        bt_guanggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StaticImgActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

        bt_download_video = (Button) findViewById(R.id.bt_download_video);
        bt_download_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StaticDownloadVideoActivity.class);
                startActivity(intent);
            }
        });

        bt_ad = (Button) findViewById(R.id.bt_ad);
        bt_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BetaAdActivity.class);
                startActivity(intent);
            }
        });
        bt_test = (Button) findViewById(R.id.bt_test);
        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
            }
        });

        bt_test_video = (Button) findViewById(R.id.bt_test_video);
        bt_test_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VideoAndImgActivity.class);
                startActivity(intent);
            }
        });






    }


}
