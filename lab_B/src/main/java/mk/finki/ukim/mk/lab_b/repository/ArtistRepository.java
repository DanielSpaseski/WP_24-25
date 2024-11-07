package mk.finki.ukim.mk.lab_b.repository;


import mk.finki.ukim.mk.lab_b.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab_b.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ArtistRepository {
    public List<Artist> findAll(){
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id){
        return DataHolder.artists.stream()
                .filter(artist -> artist.getId().equals(id))
                .findFirst();
    }
}
