
package com.clidev.gamecodex.populargamescreen.model.modeldata;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "game")
public class Game {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("aggregated_rating")
    @Expose
    private Double aggregatedRating;
    @SerializedName("developers")
    @Expose
    private List<Long> developers = null;
    @SerializedName("publishers")
    @Expose
    private List<Long> publishers = null;
    @SerializedName("player_perspectives")
    @Expose
    private List<Long> playerPerspectives = null;
    @SerializedName("game_modes")
    @Expose
    private List<Long> gameModes = null;
    @SerializedName("genres")
    @Expose
    private List<Long> genres = null;
    @SerializedName("first_release_date")
    @Expose
    private Long firstReleaseDate;
    @SerializedName("platforms")
    @Expose
    private List<Integer> platforms = null;
    @SerializedName("release_dates")
    @Expose
    private List<ReleaseDate> releaseDates = null;
    @SerializedName("screenshots")
    @Expose
    private List<Screenshot> screenShots = null;
    @SerializedName("artworks")
    @Expose
    private List<Artwork> artworks = null;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("multiplayer_modes")
    @Expose
    private List<MultiplayerMode> multiplayerModes = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Game() {
    }

    /**
     * 
     * @param summary
     * @param multiplayerModes
     * @param aggregatedRating
     * @param firstReleaseDate
     * @param publishers
     * @param artworks
     * @param genres
     * @param videos
     * @param developers
     * @param url
     * @param releaseDates
     * @param id
     * @param cover
     * @param name
     * @param playerPerspectives
     * @param popularity
     * @param gameModes
     */
    public Game(Long id, String name, String url, String summary, Double popularity, Double aggregatedRating, List<Long> developers, List<Long> publishers, List<Long> playerPerspectives, List<Long> gameModes, List<Long> genres, Long firstReleaseDate,List<Integer> plaforms, List<ReleaseDate> releaseDates,List<Screenshot> screenshots, List<Artwork> artworks, List<Video> videos, Cover cover, List<MultiplayerMode> multiplayerModes) {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.summary = summary;
        this.popularity = popularity;
        this.aggregatedRating = aggregatedRating;
        this.developers = developers;
        this.publishers = publishers;
        this.playerPerspectives = playerPerspectives;
        this.gameModes = gameModes;
        this.genres = genres;
        this.firstReleaseDate = firstReleaseDate;
        this.platforms = plaforms;
        this.releaseDates = releaseDates;
        this.screenShots = screenshots;
        this.artworks = artworks;
        this.videos = videos;
        this.cover = cover;
        this.multiplayerModes = multiplayerModes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Double getAggregatedRating() {
        return aggregatedRating;
    }

    public void setAggregatedRating(Double aggregatedRating) {
        this.aggregatedRating = aggregatedRating;
    }

    public List<Long> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Long> developers) {
        this.developers = developers;
    }

    public List<Long> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Long> publishers) {
        this.publishers = publishers;
    }

    public List<Long> getPlayerPerspectives() {
        return playerPerspectives;
    }

    public void setPlayerPerspectives(List<Long> playerPerspectives) {
        this.playerPerspectives = playerPerspectives;
    }

    public List<Long> getGameModes() {
        return gameModes;
    }

    public void setGameModes(List<Long> gameModes) {
        this.gameModes = gameModes;
    }

    public List<Long> getGenres() {
        return genres;
    }

    public void setGenres(List<Long> genres) {
        this.genres = genres;
    }

    public Long getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(Long firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public List<Integer> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Integer> platforms) {
        this.platforms = platforms;
    }

    public List<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleaseDate> releaseDates) {
        this.releaseDates = releaseDates;
    }

    public List<Screenshot> getScreenShots() {
        return screenShots;
    }

    public void setScreenShots(List<Screenshot> screenShots) {
        this.screenShots = screenShots;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public List<MultiplayerMode> getMultiplayerModes() {
        return multiplayerModes;
    }

    public void setMultiplayerModes(List<MultiplayerMode> multiplayerModes) {
        this.multiplayerModes = multiplayerModes;
    }

}
