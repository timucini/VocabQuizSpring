package vocab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String from;
    private String to;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Category> categories =  new ArrayList<>();

    public Book(String name, String from, String to, List<Category> categories) {
        this.name = name;
        this.from = from;
        this.to = to;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(name);
        sb.append(System.lineSeparator());
        sb.append(from+" - "+to);
        for (Category category : categories) {
            sb.append(System.lineSeparator());
            sb.append(category.toString());
        }
        return sb.toString();
    }
}