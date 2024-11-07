package mk.finki.ukim.mk.lab_b.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        artists.add(new Artist(1L,"Fartist1", "Lartist1", "BioArtist1"));
        artists.add(new Artist(2L,"Fartist2", "Lartist2", "BioArtist2"));
        artists.add(new Artist(3L,"Fartist3", "Lartist3", "BioArtist3"));
        artists.add(new Artist(4L,"Fartist4", "Lartist4", "BioArtist4"));
        artists.add(new Artist(5L,"Fartist5", "Lartist5", "BioArtist5"));

        songs = new ArrayList<>();
        songs.add(new Song("tr1","Title1","Pop",2000,new ArrayList<Artist>()));
        songs.add(new Song("tr2","Title2","Pop",2005,new ArrayList<Artist>()));
        songs.add(new Song("tr3","Title3","Rap",2010,new ArrayList<Artist>()));
        songs.add(new Song("tr4","Title4","Rock",2015,new ArrayList<Artist>()));
        songs.add(new Song("tr5","Title5","Rap",2020,new ArrayList<Artist>()));
    }

}