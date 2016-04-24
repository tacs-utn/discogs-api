package models;

/**
 * Created by martin on 4/23/16.
 */
public class SearchResponse {
    private String title;
    private Long id;
    private String thumb;
    private Integer rating;
    private String artist;

    public SearchResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public SearchResponse(SearchResult searchResult, Integer rating, String artists) {
        this.title  = searchResult.getTitle();
        this.id     = searchResult.getId();
        this.thumb  = searchResult.getThumb();
        this.rating = rating;
        this.artist = artists;
    }

}
