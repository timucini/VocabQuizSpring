package vocab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import vocab.domain.Book;
import vocab.domain.Match;
import vocab.domain.User;
import vocab.repositories.MatchRepository;
import vocab.services.MatchService;
import vocab.services.MatchServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;
    private MatchService matchService;

    @Before
    public void setUp() {
        MatchServiceImpl matchService = new MatchServiceImpl(matchRepository);
        this.matchService = matchService;

        User user = new User("Testuser", "password");
        Book book = new Book();
        Match match = new Match(user, book);
        match.setId(123L);
        ArrayList<Match> matchList = new ArrayList<Match>();
        matchList.add(match);
        Mockito.when(matchService.getAvailableMatches()).thenReturn(matchList);
        Long matchId = 123L;
        Mockito.when(matchService.getMatch(matchId)).thenReturn(match);
    }

    @Test
    public void testCreateMatch() {
        User user = new User("Testuser", "password");
        Book book = new Book();
        //Act
        Match createdMatch = matchService.createMatch(user, book);
        //Assert
        Assert.assertNotNull(createdMatch);
        Assert.assertEquals(user, createdMatch.getPlayer1());
        Assert.assertEquals(book, createdMatch.getBook());
    }

    @Test
    public void testGetAllMatches() {
        Long matchId = 123L;
        //Act
        List<Match> foundMatches = matchService.getAvailableMatches();
        //Assert
        Assert.assertNotNull(foundMatches);
        Assert.assertEquals(matchId, foundMatches.get(0).getId());
    }

    @Test
    public void testUpdateMatch() {
        User user = new User("Testuser", "password");
        Book book = new Book();
        Match match = new Match(user, book);
        //Act
        boolean matchUpdated = this.matchService.updateMatch(match);
        //Assert
        Assert.assertTrue(matchUpdated);
    }

    @Test
    public void testGetMatch() {
        Long matchId = 123L;
        //Act
        Match requestedMatch = matchService.getMatch(matchId);
        //Assert
        Assert.assertNotNull(requestedMatch);
        Assert.assertEquals(matchId, requestedMatch.getId());
    }




}
