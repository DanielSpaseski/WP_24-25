package mk.finki.ukim.mk.lab_b.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab_b.model.Album;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import mk.finki.ukim.mk.lab_b.model.Song;
import mk.finki.ukim.mk.lab_b.service.AlbumService;
import mk.finki.ukim.mk.lab_b.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public String getSongsPage(@RequestParam(required = false) String error, Model model,
                               @RequestParam(required = false) Long albumID) {
        if(albumID == null || albumID == -1){
            model.addAttribute("songs",songService.listSongs());
        }
        else{
            model.addAttribute("songs",songService.findAllByAlbum_Id(albumID));
        }
        model.addAttribute("error",error);
        model.addAttribute("albums",albumService.findAll());

        return "listSongs";
    }
    @PostMapping("/add")
    public String saveSong(@RequestParam (required = false) Long id,
                               @RequestParam String title,
                               @RequestParam String trackId,
                               @RequestParam String genre,
                               @RequestParam int releaseYear,
                               @RequestParam Long albumId) {

        songService.save(id,title,trackId,genre,releaseYear,albumId);

        return "redirect:/songs";
    }

    @PostMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id){
        songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public String getEditSongForm(Model model,
                                  @PathVariable Long id){

        Song song = songService.findById(id).orElse(null);
        if(song != null){
            model.addAttribute("song",song);
            model.addAttribute("albums",albumService.findAll());
            return "add-song";
        }

        return "redirect:/songs?error=SongNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model){
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }

    @GetMapping("/song-details/{id}")
    public String songDetails(Model model, @PathVariable Long id){
        Song song = songService.findById(id).orElse(null);
        if(song != null){
            model.addAttribute("song", song);
            return "songDetails";
        }
        return "redirect:/songs?error=SongNotFound";
    }
}
