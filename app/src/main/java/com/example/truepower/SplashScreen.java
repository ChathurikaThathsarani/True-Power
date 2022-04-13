package com.example.truepower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    Animation logoAnim, innoAnim,teamAnim;
    ImageView logo,innovation,team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        logoAnim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        innoAnim = AnimationUtils.loadAnimation(this,R.anim.innovation_animation);
        teamAnim = AnimationUtils.loadAnimation(this,R.anim.team_animation);

        logo = findViewById(R.id.img_logo_name);
        innovation = findViewById(R.id.img_innovation);
        team = findViewById(R.id.img_team_name);

        logo.setAnimation(logoAnim);
        innovation.setAnimation(innoAnim);
        team.setAnimation(teamAnim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                finish();
            }
        },3500);
    }
}