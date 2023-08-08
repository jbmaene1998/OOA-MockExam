import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import core.entities.Movie;
import core.interfaces.repositories.MovieRepository;
import core.interfaces.services.MovieService;
import core.services.MovieServiceImpl;
import infrastructure.repositories.mocks.MockMovieRepositoryImpl;
import infrastructure.repositories.MovieRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MovieServiceImplTest {

    private MovieService movieService;
    private MovieRepository realRepositoryMock;
    private MovieRepository mockRepositoryMock;

    @BeforeEach
    public void setUp() {
        realRepositoryMock = mock(MovieRepositoryImpl.class);
        mockRepositoryMock = mock(MockMovieRepositoryImpl.class);
        movieService = new MovieServiceImpl();
    }

    @Test
    public void testGetMoviesWithRealRepository() {
        // Set up mock behavior for real repository
        when(realRepositoryMock.getMovies("query")).thenReturn(Arrays.asList(
                new Movie(1, "Superman", "https://purepng.com/public/uploads/large/purepng.com-supermansupermanfictional-superherocomic-booksdc-comicscharacterjerry-siegelson-of-kryptonaction-comicsman-of-steel-1701528658027gyai4.png"),
                new Movie(2, "Batman", "https://static3.cbrimages.com/wordpress/wp-content/uploads/2017/04/Bat-Signal.jpg")
        ));

        List<Movie> result = realRepositoryMock.getMovies("query");

        assertEquals(2, result.size());
        assertEquals("Superman", result.get(0).getTitle());
        assertEquals("Batman", result.get(1).getTitle());
        verify(realRepositoryMock, times(1)).getMovies("query");
        verify(mockRepositoryMock, never()).getMovies(anyString());
    }

    @Test
    public void testGetMoviesWithMockRepository() {
        // Set up mock behavior for mock repository
        when(mockRepositoryMock.getMovies("query")).thenReturn(Arrays.asList(
                new Movie(1, "Superman", "https://purepng.com/public/uploads/large/purepng.com-supermansupermanfictional-superherocomic-booksdc-comicscharacterjerry-siegelson-of-kryptonaction-comicsman-of-steel-1701528658027gyai4.png"),
                new Movie(2, "Batman", "https://static3.cbrimages.com/wordpress/wp-content/uploads/2017/04/Bat-Signal.jpg")
        ));

        List<Movie> result = mockRepositoryMock.getMovies("query");

        assertEquals(2, result.size());
        assertEquals("Superman", result.get(0).getTitle());
        verify(realRepositoryMock, never()).getMovies(anyString());
        verify(mockRepositoryMock, times(1)).getMovies("query");
    }

    @Test
    public void testPingReachable() {
        assertTrue(movieService.testPing("127.0.0.1"));
    }

    @Test
    public void testPingUnreachable() {
        assertFalse(movieService.testPing("invalid-ip"));
    }
}
