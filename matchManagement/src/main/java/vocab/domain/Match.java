package vocab.domain;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


public class Match {

    private Long id;
    private User player1;
    private User player2;
    private List<Round> playedRound = new ArrayList<>();
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private String bookName;
    private Round currentRound;
    private Boolean finished;

}
