package proj.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "data entered incorrectly")
public class IncorrectDataEnteredException extends DataAccessException {
    public IncorrectDataEnteredException(String msg) {
        super(msg);
    }

    public IncorrectDataEnteredException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
