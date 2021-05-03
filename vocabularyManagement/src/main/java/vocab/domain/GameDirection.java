package vocab.domain;

import java.util.ArrayList;
import java.util.List;

public class GameDirection {
    private Long id;
    private String languageFrom;
    private String languageTo;
    private List<Book> books =  new ArrayList<>();
}
