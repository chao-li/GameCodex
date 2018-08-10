
package com.clidev.gamecodex.populargames.model.modeldata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("genres")
    @Expose
    private List<Integer> genres = null;
    @SerializedName("cover")
    @Expose
    private Cover cover;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Game() {
    }

    /**
     * 
     * @param id
     * @param cover
     * @param genres
     * @param name
     * @param popularity
     */
    public Game(Integer id, String name, Double popularity, List<Integer> genres, Cover cover) {
        super();
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.genres = genres;
        this.cover = cover;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

}
