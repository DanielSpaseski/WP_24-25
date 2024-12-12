package mk.finki.ukim.mk.lab_b.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
@NoArgsConstructor
@Getter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @ManyToOne
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album){
        this.trackId = trackId;
        this.title = title;
        this.album = album;
        this.releaseYear = releaseYear;
        this.genre = genre;

    }
}
