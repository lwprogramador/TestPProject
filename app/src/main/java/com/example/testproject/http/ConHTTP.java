package com.example.testproject.http;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.example.testproject.dto.DTOMovies;
import com.example.testproject.dto.DTOPageMovies;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConHTTP {
    public static final int TIMEOUT_LONG = 30;
    public static final int STATUS_OK = 200;

    public DTOPageMovies getMoviesPopulate(){
        DTOPageMovies itemReturn = new DTOPageMovies();
        try{
            String BASE_URL = AppService.getInstance().getProperties().getProperty("url_api");

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(TIMEOUT_LONG, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT_LONG, TimeUnit.SECONDS);
            Retrofit retBuild = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create())
                    )
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .build();

            URLConexion apiService = retBuild.create(URLConexion.class);
            Call<DTOPageMovies> call = apiService.getMovePopulate(AppService.getInstance().getProperties().getProperty("api_key"));
            retrofit2.Response<DTOPageMovies> respBody = call.execute();

            if(respBody.isSuccessful()){
                itemReturn = respBody.body();
                itemReturn.setStatus(respBody.code());
                for(DTOMovies item: respBody.body().getResult()){
                    Log.e("Movie", item.getTitle());
                }
            }else{
                itemReturn.setStatus(respBody.code());
                itemReturn.setMessage(respBody.errorBody().string());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return itemReturn;
    }

}
