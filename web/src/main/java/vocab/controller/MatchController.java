package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vocab.domain.Match;
import vocab.services.MatchService;
import vocab.services.MatchServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/match")
@CrossOrigin("*")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchServiceImpl matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return matchService.getAvailableMatches();
    }

}
