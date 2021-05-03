package vocab.services;

import org.springframework.stereotype.Service;
import vocab.domain.Match;
import vocab.domain.User;

import java.util.List;


public interface MatchService {
    /**
     * TODO add JAVADOC
     */
    public Match createMatch(User user);

    public Match getMatch(Long id);

    public void setMatch(Match match);

    Boolean updateMatch(Match match);

    List<Match> getAvailableMatches();
}
