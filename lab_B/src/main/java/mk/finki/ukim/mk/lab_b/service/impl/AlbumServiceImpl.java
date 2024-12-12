package mk.finki.ukim.mk.lab_b.service.impl;

import mk.finki.ukim.mk.lab_b.model.Album;
import mk.finki.ukim.mk.lab_b.repository.InMemoryAlbumRepository;
import mk.finki.ukim.mk.lab_b.repository.jpa.AlbumRepository;
import mk.finki.ukim.mk.lab_b.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    public AlbumServiceImpl(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}
