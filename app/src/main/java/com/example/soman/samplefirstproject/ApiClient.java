package com.example.soman.samplefirstproject;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Soman on 8/2/2017.
 */
public class ApiClient {

     static String name;
    public static String BASE_URL ;
    public static Retrofit retrofit = null;


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static Retrofit getApiClient(){

        name = MainActivity.var;


            if(name.isEmpty() || name==null)
            {

                Log.d("name is empty","no name");
                BASE_URL = "https://api.github.com/users/sharmadhiraj/repos/";
            }
            else {
                BASE_URL = "https://api.github.com/users/"+name+"/repos/";
                Log.d("name is not empty",BASE_URL);
            }
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        Log.d("retrofit",retrofit.baseUrl().toString());
        Log.d("this is name","name is this");
        return retrofit;
    }
}

