package vocab.domain;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "answerPlayer1_id", referencedColumnName = "id")
    private Answer AnswerPlayer1;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "answerPlayer2_id", referencedColumnName = "id")
    private Answer AnswerPlayer2;
    private String questionString;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public Question(String questionString, String answer1, String answer2, String answer3, String answer4) {
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

    public Answer getAnswerPlayer1() {
        return AnswerPlayer1;
    }

    public void setAnswerPlayer1(Answer answerPlayer1) {
        AnswerPlayer1 = answerPlayer1;
    }

    public Answer getAnswerPlayer2() {
        return AnswerPlayer2;
    }

    public void setAnswerPlayer2(Answer answerPlayer2) {
        AnswerPlayer2 = answerPlayer2;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}
