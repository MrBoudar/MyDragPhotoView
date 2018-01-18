package com.sdj.dragphotoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.davemorrissey.labs.subscaleview.FingerPanGroup;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView3;

/**
 * Created by sdj on 2017/11/1.
 */

public class ByteDanceActivity extends AppCompatActivity {
   private SubsamplingScaleImageView3 subsamplingScaleImageView3;
    private FingerPanGroup fingerPanGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byte_dance);
        subsamplingScaleImageView3 = findViewById(R.id.icon);
        ImageSource imageSource = ImageSource.resource(R.mipmap.too_long);
        subsamplingScaleImageView3.setImage(imageSource);
        subsamplingScaleImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        fingerPanGroup = findViewById(R.id.fingerGroup);
        fingerPanGroup.setOnAlphaChangeListener(new FingerPanGroup.onAlphaChangedListener() {
            @Override
            public void onAlphaChanged(float alpha) {
                //更改透明度
            }

            @Override
            public void onTranslationYChanged(float translationY) {
                //根据距离显示隐藏主UI控件
                if(Math.abs(translationY) > 0){
                    //todo

                }else {

                }
            }
        });

    }
}
