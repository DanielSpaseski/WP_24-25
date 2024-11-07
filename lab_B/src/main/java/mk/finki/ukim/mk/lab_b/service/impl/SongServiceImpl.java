package mk.finki.ukim.mk.lab_b.service.impl;

import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;
import mk.finki.ukim.mk.lab_b.repository.ArtistRepository;
import mk.finki.ukim.mk.lab_b.repository.SongRepository;
import mk.finki.ukim.mk.lab_b.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository){
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }
    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        Artist art = artistRepository.findAll().stream()
                .filter(a -> a.getId().equals(artist.getId()))
                .findFirst().orElse(null);
        Song s = songRepository.findByTrackId(song.getTrackId());
        songRepository.addArtistToSong(art,s);
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }
}
