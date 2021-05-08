package vocab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vocab.domain.Book;
import vocab.domain.Match;
import vocab.domain.User;
import vocab.repositories.MatchRepository;
import vocab.services.MatchService;
import vocab.services.MatchServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;
    private MatchService matchService;

    @Before
    public void setUp() {
        MatchServiceImpl matchService = new MatchServiceImpl(matchRepository);
        this.matchService = matchService;

        // Stubbing
        User user = new User("Testuser", "password");
        Book book = new Book();
        Match match = new Match(user, book);
        Match match2 = new Match(user, book);
        ArrayList<Match> matchList = new ArrayList<Match>();
        matchList.add(match);
        matchList.add(match2);
        Long matchId = 1L;
//        Mockito.when(matchService.createMatch(user, book.getName())).thenReturn(match);
//        Mockito.when(matchService.getMatch(matchId)).thenReturn(match);
        Mockito.when(matchService.getAvailableMatches()).thenReturn(matchList);
    }

    @Test
    public void testCreateMatch() {
        User user = new User("Testuser", "password");
        Book book = new Book();
        Match createdMatch = matchService.createMatch(user, book);
        Assert.assertNotNull(createdMatch);
//        Assert.assertEquals("Testuser", matchService.getMatch(1L).getPlayer1().getUserName());
//        Assert.assertEquals(createdMatch, matchService.getMatch(1L));
    }

    @Test
    public void testGetMatch() {
        User user = new User("Testuser", "password");
        Book book = new Book();
        Match match = new Match(user, book);
        Long matchId = 1L;
        Match returnedMatch = matchService.getMatch(matchId);
        Assert.assertEquals(match, returnedMatch);
    }

    @Test
    public void testGetAllMatches() {
        List<Match> foundMatches = matchService.getAvailableMatches();
        User user = new User("Testuser", "password");
        Book book = new Book();
        Match match2 = new Match(user, book);
        Assert.assertNotNull(foundMatches);
//        Assert.assertTrue(foundMatches.contains(match2));
    }




}
