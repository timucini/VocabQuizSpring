package vocab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Category> categories =  new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "gamedirection_Id")
    private GameDirection gameDirection;

    public Book(String name, GameDirection gameDirection) {
        this.name = name;
        this.gameDirection = gameDirection;
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

    public GameDirection getGameDirection() {
        return gameDirection;
    }

    public void setGameDirection(GameDirection gameDirection) {
        this.gameDirection = gameDirection;
    }
}
