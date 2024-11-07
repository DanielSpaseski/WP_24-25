package mk.finki.ukim.mk.lab_b.service;

import mk.finki.ukim.mk.lab_b.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> listArtists();
    Artist findById(Long id);
}
