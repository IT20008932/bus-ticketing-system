package com.csse.busticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import android.util.Pair;

public class SplashScreen extends AppCompatActivity {

    private static int DELAY_TIME = 4000;

    Animation topAnim, bottomAnim;
    ImageView appName, logoImage;

    SharedPreferences onBoardingScreen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        appName = findViewById(R.id.app_name);
        logoImage = findViewById(R.id.app_logo);

        appName.setAnimation(topAnim);
        logoImage.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if (isFirstTime) {
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    startActivity(new Intent(SplashScreen.this,OnBoardingScreen.class));
                }
                else {
                    Intent intent = new Intent(SplashScreen.this,LoginScreen.class);

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair(appName,"splash_text");
                    pairs[1] = new Pair(logoImage,"splash_image");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                    startActivity(intent,options.toBundle());
                    finish();
                }

            }
        },DELAY_TIME);
    }
}