package com.sdj.dragphotoview;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.davemorrissey.labs.subscaleview.FriendCircleView;

/**
 * Created by sdj on 2017/11/1.
 */

public class WechatFriendCircleActivity extends AppCompatActivity {
    FriendCircleView friendCircleView;
    private int[] location;
    private int w;
    private int h;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_friendcircle);
        friendCircleView = findViewById(R.id.icon);
        Intent intent = getIntent();
        location = new int[2];
        location[0] = intent.getIntExtra("x",0);
        location[1] = intent.getIntExtra("y",0);
        w = intent.getIntExtra("w",0);
        h = intent.getIntExtra("h",0);
        friendCircleView.setOriginView(w,h, BitmapFactory.decodeResource(getResources(),R.mipmap.ss),location);
    }
}
