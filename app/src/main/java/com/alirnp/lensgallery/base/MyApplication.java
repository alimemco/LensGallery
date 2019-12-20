package com.alirnp.lensgallery.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import androidx.appcompat.app.AppCompatDelegate;

import com.alirnp.lensgallery.base.Constants;
import com.alirnp.lensgallery.interfaces.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    public static Typeface iranSans;
    public static Typeface iranSansBold;
    private static ApiService apiService;

    public static ApiService getApiService() {
        return apiService;
    }

    public static Typeface getIranSans(Context context) {
        if (iranSans == null) {
            iranSans = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile.ttf");
        }
        return iranSans;
    }

    public static Typeface getIranSansBold(Context context) {
        if (iranSansBold == null) {
            iranSansBold = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile_Bold.ttf");

        }
        return iranSansBold;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        apiService = retrofit.create(ApiService.class);

    }

    private OkHttpClient OkHttpClientWithoutCache() {
        return new OkHttpClient.Builder()
                .cache(null)
                .build();
    }
}
