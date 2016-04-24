package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by martin on 4/23/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
    private Integer count;
    private int average;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }
}
