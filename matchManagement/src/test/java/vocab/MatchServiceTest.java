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
import vocab.repositories.AnswerRepository;
import vocab.repositories.MatchRepository;
import vocab.repositories.QuestionRepository;
import vocab.services.MatchService;
import vocab.services.MatchServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {
    @Mock
    MatchRepository mockedMatchRepository;
    @Mock
    AnswerRepository mockedAnswerRepository;
    @Mock
    QuestionRepository mockedQuestionRepository;

    MatchService matchService;

    @Before
    public void setUp() {
        matchService = new MatchServiceImpl(mockedMatchRepository, mockedAnswerRepository, mockedQuestionRepository);
    }

    @Test
    public void testCreateMatch(){
        //Arrange
        User testUser = new User();
        Book testBook = new Book();
        Mockito.when(mockedMatchRepository.save(Mockito.any(Match.class))).then(returnsFirstArg());
        //Act
        Match testMatch = matchService.createMatch(testUser, testBook);
        //Assert
        Assert.assertNotNull(testMatch);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(Mockito.any(Match.class));
    }

    @Test
    public void testGetMatch(){
        //Arrange
        Match testMatch = new Match();
        long id = 123L;
        testMatch.setId(id);
        Mockito.when(mockedMatchRepository.getMatchById(id)).thenReturn(testMatch);
        //Act
        Match requestedMatch = matchService.getMatch(id);
        //Assert
        Assert.assertEquals(id, requestedMatch.getId().longValue());
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
    }

    @Test
    public void testGetMatchNull(){
        //Arrange
        long id = 123L;
        Mockito.when(mockedMatchRepository.getMatchById(id)).thenReturn(null);
        //Act
        Match requestedMatch = matchService.getMatch(id);
        //Assert
        Assert.assertNull(requestedMatch);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
    }

    @Test
    public void testUpdateMatch(){
        //Arrange
        Match testMatch = new Match();
        Mockito.when(mockedMatchRepository.save(testMatch)).thenReturn(null);
        //Act
        Boolean isChanged = matchService.updateMatch(testMatch);
        //Assert
        Assert.assertTrue(isChanged);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(testMatch);
    }

    @Test
    public void testUpdateMatchNoChange(){
        //Arrange
        Match testMatch = new Match();
        Mockito.when(mockedMatchRepository.save(testMatch)).thenThrow(new RuntimeException());
        //Act
        Boolean isChanged = matchService.updateMatch(testMatch);
        //Assert
        Assert.assertFalse(isChanged);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(testMatch);
    }

    @Test
    public void testGetAvailableMatches(){
        //Arrange
        Match testMatch = new Match(new User(), new Book());
        Mockito.when(mockedMatchRepository.findAll()).thenReturn(Arrays.asList(testMatch));
        //Act
        List<Match> matches = matchService.getAvailableMatches(new User());
        //Assert
        Assert.assertEquals(testMatch, matches.get(0));
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetAvailableMatchesEmpty(){
        //Arrange
        User testUser = new User();
        Mockito.when(mockedMatchRepository.findAll()).thenReturn(new ArrayList<>());
        //Act
        List<Match> matches = matchService.getAvailableMatches(testUser);
        //Assert
        Assert.assertTrue(matches.isEmpty());
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).findAll();
    }
}
