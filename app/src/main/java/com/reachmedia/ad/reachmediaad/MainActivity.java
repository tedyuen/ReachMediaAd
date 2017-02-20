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
import com.reachmedia.ad.reachmediaad.ui.ImgPlayActivity;
import com.reachmedia.ad.reachmediaad.ui.VideoPlayActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    Button bt_video;
    Button bt_six;
    Button bt_four;
    Button bt_distance;

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

        bt_distance = (Button) findViewById(R.id.bt_distance);
        bt_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ImgPlayActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
            }
        });


    }


}
