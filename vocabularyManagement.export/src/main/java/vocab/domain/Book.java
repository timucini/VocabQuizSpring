package vocab.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String languageFrom;

    private String languageTo;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Category> categories =  new ArrayList<>();

    public Book(String name, String languageFrom, String languageTo, List<Category> categories) {
        this.name = name;
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
        this.categories = categories;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(String languageFrom) {
        this.languageFrom = languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public void setLanguageTo(String languageTo) {
        this.languageTo = languageTo;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("<<Book>>");
        sb.append(System.lineSeparator());
        sb.append(name);
        sb.append(System.lineSeparator());
        sb.append(languageFrom+" - "+languageTo);
        for (Category category : categories) {
            sb.append(System.lineSeparator());
            sb.append(category.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) && languageFrom.equals(book.languageFrom) && languageTo.equals(book.languageTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, languageFrom, languageTo);
    }
}
