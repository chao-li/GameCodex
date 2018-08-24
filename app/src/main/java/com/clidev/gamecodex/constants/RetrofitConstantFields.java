package com.clidev.gamecodex.constants;

public class RetrofitConstantFields {

    public static final String igdbBaseUrl = "https://api-endpoint.igdb.com/";
    public static final String FIELDS = "id,name,genres,url,summary,aggregated_rating,platforms,popularity,screenshots.url,cover,developers,publishers,player_perspectives,game_modes,first_release_date,release_dates,artworks,videos,multiplayer_modes";
    public static final int DEFAULT_PLATFORM = 48;
    public static final String ORDER = "popularity:desc";
    public static final int LIMIT = 30;

    public static final String GENRE_BASE_URL = "https://api-endpoint.igdb.com/";
    public static final String NAME = "name";
    public static final int GENRE_LIMIT = 50;

    public static final String COMPANY_FIELDS = "id,name";
    public static final String PLATFORM_FIELDS = "id,name";

    // PLAYSTATION 4
    public static final int PLAYSTATION_4 = 48;
    public static final String ORDER_POPULARITY = "popularity:desc";
    public static final String ORDER_AGGREGATED_RATING = "aggregated_rating:desc";
    public static final String ORDER_RELEASE_DATE = "release_dates.date:asc";

}
