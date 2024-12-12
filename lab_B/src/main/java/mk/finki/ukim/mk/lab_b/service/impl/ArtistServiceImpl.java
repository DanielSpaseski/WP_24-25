package mk.finki.ukim.mk.lab_b.service.impl;

import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.repository.InMemoryArtistRepository;
import mk.finki.ukim.mk.lab_b.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final InMemoryArtistRepository artistRepository;

    public ArtistServiceImpl(InMemoryArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }
    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }
}
