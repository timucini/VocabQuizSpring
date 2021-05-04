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
    public Match createMatch(User user, String bookName) {
        return null;
    }

    @Override
    public Match getMatch(Long id) {
        return null;
    }

    @Override
    public Boolean updateMatch(Match match) {
        return null;
    }

    @Override
    public List<Match> getAvailableMatches() {
        return null;
    }
}
