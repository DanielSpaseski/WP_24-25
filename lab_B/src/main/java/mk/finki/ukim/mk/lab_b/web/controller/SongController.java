package mk.finki.ukim.mk.lab_b.web.controller;

import mk.finki.ukim.mk.lab_b.model.Album;
import org.springframework.ui.Model;
import mk.finki.ukim.mk.lab_b.model.Song;
import mk.finki.ukim.mk.lab_b.service.AlbumService;
import mk.finki.ukim.mk.lab_b.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService){
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        List<Song> songs = songService.listSongs();
        model.addAttribute("songs",songs);
        model.addAttribute("error",error);

        return "listSongs";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam(required = false) Long id,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId){
        songService.saveSong(id,title,trackId,genre,releaseYear,albumId);
        return "redirect:/songs";
    }


    @PostMapping("delete/{id}")
    public String deleteSong(@PathVariable Long id){
        songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(Model model,
                                  @PathVariable Long id){
        if(songService.findById(id).isPresent()){
            Song song = songService.findById(id).get();
            List<Album> albums = albumService.findAll();
            model.addAttribute("albums", albums);
            model.addAttribute("song", song);
            return "add-song";

        }
        return "redirect:/songs?error=SongNotFound";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model){
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/song-details/{id}")
    public String songDetails(Model model, @PathVariable Long id){
        if(songService.findById(id).isPresent()){
            Song song = songService.findById(id).get();
            model.addAttribute("song", song);
            return "songDetails";
        }
        return "redirect:/songs?error=SongNotFound";
    }
}
