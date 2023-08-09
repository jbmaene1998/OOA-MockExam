package core.services;

import core.entities.Movie;
import core.interfaces.repositories.MovieRepository;
import core.interfaces.services.MovieService;
import infrastructure.helpers.ServerConnection;
import infrastructure.repositories.MovieRepositoryImpl;
import infrastructure.repositories.mocks.MockMovieRepositoryImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.ResourceBundle;

public class MovieServiceImpl implements MovieService {
    private final MovieRepository _movieRepository;
    private final MovieRepository _mockMovieRepository;

    public MovieServiceImpl() {
        _movieRepository = new MovieRepositoryImpl();
        _mockMovieRepository = new MockMovieRepositoryImpl();
    }

    @Override
    public List<Movie> getMovies(String query) {
        if (ServerConnection.TestConnection()) {
            return _mockMovieRepository.getMovies(query);
        }
        return _movieRepository.getMovies(query);
    }
}
