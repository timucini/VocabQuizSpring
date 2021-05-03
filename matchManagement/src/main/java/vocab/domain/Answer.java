package vocab.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    private Long id;
    private String answer;
    private Boolean correct;

    public Answer(String answer, Boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public Answer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

}
