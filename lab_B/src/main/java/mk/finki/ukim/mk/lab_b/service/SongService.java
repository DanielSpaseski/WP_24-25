package mk.finki.ukim.mk.lab_b.service;

import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Optional<Song> findByTrackId(String trackId);
    Optional<Song> findById(Long id);
    void deleteById(Long id);
    void save(Long id, String title, String trackID, String genre, int releaseYear, Long albumID);
    List<Song> findAllByAlbum_Id (Long albumId);

}
