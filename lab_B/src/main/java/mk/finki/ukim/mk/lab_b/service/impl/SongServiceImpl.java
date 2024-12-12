package mk.finki.ukim.mk.lab_b.service.impl;

import mk.finki.ukim.mk.lab_b.model.Album;
import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;
import mk.finki.ukim.mk.lab_b.model.exceptions.NoAlbumFoundException;
import mk.finki.ukim.mk.lab_b.model.exceptions.NoSongFoundException;
import mk.finki.ukim.mk.lab_b.repository.InMemoryAlbumRepository;
import mk.finki.ukim.mk.lab_b.repository.InMemoryArtistRepository;
import mk.finki.ukim.mk.lab_b.repository.InMemorySongRepository;
import mk.finki.ukim.mk.lab_b.repository.jpa.AlbumRepository;
import mk.finki.ukim.mk.lab_b.repository.jpa.SongRepository;
import mk.finki.ukim.mk.lab_b.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository){
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }
    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }


    @Override
    public Optional<Song> findByTrackId(String trackId) { return songRepository.findByTrackId(trackId); }


    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void save(Long id, String title, String trackID, String genre, int releaseYear, Long albumID) {
        Album album = albumRepository.findById(albumID).orElseThrow();
        if(id == null){
            Song newSong = new Song(trackID,title,genre,releaseYear,album);
            songRepository.save(newSong);
        }
        else{
            Song song = songRepository.findById(id).orElseThrow();
            song.setAlbum(album);
            song.setTitle(title);
            song.setTrackId(trackID);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
            songRepository.save(song);
        }
    }

    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }
}
