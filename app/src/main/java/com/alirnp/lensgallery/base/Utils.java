package com.alirnp.lensgallery.base;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import com.alirnp.lensgallery.R;
import com.alirnp.lensgallery.views.MainActivity;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class Utils {

    private static final String TAG = "UtilsApp";

    public static boolean connected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null)
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        else
            return false;
    }


    public static void log(Class cls, String txt) {
        Log.i(TAG, txt);
    }

    public static void log(Class cls, int txt) {
        Log.i(TAG, String.valueOf(txt));
    }

    public static void sendNotification(Context context, String messageBody) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = context.getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle(context.getString(R.string.fcm_message))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null) {
                NotificationChannel channel = new NotificationChannel(channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);

                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
            }

        }


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
