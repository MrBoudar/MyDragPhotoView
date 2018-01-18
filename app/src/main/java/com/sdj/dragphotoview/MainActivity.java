package com.sdj.dragphotoview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onByteDance(View v){
        Intent intent = new Intent(this,ImageViewActivity.class);
        intent.putExtra("isFriendCircle",false);
        startActivity(intent);

    }
    public void onFriendCircle(View v){
        Intent intent = new Intent(this,ImageViewActivity.class);
        intent.putExtra("isFriendCircle",true);
        startActivity(intent);
    }
}
