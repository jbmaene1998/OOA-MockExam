package core.services;

import core.entities.Movie;
import core.interfaces.repositories.MovieRepository;
import core.interfaces.services.MovieService;
import infrastructure.helpers.ServerConnection;
import infrastructure.repositories.MovieRepositoryImpl;
import infrastructure.repositories.mocks.MockMovieRepositoryImpl;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    private final MovieRepository _movieRepository;
    private final MovieRepository _mockMovieRepository;

    public MovieServiceImpl() {
        _movieRepository = new MovieRepositoryImpl();
        _mockMovieRepository = new MockMovieRepositoryImpl();
    }

    @Override
    public List<Movie> getMovies(String query) {
        if (ServerConnection.testConnection()) {
            return _mockMovieRepository.getMovies(query);
        }
        return _movieRepository.getMovies(query);
    }
}
