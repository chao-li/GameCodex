package com.clidev.gamecodex.retrofit;


import com.clidev.gamecodex.ApiKeys;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;
import com.clidev.gamecodex.room.entities.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PopularGamesRetrofitClient {


    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/games/")
    Call<List<Game>> getReleasedGames(
            @Query("fields") String fields,
            @Query("filter[release_dates.date][lte]") String releasedBefore,
            @Query("filter[release_dates.platform][eq]") int platform,
            @Query("order") String order,
            @Query("limit") int limit,
            @Query("offset") int offset
    );



    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/games/")
    Call<List<Game>> getNextSetReleasedGames(
            @Query("fields") String fields,
            @Query("filter[release_dates.date][lte]") String releasedBefore,
            @Query("filter[release_dates.platform][eq]") int platform,
            @Query("order") String order,
            @Query("limit") int limit,
            @Query("offset") int offset
    );



    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/genres/")
    Call<List<Genre>> getGenres(
            @Query("fields") String fields,
            @Query("limit") int limit
    );







}
