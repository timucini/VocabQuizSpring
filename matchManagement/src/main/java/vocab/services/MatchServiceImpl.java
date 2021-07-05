package vocab.services;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.*;
import vocab.exceptions.ResourceNotFoundException;
import vocab.repositories.AnswerRepository;
import vocab.repositories.MatchRepository;
import vocab.repositories.QuestionRepository;

import javax.persistence.OptimisticLockException;
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
        match.setScorePlayer2(0);
        return matchRepository.save(match);
    }

    @Override
    public Match getMatch(Long id) throws ResourceNotFoundException {
        try {
            return matchRepository.getMatchById(id);
        }catch (RuntimeException e) {
            throw new ResourceNotFoundException("match with id: "+id.toString()+" not found");
        }
    }

    @Override
    public void updateMatch(Match match) throws OptimisticLockException {
        matchRepository.save(match);
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
    public Match joinMatch(User user, Long match_id) throws OptimisticLockException, ResourceNotFoundException {
        Match match = this.getMatch(match_id);
        match.setPlayer2(user);
        this.updateMatch(match);
        return match;
    }

    @Override
    public Boolean submitAnswer(String answer, Question question, User user) {
        List<String> possibleAnswers = question.getCorrectAnswer().getStringTo();
        Boolean isAnswerCorrect = possibleAnswers.contains(answer);
        Answer answerObject = new Answer(answer, isAnswerCorrect, user, question);
        Answer submittedAnswer = answerRepository.save(answerObject);
        return submittedAnswer.getCorrect();
    }

    @Override
    public void finishMatch(Long match_id) throws ResourceNotFoundException, OptimisticLockException {
        Match match = this.getMatch(match_id);
        match.setFinished(true);
        this.updateMatch(match);
    }

    @Override
    public Question getQuestion(Long id) throws ResourceNotFoundException{
        try {
            return questionRepository.getQuestionById(id);
        }catch (RuntimeException e) {
            throw new ResourceNotFoundException("question with id: "+id.toString()+" not found");
        }
    }

    @Override
    public Round startRound(Category category, Match match) throws OptimisticLockException, ResourceNotFoundException {
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
        updateMatch(match);
        return getMatch(match.getId()).getCurrentRound();
    }
}
