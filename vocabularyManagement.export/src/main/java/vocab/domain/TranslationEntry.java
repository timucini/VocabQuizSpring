package vocab.domain;

import javax.persistence.*;

@Entity
@Table(name="translationentry")
public class TranslationEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;

    public TranslationEntry(String value) {
        this.value = value;
    }

    public TranslationEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{"+value+"}";
    }
}
