package infrastructure.repositories.mocks;

import core.entities.Movie;
import core.interfaces.repositories.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MockMovieRepositoryImpl implements MovieRepository {
    @Override
    public List<Movie> getMovies(String query) {
        List<Movie> movies = List.of(
                new Movie(1, "Superman", "https://purepng.com/public/uploads/large/purepng.com-supermansupermanfictional-superherocomic-booksdc-comicscharacterjerry-siegelson-of-kryptonaction-comicsman-of-steel-1701528658027gyai4.png"),
                new Movie(2, "Batman", "https://static3.cbrimages.com/wordpress/wp-content/uploads/2017/04/Bat-Signal.jpg"),
                new Movie(3, "Spider-man", "https://pngimg.com/uploads/spider_man/spider_man_PNG11.png"),
                new Movie(4, "Aqua-man", "https://picfiles.alphacoders.com/377/377745.jpg"),
                new Movie(5, "Ant-man", "https://getwallpapers.com/wallpaper/full/3/7/4/975191-download-free-ant-man-wallpapers-1901x3036-screen.jpg")
        );

        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase()
                        .contains(query))
                .collect(Collectors.toList());
    }
}
