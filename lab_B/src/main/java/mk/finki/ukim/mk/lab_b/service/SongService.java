package mk.finki.ukim.mk.lab_b.service;

import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    void addArtistToSong(Long artistId, Long songId);
    public Song findByTrackId(String trackId);
    void saveSong(Long id, String title, String trackId, String genre, int releaseYear, Long albumId);
    Optional<Song> findById(Long id);
    void deleteById(Long id);
}
