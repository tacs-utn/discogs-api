package example;

/**
 * Created by martin on 3/24/16.
 */
public class Song {
    private int id;
    private String artist;
    private int year;
    private String name;

    public Song() {
    }

    public Song(int id, String artist, int year, String name) {
        this.id = id;
        this.artist = artist;
        this.year = year;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
