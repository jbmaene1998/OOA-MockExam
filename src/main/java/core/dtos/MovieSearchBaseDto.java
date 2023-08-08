package core.dtos;

import core.dtos.base.BaseDto;

public class MovieSearchBaseDto extends BaseDto {

    private final String query;

    public MovieSearchBaseDto(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
