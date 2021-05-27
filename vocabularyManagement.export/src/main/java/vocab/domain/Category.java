package vocab.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Translation> translations =  new ArrayList<>();


    public Category(String name, List<Translation> translations) {
        this.name = name;
        this.translations = translations;
    }

    public Category() {

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

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("<<Category>>");
        sb.append(System.lineSeparator());
        sb.append(name);
        for (Translation translation : translations) {
            sb.append(System.lineSeparator());
            sb.append("   "+translation.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
