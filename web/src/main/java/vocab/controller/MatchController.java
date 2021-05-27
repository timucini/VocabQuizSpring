package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vocab.domain.*;
import vocab.services.MatchService;
import vocab.services.MatchServiceImpl;
import vocab.services.VocabularyService;
import vocab.services.VocabularyServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/match")
@CrossOrigin("*")
public class MatchController {

    private final MatchService matchService;
    private final VocabularyService vocabularyService;

    @Autowired
    public MatchController(MatchServiceImpl matchService, VocabularyServiceImpl vocabularyService) {
        this.matchService = matchService;
        this.vocabularyService = vocabularyService;
    }

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getMatches() {
        List<Match> matches =  matchService.getAvailableMatches();
        if (matches != null) {
            return new ResponseEntity<>(matches, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // sollten eig wirklich keine ganzen User rüberschicken sondern nur die userid oder username zb, anstatt im frontend nen ganzen User mitschicken zu müssen im POST Request
    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestParam User user, @RequestParam Book bookName){
        Match createdMatch = matchService.createMatch(user, bookName);
        if (createdMatch != null) {
            return new ResponseEntity<>(createdMatch, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //hier auch besser nur id statt ganzen user
    @PostMapping("/join")
    public ResponseEntity<Match> joinMatch(@RequestParam User user, @RequestParam Long match_id) {
        Match joinedMatch = matchService.joinMatch(user, match_id);
        if (joinedMatch != null) {
            return new ResponseEntity<>(joinedMatch, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/round")
    public ResponseEntity<Round> startRound(@RequestParam Long category_id, @RequestParam Long match_id) {
        Match match = matchService.getMatch(match_id);
        Round round = new Round();
        Category category = vocabularyService.getCategory(category_id);
        round.setCategory(category);
        match.setCurrentRound(round);
        boolean matchUpdated = matchService.updateMatch(match);
        if (round.getCategory() != null && matchUpdated) {
            return new ResponseEntity<>(round, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/answer")
    public ResponseEntity<Boolean> submitAnswer(@RequestParam String answer, @RequestParam Question question, @RequestParam Long match_id, @RequestParam User user) {
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
