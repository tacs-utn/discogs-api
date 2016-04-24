package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by martin on 4/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Community {
    private Rating rating;
    private Integer want;
    private Integer have;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Integer getWant() {
        return want;
    }

    public void setWant(Integer want) {
        this.want = want;
    }

    public Integer getHave() {
        return have;
    }

    public void setHave(Integer have) {
        this.have = have;
    }
}
