package mk.finki.ukim.mk.lab_b.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data

public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
    private Long id;
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album){
        Random random = new Random();
        this.id = random.nextLong(10000000);
        this.trackId = trackId;
        this.title = title;
        this.performers = new ArrayList<>();
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
    }
}
