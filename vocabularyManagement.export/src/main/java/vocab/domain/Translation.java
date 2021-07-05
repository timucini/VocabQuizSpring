package vocab.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="translation")
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

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
        StringBuffer fromBuffer = new StringBuffer();
        StringBuffer toBuffer = new StringBuffer();
        for (String string: stringFrom) {
            fromBuffer.append("<"+string+">");
        }
        for (String string: stringTo) {
            toBuffer.append("<"+string+">");
        }
        StringBuffer sb = new StringBuffer();
        sb.append(fromBuffer);
        sb.append(" - ");
        sb.append(toBuffer);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translation that = (Translation) o;
        return stringFrom.equals(that.stringFrom) && stringTo.equals(that.stringTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringFrom, stringTo);
    }
}
