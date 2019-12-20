package com.alirnp.lensgallery.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alirnp.lensgallery.R;
import com.alirnp.lensgallery.base.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Utils.connected(this))
            startActivity(new Intent(this, MainActivity.class));
        else
            startActivity(new Intent(this, ErrorConnectionActivity.class));

        finish();
    }
}
