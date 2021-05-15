package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vocab.domain.*;
import vocab.services.MatchService;
import vocab.services.MatchServiceImpl;
import vocab.services.VocabularyService;
import vocab.services.VocabularyServiceImpl;

import java.util.List;

@RestController
public class MatchController {

    private final MatchService matchService;
    private final VocabularyService vocabularyService;

    @Autowired
    public MatchController(MatchServiceImpl matchService, VocabularyServiceImpl vocabularyService) {
        this.matchService = matchService;
        this.vocabularyService = vocabularyService;
    }

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return (List<Match>) matchService.getAvailableMatches();
    }

    // sollten eig wirklich keine ganzen User rüberschicken sondern nur die userid oder username zb, anstatt im frontend nen ganzen User mitschicken zu müssen im POST Request
    @PostMapping("/match")
    public Match createMatch(@RequestParam User user, @RequestParam Book bookName){
        return matchService.createMatch(user, bookName);
    }

    //hier auch besser nur id statt ganzen user
    @PostMapping("/join")
    public Boolean joinMatch(@RequestParam User user, @RequestParam Long match_id) {
        Match match = matchService.getMatch(match_id);
        match.setPlayer2(user);
        return matchService.updateMatch(match);
    }

    @PostMapping("/round")
    public Round startRound(@RequestParam Long category_id, @RequestParam Long match_id) {
        Match match = matchService.getMatch(match_id);
        Round round = new Round();
        Category category = vocabularyService.getCategory(category_id);
        round.setCategory(category);
        match.setCurrentRound(round);
        matchService.updateMatch(match);
        return round;
    }

    @PostMapping("/answer")
    public void submitAnswer(@RequestParam String answer) {

    }

    private void finishMatch(Match match) {
        match.setFinished(true);
        matchService.updateMatch(match);
    }

}
