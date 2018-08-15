
package com.clidev.gamecodex.populargames.model.modeldata;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
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
    private List<Integer> developers = null;
    @SerializedName("publishers")
    @Expose
    private List<Integer> publishers = null;
    @SerializedName("player_perspectives")
    @Expose
    private List<Integer> playerPerspectives = null;
    @SerializedName("game_modes")
    @Expose
    private List<Integer> gameModes = null;
    @SerializedName("genres")
    @Expose
    private List<Integer> genres = null;
    @SerializedName("first_release_date")
    @Expose
    private Long firstReleaseDate;
    @SerializedName("release_dates")
    @Expose
    private List<ReleaseDate> releaseDates = null;
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
    public final static Creator<Game> CREATOR = new Creator<Game>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return (new Game[size]);
        }

    }
    ;

    protected Game(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Double) in.readValue((Integer.class.getClassLoader())));
        this.aggregatedRating = ((Double) in.readValue((Double.class.getClassLoader())));

        if (this.developers != null) {
            in.readList(this.developers, (Integer.class.getClassLoader()));
        } else {
            List<Integer> dummyInteger = new ArrayList<>();
            in.readList(dummyInteger, (Integer.class.getClassLoader()));
        }

        if (this.publishers != null) {
            in.readList(this.publishers, (Integer.class.getClassLoader()));
        }

        if (this.playerPerspectives != null) {
            in.readList(this.playerPerspectives, (Integer.class.getClassLoader()));
        }

        if (this.gameModes != null) {
            in.readList(this.gameModes, (Integer.class.getClassLoader()));
        }

        if (this.genres != null) {
            in.readList(this.genres, (Integer.class.getClassLoader()));
        }

        this.firstReleaseDate = ((Long) in.readLong());

        if (this.releaseDates != null) {
            in.readList(this.releaseDates, (com.clidev.gamecodex.populargames.model.modeldata.ReleaseDate.class.getClassLoader()));
        }

        if (this.artworks != null) {
            in.readList(this.artworks, (Artwork.class.getClassLoader()));
        }

        if (this.videos != null) {
            in.readList(this.videos, (com.clidev.gamecodex.populargames.model.modeldata.Video.class.getClassLoader()));
        }

        this.cover = ((Cover) in.readValue((Cover.class.getClassLoader())));

        if (this.multiplayerModes != null) {
            in.readList(this.multiplayerModes, (com.clidev.gamecodex.populargames.model.modeldata.MultiplayerMode.class.getClassLoader()));
        }
    }

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
    public Game(Integer id, String name, String url, String summary, Double popularity, Double aggregatedRating, List<Integer> developers, List<Integer> publishers, List<Integer> playerPerspectives, List<Integer> gameModes, List<Integer> genres, Long firstReleaseDate, List<ReleaseDate> releaseDates, List<Artwork> artworks, List<Video> videos, Cover cover, List<MultiplayerMode> multiplayerModes) {
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
        this.releaseDates = releaseDates;
        this.artworks = artworks;
        this.videos = videos;
        this.cover = cover;
        this.multiplayerModes = multiplayerModes;
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

    public List<Integer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Integer> developers) {
        this.developers = developers;
    }

    public List<Integer> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Integer> publishers) {
        this.publishers = publishers;
    }

    public List<Integer> getPlayerPerspectives() {
        return playerPerspectives;
    }

    public void setPlayerPerspectives(List<Integer> playerPerspectives) {
        this.playerPerspectives = playerPerspectives;
    }

    public List<Integer> getGameModes() {
        return gameModes;
    }

    public void setGameModes(List<Integer> gameModes) {
        this.gameModes = gameModes;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public Long getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(Long firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public List<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleaseDate> releaseDates) {
        this.releaseDates = releaseDates;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(url);
        dest.writeValue(summary);
        dest.writeValue(popularity);
        dest.writeValue(aggregatedRating);
        dest.writeList(developers);
        dest.writeList(publishers);
        dest.writeList(playerPerspectives);
        dest.writeList(gameModes);
        dest.writeList(genres);
        dest.writeLong(firstReleaseDate);
        dest.writeList(releaseDates);
        dest.writeList(artworks);
        dest.writeList(videos);
        dest.writeValue(cover);
        dest.writeList(multiplayerModes);
    }

    public int describeContents() {
        return  0;
    }

}
