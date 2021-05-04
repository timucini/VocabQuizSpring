package vocab.domain;

import javax.persistence.*;

@Entity
@Table(name="translation")
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stringFrom;
    private String stringTo;

    @ManyToOne
    private Category category;

    public Translation(String stringFrom, String stringTo, Category category) {
        this.stringFrom = stringFrom;
        this.stringTo = stringTo;
        this.category = category;
    }

    public Translation() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringFrom() {
        return stringFrom;
    }

    public void setStringFrom(String stringFrom) {
        this.stringFrom = stringFrom;
    }

    public String getStringTo() {
        return stringTo;
    }

    public void setStringTo(String stringTo) {
        this.stringTo = stringTo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
