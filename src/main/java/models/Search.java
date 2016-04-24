package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;

/**
 * Created by martin on 4/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {
    private Collection<SearchResult> results;

    public Collection<SearchResult> getResults() {
        return results;
    }

    public void setResults(Collection<SearchResult> results) {
        this.results = results;
    }
}
