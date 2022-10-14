package proj.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "no such element exists")
public class EmptyDataException extends DataAccessException {

    public EmptyDataException (String msg) {
        super(msg);
    }
    public EmptyDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
