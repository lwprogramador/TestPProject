package com.example.testproject.http;

import com.example.testproject.dto.DTOPageMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface URLConexion {

    @GET("movie/popular")
    Call<DTOPageMovies> getMovePopulate(@Query("api_key") String apiKey);

}
