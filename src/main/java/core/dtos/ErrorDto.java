package core.dtos;

import core.dtos.base.BaseDto;

public class ErrorDto extends BaseDto {

    private final String message;

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
