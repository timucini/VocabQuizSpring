package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.Match;
import vocab.domain.User;
import vocab.repositories.MatchRepository;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match createMatch(User user) {
        Match match= new Match(user);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match getMatch(Long id) {
        return null;
    }

    @Override
    public void setMatch(Match match) {

    }

    @Override
    public Boolean updateMatch(Match match) {
        /**
         * Just for Testin -> normally need to update not! safe
         */
        matchRepository.save(match);
        return true;
    }

    @Override
    public List<Match> getAvailableMatches() {
        return null;
    }
}
