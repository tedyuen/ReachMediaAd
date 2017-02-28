package com.reachmedia.ad.reachmediaad.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.reachmedia.ad.reachmediaad.R;

/**
 * Created by tedyuen on 28/02/2017.
 */

public class StaticImgActivity extends Activity {

    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        setContentView(R.layout.activity_static_img);
        iv = (ImageView) findViewById(R.id.iv);

        int type = getIntent().getIntExtra("type",0);
        switch (type) {
            case 0://红包
                iv.setImageResource(R.mipmap.red_package);
                break;
            case 1://买电影票
                iv.setImageResource(R.mipmap.buy_ticket);
                break;
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
