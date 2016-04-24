package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;

/**
 * Created by martin on 4/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    private String title;
    private Long id;
    private String thumb;


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
}
