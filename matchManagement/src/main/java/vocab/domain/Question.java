package vocab.domain;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
public class Question {
    private Long id;
    private String questionString;
    private HashMap<String,Boolean> possibleAnswers;
    private Answer answerPlayer1;
    private Answer answerPlayer2;

}
