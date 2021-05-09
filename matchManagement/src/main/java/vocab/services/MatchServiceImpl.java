package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.Book;
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
    public Match createMatch(User user,Book book) {
        Match match= new Match(user,book);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match getMatch(Long id) {
//        return matchRepository.findById(id).orElse(null);
        return matchRepository.getMatchById(id);
    }

    @Override
    public Boolean updateMatch(Match match) {
        try {
            matchRepository.save(match);
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
