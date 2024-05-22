package gokhancihan.vet.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {
    private boolean status;
    private String message;
    private String code;
    private T error;

    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public Result(boolean status, String message, String code, T error) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.error = error;
    }
}
