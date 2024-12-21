package com.example.slasscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SlashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slash_screen);



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SlashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },3000);
        }

    }



