package core.services;

import core.entities.Movie;
import core.interfaces.repositories.MovieRepository;
import core.interfaces.services.MovieService;
import infrastructure.repositories.MovieRepositoryImpl;
import infrastructure.repositories.mocks.MockMovieRepositoryImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.ResourceBundle;

public class MovieServiceImpl implements MovieService {
    private final MovieRepository _movieRepository;
    private final MovieRepository _mockMovieRepository;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("config/server");

    public MovieServiceImpl() {
        _movieRepository = new MovieRepositoryImpl();
        _mockMovieRepository = new MockMovieRepositoryImpl();
    }


    @Override
    public List<Movie> getMovies(String query) {
        if (!testPing(resourceBundle.getString("host.ip"))) {
            return _mockMovieRepository.getMovies(query);
        }
        return _movieRepository.getMovies(query);
    }

    @Override
    public boolean testPing(String ip) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            return inetAddress.isReachable(1000); // Timeout in milliseconds
        } catch (IOException e) {
            // Handle exception or logging here
            return false;
        }
    }
}
