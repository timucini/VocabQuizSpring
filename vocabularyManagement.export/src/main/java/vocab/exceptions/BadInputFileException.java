package vocab.exceptions;

import java.io.IOException;

public class BadInputFileException extends IOException {

    public BadInputFileException(String message) {
        super(message);
    }
}
