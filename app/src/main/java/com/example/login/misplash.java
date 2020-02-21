package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class misplash extends AppCompatActivity {

    TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misplash);

        tv = findViewById(R.id.textView);
        iv = findViewById(R.id.imageView);


        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.translate);
        Animation myAnimLogo = AnimationUtils.loadAnimation(this, R.anim.fadein);

        iv.startAnimation(myAnim);
        tv.startAnimation(myAnimLogo);
        getSupportActionBar().hide();
        openApp(true);
    }

    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(misplash
                        .this, InicioActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
