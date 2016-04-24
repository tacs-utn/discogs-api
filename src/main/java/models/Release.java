package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;

/**
 * Created by martin on 4/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Release {
    private Community community;
    private Long id;
    private Collection<Artist> artists;

    public Collection<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Collection<Artist> artists) {
        this.artists = artists;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomRating(){
        return community.getRating().getAverage() + community.getWant() + community.getHave();
    }

    public String getArtistsNames(){
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for(Artist s: artists) {
            sb.append(sep).append(s.getName());
            sep = " & ";
        }
        return sb.toString();
    }
}
