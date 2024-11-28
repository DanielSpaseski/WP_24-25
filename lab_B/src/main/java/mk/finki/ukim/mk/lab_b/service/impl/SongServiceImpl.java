package mk.finki.ukim.mk.lab_b.service.impl;

import mk.finki.ukim.mk.lab_b.model.Album;
import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;
import mk.finki.ukim.mk.lab_b.repository.AlbumRepository;
import mk.finki.ukim.mk.lab_b.repository.ArtistRepository;
import mk.finki.ukim.mk.lab_b.repository.SongRepository;
import mk.finki.ukim.mk.lab_b.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository){
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }
    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public void addArtistToSong(Long artistId, Long songId) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        addArtist(artist,song);
    }
    public void addArtist(Artist artist, Song song){
        songRepository.addArtistToSong(artist,song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void saveSong(Long id, String title, String trackId, String genre, int releaseYear, Long albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow();
        Song song = new Song(trackId,title,genre,releaseYear,album);
        if(id!=null){
            song.setId(id);
            song.setPerformers(songRepository.findById(id).orElseThrow().getPerformers());
        }
        songRepository.saveSong(song);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
