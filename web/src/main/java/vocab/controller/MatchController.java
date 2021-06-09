package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vocab.domain.*;
import vocab.services.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/match")
@CrossOrigin("*")
public class MatchController {

    private final MatchService matchService;
    private final VocabularyService vocabularyService;
    private final UserService userService;

    @Autowired
    public MatchController(MatchServiceImpl matchService, VocabularyServiceImpl vocabularyService, UserServiceImpl userService) {
        this.matchService = matchService;
        this.vocabularyService = vocabularyService;
        this.userService = userService;
    }

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getMatches(@RequestParam String user_id) {
        try{
            User user = userService.getUserById(Long.parseLong(user_id));
            List<Match> matches =  matchService.getAvailableMatches(user);
            if (matches != null) {
                return new ResponseEntity<>(matches, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestParam String user_id, String book_id){
        try {
            User user = userService.getUserById(Long.parseLong(user_id));
            Book book = vocabularyService.getBook(Long.parseLong(book_id));
            Match createdMatch = matchService.createMatch(user, book);
            if (createdMatch != null) {
                return new ResponseEntity<>(createdMatch, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/join")
    public ResponseEntity<Match> joinMatch(@RequestParam String user_id, String match_id) {
        try {
            User user = userService.getUserById(Long.parseLong(user_id));
            Match joinedMatch = matchService.joinMatch(user, Long.parseLong(match_id));
            if (joinedMatch != null) {
                return new ResponseEntity<>(joinedMatch, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/round")
    public ResponseEntity<Round> startRound(@RequestParam String category_id, String match_id) {
        try {
            Match match = matchService.getMatch(Long.parseLong(match_id));
            Category category = vocabularyService.getCategory(Long.parseLong(category_id));
            //
            List<Translation> translations = category.getTranslations();
            List<Question> questionList = new ArrayList<>();
            int questionNumber = translations.size() > 3 ? 3 : translations.size();
            for (int i = 0; i < questionNumber; i++) {
                int otherTranslationIndex = i > translations.size() ? i-1 : i+1;
                Translation translation = translations.get(i);

                Question question = new Question(
                        translation.getStringFrom().get(0),
                        translation.getStringTo().get(0),
                        translations.get(0).getStringFrom().get(0),
                        translations.get(0).getStringFrom().get(0),
                        translations.get(0).getStringTo().get(0));
                questionList.add(question);
            }
            Round round = new Round(questionList,category);
            match.setCurrentRound(round);
            /**
                TODO: Generate Questions for Category
                -> pass new Round with generated questions
             */
//            vocabularyService.getQuestions(category_id);
//            round.setCategory(category);
//            match.setCurrentRound(round);
            boolean matchUpdated = matchService.updateMatch(match);
            Round returnRound = matchService.getMatch(Long.parseLong(match_id)).getCurrentRound();
            if (round.getCategory() != null && matchUpdated) {
                return new ResponseEntity<>(returnRound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/answer")
    public ResponseEntity<Boolean> submitAnswer(@RequestParam String answer, Long question_id, Long match_id, Long user_id) {
        Question question = matchService.getQuestion(question_id);
        User user = userService.getUserById(user_id);
        Boolean answerIsCorrect = matchService.submitAnswer(answer, question, match_id, user);
        if (answerIsCorrect != null) {
            return new ResponseEntity<>(answerIsCorrect, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/finish")
    private void finishMatch(@RequestParam Long match_id) {
        matchService.finishMatch(match_id);
    }

}
