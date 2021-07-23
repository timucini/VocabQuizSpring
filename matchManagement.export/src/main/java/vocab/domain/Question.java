package vocab.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String questionString;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "correctAnswer_id", referencedColumnName = "id")
    private Translation answer1;
    @ManyToOne
    @JoinColumn(name = "wrongAnswer1_id", referencedColumnName = "id")
    private Translation answer2;
    @ManyToOne
    @JoinColumn(name = "wrongAnswer2_id", referencedColumnName = "id")
    private Translation answer3;
    @ManyToOne
    @JoinColumn(name = "wrongAnswer3_id", referencedColumnName = "id")
    private Translation answer4;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Answer> answers =  new ArrayList<>();

    public Question(String questionString, Translation answer1, Translation answer2, Translation answer3, Translation answer4) {
        this.questionString = questionString;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public Question() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public Translation getAnswer1() {
        return answer1;
    }

    public void setAnswer1(Translation answer1) {
        this.answer1 = answer1;
    }

    public Translation getAnswer2() {
        return answer2;
    }

    public void setAnswer2(Translation answer2) {
        this.answer2 = answer2;
    }

    public Translation getAnswer3() {
        return answer3;
    }

    public void setAnswer3(Translation answer3) {
        this.answer3 = answer3;
    }

    public Translation getAnswer4() {
        return answer4;
    }

    public void setAnswer4(Translation answer4) {
        this.answer4 = answer4;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
