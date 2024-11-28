package mk.finki.ukim.mk.lab_b.service;

import mk.finki.ukim.mk.lab_b.model.Album;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    public List<Album> findAll();
    Optional<Album> findById(Long id);
}
