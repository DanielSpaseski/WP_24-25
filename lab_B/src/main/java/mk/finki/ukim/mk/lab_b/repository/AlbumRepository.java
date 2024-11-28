package mk.finki.ukim.mk.lab_b.repository;

import mk.finki.ukim.mk.lab_b.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab_b.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {

    public List<Album> findAll(){
        return DataHolder.albums;
    }
    public Optional<Album> findById(Long id){
        return DataHolder.albums.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }
}
