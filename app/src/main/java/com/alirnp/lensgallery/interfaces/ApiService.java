package com.alirnp.lensgallery.interfaces;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    /*@POST(Constants.API_V1 + "test.php")
    Call<ResponseBody> get(
            @Query("REQUEST") String request,
            @Query("TOKEN") String token,
            @QueryMap Map<String, String> info);*/

    @POST("test.php")
    Call<ResponseBody> get(
            @Query("REQUEST") String request,
            @Query("TOKEN") String token,
            @QueryMap Map<String, String> info);
}
