package mk.finki.ukim.mk.lab_b.web.controller;

import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.service.ArtistService;
import mk.finki.ukim.mk.lab_b.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService){
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/artist-to-song")
    public String artistToSong(Model model, @RequestParam Long songId){
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("songId", songId);
        return "artistsList";
    }

    @PostMapping("/artist-to-song")
    public String artistToSong(@RequestParam Long songId, @RequestParam Long artistId){
        songService.addArtistToSong(artistId,songId);
        return "redirect:/songs/song-details/" + songId;
    }


}
