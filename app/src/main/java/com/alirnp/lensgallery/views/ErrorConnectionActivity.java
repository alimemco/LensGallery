package com.alirnp.lensgallery.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alirnp.lensgallery.R;
import com.alirnp.lensgallery.base.Utils;
import com.alirnp.lensgallery.customView.MyButton;

public class ErrorConnectionActivity extends AppCompatActivity implements View.OnClickListener {

    private MyButton tryAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_connection);

        initView();
        setListener();
    }

    private void setListener() {
        tryAgainButton.setOnClickListener(this);
    }

    private void initView() {
        tryAgainButton = findViewById(R.id.activity_error_connection_button);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_error_connection_button) {
            if (Utils.connected(ErrorConnectionActivity.this)) {
                startActivity(new Intent(ErrorConnectionActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, R.string.error_connection, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
