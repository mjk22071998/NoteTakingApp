package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logoImage;
    TextView title;
    Animation topAnimation, bottomAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        logoImage=findViewById(R.id.logo);
        title=findViewById(R.id.name);
        topAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_animation);
        logoImage.setAnimation(topAnimation);
        title.setAnimation(bottomAnimation);
        new Handler().postDelayed(
                () -> {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                },2000);
    }
}