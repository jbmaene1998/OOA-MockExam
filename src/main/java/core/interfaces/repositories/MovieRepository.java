package core.interfaces.repositories;

import core.entities.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies(String query);
}
