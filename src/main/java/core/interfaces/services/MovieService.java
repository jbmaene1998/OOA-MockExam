package core.interfaces.services;

import core.entities.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies(String query);
    boolean testPing(String ip);
}
