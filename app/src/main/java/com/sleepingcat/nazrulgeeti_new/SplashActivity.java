package com.sleepingcat.nazrulgeeti_new;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.sleepingcat.nazrulgeeti_new.R;

public class SplashActivity extends AppCompatActivity {

    //animation variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView brand, ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        logo = findViewById(R.id.imageView2);
        brand = findViewById(R.id.textView2);
        ver = findViewById(R.id.textView3);

        logo.setAnimation(topAnim);
        brand.setAnimation(bottomAnim);
        ver.setAnimation(bottomAnim);


        // Display splash screen for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After 3 seconds, start MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close SplashActivity so it's not shown when the user presses back
            }
        }, 5000); // 3 seconds delay
    }
}