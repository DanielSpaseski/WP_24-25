package mk.ukim.finki.wp.kol2023.g2.service.impl;

import mk.ukim.finki.wp.kol2023.g2.model.Director;
import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.model.Movie;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidMovieIdException;
import mk.ukim.finki.wp.kol2023.g2.repository.MovieRepository;
import mk.ukim.finki.wp.kol2023.g2.service.DirectorService;
import mk.ukim.finki.wp.kol2023.g2.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.kol2023.g2.service.FieldFilterSpecification.*;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final DirectorService directorService;

    public MovieServiceImpl(MovieRepository movieRepository, DirectorService directorService){
        this.movieRepository=movieRepository;
        this.directorService=directorService;
    }
    @Override
    public List<Movie> listAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Movie create(String name, String description, Double rating, Genre genre, Long director) {
        Director director1 = this.directorService.findById(director);
        Movie movie = new Movie(name,description,rating,genre,director1);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, String name, String description, Double rating, Genre genre, Long director) {
        Director director1 = this.directorService.findById(director);
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        movie.setDescription(description);
        movie.setDirector(director1);
        movie.setGenre(genre);
        movie.setRating(rating);
        movie.setName(name);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        this.movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie vote(Long id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        movie.setVotes(movie.getVotes()+1);
        this.movieRepository.save(movie);
        return movie;
    }

    @Override
    public Page<Movie> findPage(Double rating, Genre genre, Integer pageNum, Integer pageSize) {
        Specification<Movie> specification = Specification
                .where(lessThan(Movie.class, "rating", rating))
                .and(filterEqualsV(Movie.class, "genre", genre));

        return this.movieRepository.findAll(
                specification,
                PageRequest.of(pageNum - 1, pageSize)
        );

    }

}
