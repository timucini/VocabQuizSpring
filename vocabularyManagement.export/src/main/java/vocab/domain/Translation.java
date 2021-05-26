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

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TranslationEntry> toList =  new ArrayList<>();
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TranslationEntry> fromList =  new ArrayList<>();

    public Translation(List<TranslationEntry> fromList, List<TranslationEntry> toList) {
        this.toList = toList;
        this.fromList = fromList;
    }

    public Translation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TranslationEntry> getToList() {
        return toList;
    }

    public void setToList(List<TranslationEntry> toList) {
        this.toList = toList;
    }

    public List<TranslationEntry> getFromList() {
        return fromList;
    }

    public void setFromList(List<TranslationEntry> fromList) {
        this.fromList = fromList;
    }

    @Override
    public String toString() {
        StringBuffer fromBuffer = new StringBuffer();
        StringBuffer toBuffer = new StringBuffer();
        for (TranslationEntry translationEntry: fromList) {
            fromBuffer.append(translationEntry.toString());
        }
        for (TranslationEntry translationEntry: toList) {
            toBuffer.append(translationEntry.toString());
        }
        StringBuffer sb = new StringBuffer();
        sb.append(fromBuffer);
        sb.append(" - ");
        sb.append(toBuffer);
        return sb.toString();
    }

}
