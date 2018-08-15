package com.clidev.gamecodex.gamedetails.model;

import com.clidev.gamecodex.ApiKeys;
import com.clidev.gamecodex.populargames.model.modeldata.Game;

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
}
