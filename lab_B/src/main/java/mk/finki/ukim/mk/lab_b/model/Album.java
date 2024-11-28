package mk.finki.ukim.mk.lab_b.model;

import lombok.Data;

import java.util.Random;

@Data
public class Album {
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;

    public Album(String name, String genre, String releaseYear){
        Random random = new Random();
        this.id = random.nextLong(100000000);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}
