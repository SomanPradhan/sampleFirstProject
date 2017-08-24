package com.example.soman.samplefirstproject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Soman on 8/2/2017.
 */
public interface ApiInterface {
//    @GET("/users/sharmadhiraj/repos")
//    Call<List<ListItem>> getListItem();


    @GET
    Call<ResponseBody> getApiInterface(@Url  String url);

}
