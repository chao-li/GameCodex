package com.clidev.gamecodex.retrofit;

import com.clidev.gamecodex.ApiKeys;
import com.clidev.gamecodex.room.entities.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CompanyRetrofitClient {

    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/companies/")
    Call<List<Genre>> getGenres(
            @Query("fields") String fields,
            @Query("limit") int limit
    );

}
