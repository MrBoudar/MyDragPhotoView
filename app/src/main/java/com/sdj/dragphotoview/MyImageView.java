package com.sdj.dragphotoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by sdj on 2017/11/1.
 */

public class MyImageView extends android.support.v7.widget.AppCompatImageView {
    int width;
    int height;
    private boolean isUp;
    private boolean isDown;
    float dy = 0;
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = getHeight();
    }

    @SuppressLint("Range")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                dy = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetY = event.getRawY() - dy;
                if(event.getRawY() - dy > 0){
                    float alpha = 1.0f;
                    if(offsetY <= 0){
                        alpha = 1.0f;
                    }else if(offsetY >= height / 2){
                        offsetY = height / 2;
                        alpha = 1.0f;
                    }else {
                        alpha = Math.abs(offsetY / (height / 2));
                    }
                    if(alpha >= 1.0){
                        alpha = alpha - 1.0f;
                    }
                    LinearLayout linearLayout = (LinearLayout) getParent();
                    if(null != linearLayout && offsetY > 0){
                        ViewHelper.setTranslationY(this,offsetY);
                        linearLayout.setAlpha(1 - alpha);
                    }
                    Log.e("MyImageView","alpha = " + alpha + "offsetY = " + offsetY + "Height / 2 = " + height / 2 + "dy ==" + dy);
                    return true;
                }
                break;
        }
        return true;
    }
}
