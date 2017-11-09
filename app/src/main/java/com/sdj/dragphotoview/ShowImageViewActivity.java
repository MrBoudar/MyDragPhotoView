package com.sdj.dragphotoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView2;

/**
 * Created by sdj on 2017/11/1.
 */

public class ShowImageViewActivity extends AppCompatActivity {
    SubsamplingScaleImageView2 subsamplingScaleImageView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        subsamplingScaleImageView2 = findViewById(R.id.icon);
        ImageSource imageSource = ImageSource.resource(R.mipmap.ss);
        subsamplingScaleImageView2.setImage(imageSource);
        subsamplingScaleImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

    }

}
