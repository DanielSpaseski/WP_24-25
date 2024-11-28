//package mk.finki.ukim.mk.lab_b.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab_b.model.Artist;
//import mk.finki.ukim.mk.lab_b.model.Song;
//import mk.finki.ukim.mk.lab_b.service.ArtistService;
//import mk.finki.ukim.mk.lab_b.service.SongService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songs/songDetails")
//public class SongDetailsServlet extends HttpServlet {
//    private final SpringTemplateEngine templateEngine;
//    private final SongService songService;
//    private final ArtistService artistService;
//
//    public SongDetailsServlet(SpringTemplateEngine templateEngine, SongService songService, ArtistService artistService) {
//        this.templateEngine = templateEngine;
//        this.songService = songService;
//        this.artistService = artistService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Song song = songService.listSongs().stream()
//                                            .findFirst().orElse(null);
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(req.getServletContext())
//                .buildExchange(req,resp);
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("song", song);
//        templateEngine.process("songDetails.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(req.getServletContext())
//                .buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//        String artistId = req.getParameter("artistId");
//        String trackId = req.getParameter("trackId");
//
//        Song song = null;
//        if (artistId != null && trackId != null) {
//            song = songService.findByTrackId(trackId);
//            Artist artist = artistService.findById(Long.parseLong(artistId));
//            if(!song.getPerformers().contains(artist))
//                songService.addArtistToSong(artist, song);
//        }
//        context.setVariable("song", song);
//        templateEngine.process("songDetails.html", context, resp.getWriter());
//    }
//}
//