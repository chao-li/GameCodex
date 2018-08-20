package com.clidev.gamecodex.retrofit;

import com.clidev.gamecodex.ApiKeys;
import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Company;
import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Platform;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameDetailsRetrofitClient {

    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/games/{id}/")
    Call<List<Game>> getThisGame(
            @Path("id") Long id,
            @Query("fields") String fields
    );


    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/companies/{id}/")
    Call<List<Company>> getTheseCompanies(
            @Path("id") String id,
            @Query("fields") String fields
    );

    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/platforms/{id}/")
    Call<List<Platform>> getThesePlatforms(
            @Path("id") String id,
            @Query("fields") String fields
    );


}
