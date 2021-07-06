package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vocab.domain.*;
import vocab.services.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/match")
@CrossOrigin("*")
public class MatchController {

    private final MatchService matchService;
    private final VocabularyService vocabularyService;
    private final UserService userService;

    @Autowired
    public MatchController(MatchService matchService, VocabularyService vocabularyService, UserService userService) {
        this.matchService = matchService;
        this.vocabularyService = vocabularyService;
        this.userService = userService;
    }

    @GetMapping("/matches")
    public ResponseEntity<Object> getMatches(@RequestParam String user_id) {
        try{
            User user = userService.getUserById(Long.parseLong(user_id));
            List<Match> matches =  matchService.getAvailableMatches(user);
            if (matches != null) {
                return new ResponseEntity<>(matches, HttpStatus.OK);
            } else {
                String errorMsg = "No matches found";
                return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            String errorMsg = "Error while getting Data from Database";
            return new ResponseEntity<>(errorMsg,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/match")
    public ResponseEntity<Object> getMatch(@RequestParam String match_id) {
        Match match = matchService.getMatch(Long.parseLong(match_id));
        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            String errorMsg = "Can't get Match from Database";
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createMatch(@RequestParam String user_id, String book_id){
        try {
            User user = userService.getUserById(Long.parseLong(user_id));
            Book book = vocabularyService.getBook(Long.parseLong(book_id));
            Match createdMatch = matchService.createMatch(user, book);
            if (createdMatch != null) {
                return new ResponseEntity<>(createdMatch, HttpStatus.OK);
            } else {
                String errorMsg = "Couldn't create Match";
                return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            String errorMsg = "Error while requesting";
        return new ResponseEntity<>(errorMsg,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/join")
    public ResponseEntity<Object> joinMatch(@RequestParam String user_id, String match_id) {
        try {
            User user = userService.getUserById(Long.parseLong(user_id));
            Match joinedMatch = matchService.joinMatch(user, Long.parseLong(match_id));
            if (joinedMatch != null) {
                return new ResponseEntity<>(joinedMatch, HttpStatus.OK);
            } else {
                String errorMsg = "Error while joining Match";
                return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            String errorMsg = "Error while requesting";
            return new ResponseEntity<>(errorMsg,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/round")
    public ResponseEntity<Object> startRound(@RequestParam String category_id, String match_id) {
        try {
            Match match = matchService.getMatch(Long.parseLong(match_id));
            Category category = vocabularyService.getCategory(Long.parseLong(category_id));
            Round returnRound = matchService.startRound(category,match);
            if (returnRound != null) {
                return new ResponseEntity<>(returnRound, HttpStatus.OK);
            } else {
                String errorMsg = "Error while creating new Round";
                return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            String errorMsg = "Error while requesting";
            return new ResponseEntity<>(errorMsg,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/answer")
    public ResponseEntity<Object> submitAnswer(@RequestParam String answer, Long question_id, Long user_id) {
        Question question = matchService.getQuestion(question_id);
        User user = userService.getUserById(user_id);
        Boolean answerIsCorrect = matchService.submitAnswer(answer, question, user);
        if (answerIsCorrect != null) {
            return new ResponseEntity<>(answerIsCorrect, HttpStatus.OK);
        } else {
            String errorMsg = "Error while requesting";
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/finish")
    private void finishMatch(@RequestParam Long match_id) {
        matchService.finishMatch(match_id);
    }

}
