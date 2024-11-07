package mk.finki.ukim.mk.lab_b.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab_b.model.Artist;
import mk.finki.ukim.mk.lab_b.model.Song;
import mk.finki.ukim.mk.lab_b.service.ArtistService;
import mk.finki.ukim.mk.lab_b.service.impl.ArtistServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ArtistService artistService;

    public ArtistServlet(SpringTemplateEngine templateEngine, ArtistService artistService) {
        this.templateEngine = templateEngine;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artistList = artistService.listArtists();
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("artistList", artistList);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artistList = artistService.listArtists();
        String trackId;
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        if(req.getParameter("songRadio") != null)
            trackId = req.getParameter("songRadio");
        else
            trackId = null;
        context.setVariable("trackId", trackId);
        context.setVariable("artistList", artistList);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }
}
