package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.*;
import vocab.repositories.AnswerRepository;
import vocab.repositories.MatchRepository;
import vocab.repositories.QuestionRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.matchRepository = matchRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Match createMatch(User user,Book book) {
        Match match= new Match(user,book);
        match.setFinished(false);
        match.setScorePlayer1(0);
        match.setScorePlayer2(2);
        return matchRepository.save(match);
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
        List<String> possibleAnswers = question.getCorrectAnswer().getStringTo();
        Boolean isAnswerCorrect = possibleAnswers.contains(answer);
        Answer answerObject = new Answer(answer, isAnswerCorrect, user, question);
        Answer submittedAnswer = answerRepository.save(answerObject);
        return submittedAnswer.getCorrect();
    }

    @Override
    public void finishMatch(Long match_id) {
        Match match = this.getMatch(match_id);
        match.setFinished(true);
        this.updateMatch(match);
    }

    @Override
    public Question getQuestion(Long id) {
        return questionRepository.getQuestionById(id);
    }

    @Override
    public Round startRound(Category category, Match match) {
        //
        List<Translation> translations = category.getTranslations();
        List<Question> questionList = new ArrayList<>();
        int questionNumber = translations.size() > 3 ? 3 : translations.size();
        for (int i = 0; i < questionNumber; i++) {
            int otherTranslationIndex = i > translations.size() ? i-1 : i+1;
            Translation translation = translations.get(i);

            Question question = new Question(
                    translation.getStringFrom().get(0),
                    translation,
                    translations.get(0),
                    translations.get(0),
                    translations.get(0));
            questionList.add(question);
        }
        Round round = new Round(questionList,category);
        match.setCurrentRound(round);
        boolean matchUpdated = updateMatch(match);
        if (matchUpdated) {
            return getMatch(match.getId()).getCurrentRound();
        } else {
            return null;
        }
    }


}
