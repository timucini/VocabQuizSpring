package vocab.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Long id;
    private String name;
    private List<Translation> translations =  new ArrayList<>();
}
