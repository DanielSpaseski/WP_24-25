package mk.finki.ukim.mk.lab_b.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab_b.model.Album;
import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;
import mk.finki.ukim.mk.lab_b.repository.jpa.AlbumRepository;
import mk.finki.ukim.mk.lab_b.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public DataHolder(AlbumRepository albumRepository, SongRepository songRepository){
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @PostConstruct
  public void init(){
        artists = new ArrayList<>();
        artists.add(new Artist(1L,"Fartist1", "Lartist1", "BioArtist1"));
        artists.add(new Artist(2L,"Fartist2", "Lartist2", "BioArtist2"));
        artists.add(new Artist(3L,"Fartist3", "Lartist3", "BioArtist3"));
        artists.add(new Artist(4L,"Fartist4", "Lartist4", "BioArtist4"));
        artists.add(new Artist(5L,"Fartist5", "Lartist5", "BioArtist5"));

        albums = new ArrayList<>();
        if(this.albumRepository.count() == 0) {
            albums.add(new Album("Album1", "Pop", "2000"));
            albums.add(new Album("Album2", "Rock", "2015"));
            albums.add(new Album("Album3", "Rap", "2020"));
            albums.add(new Album("Album4", "Pop", "2005"));
            albums.add(new Album("Album5", "Rock", "2010"));
            albumRepository.saveAll(albums);
        }

        songs = new ArrayList<>();
        if(this.songRepository.count() == 0) {
            songs.add(new Song("tr1", "Title1", "Pop", 2000, albums.get(0)));
            songs.add(new Song("tr2", "Title2", "Pop", 2005, albums.get(3)));
            songs.add(new Song("tr3", "Title3", "Rap", 2010, albums.get(4)));
            songs.add(new Song("tr4", "Title4", "Rock", 2015, albums.get(1)));
            songs.add(new Song("tr5", "Title5", "Rap", 2020, albums.get(2)));
            songRepository.saveAll(songs);
        }
    }

}
