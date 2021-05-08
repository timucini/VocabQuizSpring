package vocab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String CorrectAnswer;
    private String WrongAnswer1;
    private String WrongAnswer2;
    private String WrongAnswer3;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Answer> answers =  new ArrayList<>();

    public Question(String questionString, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
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

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public String getWrongAnswer1() {
        return WrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        WrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return WrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        WrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return WrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        WrongAnswer3 = wrongAnswer3;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
