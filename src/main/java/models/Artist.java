package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by martin on 4/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
