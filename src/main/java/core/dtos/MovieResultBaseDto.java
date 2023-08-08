package core.dtos;

import core.dtos.base.BaseDto;
import core.entities.Movie;

import java.util.ArrayList;

public class MovieResultBaseDto extends BaseDto {

    private final ArrayList<Movie> results;

    public MovieResultBaseDto(ArrayList<Movie> results) {
        this.results = results;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieResultMessage{" +
                "results=" + results +
                '}';
    }
}
