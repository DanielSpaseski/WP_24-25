package mk.finki.ukim.mk.lab_b.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    public Artist(Long id, String firstName, String lastName, String bio){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }
}
