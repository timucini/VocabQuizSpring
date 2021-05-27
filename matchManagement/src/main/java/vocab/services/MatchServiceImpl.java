package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.*;
import vocab.repositories.MatchRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        match.setFinished(false);
        match.setScorePlayer1(0);
        match.setScorePlayer2(2);
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
    public List<Match> getAvailableMatches(User user) {
         List<Match> filteredMatches = matchRepository
                 .findAll()
                 .stream()
                 .filter(m -> m.getPlayer2() == null && m.getFinished() == false && m.getPlayer1() != user)
                 .collect(Collectors.toList());
         return filteredMatches;
    }

    @Override
    public Match joinMatch(User user, Long match_id) {
        Match match = this.getMatch(match_id);
        match.setPlayer2(user);
        Boolean matchUpdated = this.updateMatch(match);
        if (matchUpdated) {
            return match;
        } else {
            return null;
        }
    }

    @Override
    public Boolean submitAnswer(String answer, Question question, Long match_id, User user) {
        Boolean isAnswerCorrect = answer.equals(question.getCorrectAnswer());
        //fehlt nicht noch die Verbindung zur question?
        Answer answerObject = new Answer(answer, isAnswerCorrect, user);
        if (isAnswerCorrect) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void finishMatch(Long match_id) {
        Match match = this.getMatch(match_id);
        match.setFinished(true);
        this.updateMatch(match);
    }


}
