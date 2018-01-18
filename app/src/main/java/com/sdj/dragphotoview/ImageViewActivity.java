package com.sdj.dragphotoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class ImageViewActivity extends AppCompatActivity {
    private ImageView imageView;
    private boolean isFriendCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        imageView = findViewById(R.id.icon);
        isFriendCircle = getIntent().getBooleanExtra("isFriendCircle",false);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(isFriendCircle){
                    intent = new Intent(ImageViewActivity.this, WechatFriendCircleActivity.class);
                    int[]location = new int[2];
                    imageView.getLocationOnScreen(location);
                    intent.putExtra("x",location[0]);
                    intent.putExtra("y",location[1]);
                    intent.putExtra("w",imageView.getWidth());
                    intent.putExtra("h",imageView.getHeight());
                }else {
                    intent = new Intent(ImageViewActivity.this, ByteDanceActivity.class);
                    int[]location = new int[2];
                    imageView.getLocationOnScreen(location);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}
