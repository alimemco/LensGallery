package com.alirnp.lensgallery.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.alirnp.lensgallery.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class Utils {

    public static boolean connected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null)
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        else
            return false;
    }

    public static boolean validateCallback(Context context, Response response) {
        String error = "unknown";

        if (response.isSuccessful()) {
            if (response.body() != null) {
                if (response.code() == 200) {
                    //TODO change success work
                    /*
                    Result result = response.body().getResult().get(0);
                    boolean success = Boolean.parseBoolean(result.getSuccess());
                    if (success) {
                        if (result.getItems() != null) {
                            return true;
                        } else {
                            error = context.getString(R.string.error_emptyItems);
                        }

                    } else {
                        error = result.getMessage();
                    }

                    */
                } else {
                    error = context.getString(R.string.error_emptyHttpOK);
                }

            } else {
                error = context.getString(R.string.error_unSuccess);
            }
        } else {
            error = context.getString(R.string.error_empty_body);
        }
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        return false;
    }


    public static boolean validateCallbackResponseBody(Context context, Response<ResponseBody> response) {
        String error;

        if (response.isSuccessful()) {
            if (response.body() != null) {
                if (response.code() == 200) {

                    try {
                        response.body().string();
                        return true;
                    } catch (IOException e) {
                        error = e.toString();
                    }


                } else {
                    error = context.getString(R.string.error_emptyHttpOK);
                }

            } else {
                error = context.getString(R.string.error_unSuccess);
            }
        } else {
            error = context.getString(R.string.error_empty_body);
        }
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        return false;
    }

}
