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


    // BookName??? Not implemented yet
    @Override
    public Match createMatch(User user, String bookName) {
        Match match= new Match(user);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match getMatch(Long id) {
        return matchRepository.findById(id).get();
    }


    @Override
    public Boolean updateMatch(Match match) {
        try {
            matchRepository.saveAndFlush(match);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Match> getAvailableMatches() {
         return matchRepository.findAll();
    }
}
