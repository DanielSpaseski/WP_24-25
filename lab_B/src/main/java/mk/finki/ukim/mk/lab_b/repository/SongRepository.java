package mk.finki.ukim.mk.lab_b.repository;


import mk.finki.ukim.mk.lab_b.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab_b.model.Album;
import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SongRepository {

    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId){
        return DataHolder.songs.stream()
                .filter(song -> song.getTrackId().equals(trackId))
                .findFirst()
                .orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        song.getPerformers().removeIf(a -> a.getId().equals(artist.getId()));
        song.getPerformers().add(artist);
        return artist;
    }
    public void saveSong(Song song){
        DataHolder.songs.removeIf(s->s.getId().equals(song.getId()));
        DataHolder.songs.add(song);
    }

    public Optional<Song> findById(Long id) {
        return DataHolder.songs.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id){
        DataHolder.songs.removeIf(s -> s.getId().equals(id));
    }

}
