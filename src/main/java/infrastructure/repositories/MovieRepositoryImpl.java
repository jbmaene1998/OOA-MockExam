package infrastructure.repositories;

import core.interfaces.repositories.MovieRepository;
import core.entities.Movie;
import core.dtos.ErrorDto;
import core.dtos.base.BaseDto;
import core.dtos.MovieResultBaseDto;
import core.dtos.MovieSearchBaseDto;
import core.exceptions.MovieException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieRepositoryImpl implements MovieRepository {

    private static final Logger LOGGER = Logger.getLogger(MovieRepositoryImpl.class.getName());
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config/server");

    @Override
    public List<Movie> getMovies(String query){
        try (Socket socket = new Socket(RESOURCE_BUNDLE.getString("host.ip"), Integer.parseInt(RESOURCE_BUNDLE.getString("host.port")))){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(new MovieSearchBaseDto(query));
            BaseDto response = (BaseDto) in.readObject();


            if (response instanceof MovieResultBaseDto){
                return ((MovieResultBaseDto) response).getResults();
            } else {
                LOGGER.log(Level.SEVERE, "Failed server: {0}", ((ErrorDto) response).getMessage());
                throw new MovieException(((ErrorDto) response).getMessage());
            }

        } catch (IOException | ClassNotFoundException ex){
            LOGGER.log(Level.SEVERE, "Failed to receive movies", ex);
            throw new MovieException("Can't receive movies");
        }
    }
}
