package mk.ukim.finki.wp.kol2023.g2.web;

import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.model.Movie;
import mk.ukim.finki.wp.kol2023.g2.service.DirectorService;
import mk.ukim.finki.wp.kol2023.g2.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/", "/movies"})
public class MoviesController {

    private final MovieService movieService;
    private final DirectorService directorService;

    public MoviesController(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService=directorService;
    }

    /**
     * This method should use the "list.html" template to display all movies.
     * The method should be mapped on paths '/' and '/movies'.
     * The arguments that this method takes are optional and can be 'null'.
     * In the case when the arguments are not passed (both are 'null') all movies should be displayed.
     * If one, or both of the arguments are not 'null', the movies that are the result of the call
     * to the method 'listMoviesWithRatingGreaterThenAndGenre' from the MovieService should be displayed.
     *
     * @param rating
     * @param genre
     * @return The view "list.html".
     */
    @GetMapping
    public String showMovies(@RequestParam(required = false) Double rating,
                             @RequestParam(required = false) Genre genre,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             Model model) {
        Page<Movie> page = this.movieService.findPage(rating,genre,pageNum,pageSize);
        model.addAttribute("page", page);
        model.addAttribute("rating", rating);
        model.addAttribute("genre", genre);
        model.addAttribute("genres", Genre.values());
        model.addAttribute("directors", this.directorService.listAll());
        return "list";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/movies/add'.
     *
     * @return The view "form.html".
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showAdd(Model model)
    {
        model.addAttribute("genres", Genre.values());
        model.addAttribute("directors", this.directorService.listAll());
        return "form";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the movie that is updated.
     * The method should be mapped on path '/movies/[id]/edit'.
     *
     * @return The view "form.html".
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("movie",this.movieService.findById(id));
        model.addAttribute("genres", Genre.values());
        model.addAttribute("directors", this.directorService.listAll());
        return "form";
    }

    /**
     * This method should create a movie given the arguments it takes.
     * The method should be mapped on path '/movies'.
     * After the movie is created, all movies should be displayed.
     *
     * @return The view "list.html".
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String create(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam Double rating,
                         @RequestParam Genre genre,
                         @RequestParam Long director) {
        this.movieService.create(name, description, rating, genre, director);
        return "redirect:/movies";
    }

    /**
     * This method should update a movie given the arguments it takes.
     * The method should be mapped on path '/movies/[id]'.
     * After the movie is updated, all movies should be displayed.
     *
     * @return The view "list.html".
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam Double rating,
                         @RequestParam Genre genre,
                         @RequestParam Long director) {
        this.movieService.update(id, name, description, rating, genre, director);
        return "redirect:/movies";
    }

    /**
     * This method should delete the movie that has the appropriate identifier.
     * The method should be mapped on path '/movies/[id]/delete'.
     * After the movie is deleted, all movies should be displayed.
     *
     * @return The view "list.html".
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.movieService.delete(id);
        return "redirect:/movies";
    }

    /**
     * This method should increase the number of votes of the appropriate movie by 1.
     * The method should be mapped on path '/movies/[id]/vote'.
     * After the operation, all movies should be displayed.
     *
     * @return The view "list.html".
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}/vote")
    public String vote(@PathVariable Long id) {
        this.movieService.vote(id);
        return "redirect:/movies";
    }
}
