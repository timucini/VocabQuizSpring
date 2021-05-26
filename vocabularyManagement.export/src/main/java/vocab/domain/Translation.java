package vocab.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="translation")
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> stringFrom = new ArrayList<>();

    @ElementCollection
    private List<String> stringTo = new ArrayList<>();

    public Translation(List<String> stringFrom, List<String> stringTo) {
        this.stringFrom = stringFrom;
        this.stringTo = stringTo;
    }

    public Translation() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getStringFrom() {
        return stringFrom;
    }

    public void setStringFrom(List<String> stringFrom) {
        this.stringFrom = stringFrom;
    }

    public List<String> getStringTo() {
        return stringTo;
    }

    public void setStringTo(List<String> stringTo) {
        this.stringTo = stringTo;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "id=" + id +
                ", stringFrom=" + stringFrom +
                ", stringTo=" + stringTo +
                '}';
    }
}
