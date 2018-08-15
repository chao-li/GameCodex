package com.clidev.gamecodex.populargames.model;


import com.clidev.gamecodex.ApiKeys;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.model.room.genre.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitClient {


    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/games/")
    Call<List<Game>> getGame(
            @Query("fields") String fields,
            @Query("filter[release_dates.date][lte]") String releasedBefore,
            @Query("order") String order,
            @Query("limit") int limit

    );




    @Headers({
            ApiKeys.IGDB_API_KEY,
            "Accept: application/json"
    })
    @GET("/games/")
    Call<List<Game>> getNextSetGame(
            @Query("fields") String fields,
            @Query("filter[release_dates.date][lte]") String releasedBefore,
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
