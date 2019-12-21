package com.alirnp.lensgallery.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alirnp.lensgallery.R;
import com.alirnp.lensgallery.base.Utils;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFireBaseMassage();

        //Utils.sendNotification(this,"message body");


    }

    private void initFireBaseMassage() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {
                        Utils.log(MainActivity.class, "getInstanceId failed" + task.getException());
                        return;
                    }

                    if (task.getResult() != null) {
                        String token = task.getResult().getToken();

                        // Log
                        String msg = getString(R.string.msg_token_fmt, token);
                        Utils.log(MainActivity.class, msg);
                    }
                });
    }

}
