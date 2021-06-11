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

    @OneToOne
    @JoinColumn(name = "correctAnswer_id", referencedColumnName = "id")
    private Translation CorrectAnswer;
    @OneToOne
    @JoinColumn(name = "wrongAnswer1_id", referencedColumnName = "id")
    private Translation WrongAnswer1;
    @OneToOne
    @JoinColumn(name = "wrongAnswer2_id", referencedColumnName = "id")
    private Translation WrongAnswer2;
    @OneToOne
    @JoinColumn(name = "wrongAnswer3_id", referencedColumnName = "id")
    private Translation WrongAnswer3;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Answer> answers =  new ArrayList<>();

    public Question(String questionString, Translation correctAnswer, Translation wrongAnswer1, Translation wrongAnswer2, Translation wrongAnswer3) {
        this.questionString = questionString;
        CorrectAnswer = correctAnswer;
        WrongAnswer1 = wrongAnswer1;
        WrongAnswer2 = wrongAnswer2;
        WrongAnswer3 = wrongAnswer3;
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

    public Translation getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(Translation correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public Translation getWrongAnswer1() {
        return WrongAnswer1;
    }

    public void setWrongAnswer1(Translation wrongAnswer1) {
        WrongAnswer1 = wrongAnswer1;
    }

    public Translation getWrongAnswer2() {
        return WrongAnswer2;
    }

    public void setWrongAnswer2(Translation wrongAnswer2) {
        WrongAnswer2 = wrongAnswer2;
    }

    public Translation getWrongAnswer3() {
        return WrongAnswer3;
    }

    public void setWrongAnswer3(Translation wrongAnswer3) {
        WrongAnswer3 = wrongAnswer3;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
