package vocab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vocab.domain.*;
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
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(Mockito.any(Match.class));
        Assert.assertNotNull(testMatch);
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
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
        Assert.assertEquals(id, requestedMatch.getId().longValue());
    }

    @Test
    public void testGetMatchNull(){
        //Arrange
        long id = 123L;
        Mockito.when(mockedMatchRepository.getMatchById(id)).thenReturn(null);
        //Act
        Match requestedMatch = matchService.getMatch(id);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
        Assert.assertNull(requestedMatch);
    }

    @Test
    public void testUpdateMatch(){
        //Arrange
        Match testMatch = new Match();
        Mockito.when(mockedMatchRepository.save(testMatch)).thenReturn(null);
        //Act
        Boolean isChanged = matchService.updateMatch(testMatch);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(testMatch);
        Assert.assertTrue(isChanged);
    }

    @Test
    public void testUpdateMatchNoChange(){
        //Arrange
        Match testMatch = new Match();
        Mockito.when(mockedMatchRepository.save(testMatch)).thenThrow(new RuntimeException());
        //Act
        Boolean isChanged = matchService.updateMatch(testMatch);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(testMatch);
        Assert.assertFalse(isChanged);
    }

    @Test
    public void testGetAvailableMatches(){
        //Arrange
        Match testMatch = new Match(new User(), new Book());
        Mockito.when(mockedMatchRepository.findAll()).thenReturn(Arrays.asList(testMatch));
        //Act
        List<Match> matches = matchService.getAvailableMatches(new User());
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).findAll();
        Assert.assertEquals(testMatch, matches.get(0));
    }

    @Test
    public void testGetAvailableMatchesEmpty(){
        //Arrange
        User testUser = new User();
        Mockito.when(mockedMatchRepository.findAll()).thenReturn(new ArrayList<>());
        //Act
        List<Match> matches = matchService.getAvailableMatches(testUser);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).findAll();
        Assert.assertTrue(matches.isEmpty());
    }

    @Test
    public void testJoinMatch(){
        //Arrange
        User testUser = new User();
        long id = 123L;
        Mockito.when((mockedMatchRepository.getMatchById(id))).thenReturn(new Match());
        Mockito.when(mockedMatchRepository.save(Mockito.any(Match.class))).then(returnsFirstArg());
        //Act
        Match testMatch = matchService.joinMatch(testUser, id);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(Mockito.any(Match.class));
        Assert.assertNotNull(testMatch);
    }

    @Test
    public void testJoinMatchNull(){
        //Arrange
        User testUser = new User();
        long id = 123L;
        Mockito.when((mockedMatchRepository.getMatchById(123L))).thenReturn(new Match());
        Mockito.when(mockedMatchRepository.save(Mockito.any(Match.class))).thenThrow(new RuntimeException());
        //Act
        Match testMatch = matchService.joinMatch(testUser, id);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(Mockito.any(Match.class));
        Assert.assertNull(testMatch);
    }

    @Test
    public void testSubmitAnswer(){
        //Arrange
        long id = 123L;
        Translation correctAnswer = new Translation(Arrays.asList("correct"), Arrays.asList("true"));
        Translation wrongAnswer1 = new Translation(Arrays.asList("wrong1"), Arrays.asList("false1"));
        Translation wrongAnswer2 = new Translation(Arrays.asList("wrong2"), Arrays.asList("false2"));
        Translation wrongAnswer3 = new Translation(Arrays.asList("wrong3"), Arrays.asList("false3"));
        Question testQuestion = new Question(correctAnswer.getStringFrom().get(0), correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
        User testUser = new User();
        Mockito.when(mockedAnswerRepository.save(Mockito.any(Answer.class))).then(returnsFirstArg());
        //Act
        Boolean isCorrect = matchService.submitAnswer(correctAnswer.getStringTo().get(0), testQuestion, id, testUser);
        Boolean notCorrect = matchService.submitAnswer(wrongAnswer1.getStringTo().get(0), testQuestion, id, testUser);
        //Assert
        Mockito.verify(mockedAnswerRepository, Mockito.times(2)).save(Mockito.any(Answer.class));
        Assert.assertTrue(isCorrect);
        Assert.assertFalse(notCorrect);
    }

    @Test
    public void testFinishMatch(){
        //Arrange
        long id = 123L;
        Match testMatch = new Match();
        Mockito.when(mockedMatchRepository.getMatchById(id)).thenReturn(testMatch);
        Mockito.when(mockedMatchRepository.save(Mockito.any(Match.class))).then(returnsFirstArg());
        //Act
        matchService.finishMatch(id);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(Mockito.any(Match.class));
        Assert.assertTrue(testMatch.getFinished());
    }

    @Test
    public void testGetQuestion(){
        //Arrange
        long id = 123L;
        Question testQuestion = new Question();
        testQuestion.setId(id);
        Mockito.when(mockedQuestionRepository.getQuestionById(id)).thenReturn(testQuestion);
        //Act
        Question requestedQuestion = matchService.getQuestion(id);
        //Assert
        Mockito.verify(mockedQuestionRepository, Mockito.times(1)).getQuestionById(id);
        Assert.assertEquals(id, requestedQuestion.getId().longValue());
    }

    @Test
    public void testStartRound(){
        //Arrange
        long id = 123L;
        Translation translationA = new Translation(Arrays.asList("a"),Arrays.asList("A"));
        Translation translationB = new Translation(Arrays.asList("b"),Arrays.asList("B"));
        Translation translationC = new Translation(Arrays.asList("c"),Arrays.asList("C"));
        Translation translationD = new Translation(Arrays.asList("d"),Arrays.asList("D"));
        Category testCategory = new Category("alpha",Arrays.asList(translationA,translationB,translationC,translationD));
        Match testMatch = new Match();
        testMatch.setId(id);
        Match failMatch = new Match();
        Mockito.when(mockedMatchRepository.save(testMatch)).then(returnsFirstArg());
        Mockito.when(mockedMatchRepository.getMatchById(id)).thenReturn(testMatch);
        Mockito.when(mockedMatchRepository.save(failMatch)).thenThrow(new RuntimeException());
        //Act
        Round startedRound = matchService.startRound(testCategory, testMatch);
        Round failRound = matchService.startRound(testCategory, failMatch);
        //Assert
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(testMatch);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).save(failMatch);
        Mockito.verify(mockedMatchRepository, Mockito.times(1)).getMatchById(id);
        Assert.assertNotNull(startedRound);
        Assert.assertNull(failRound);
    }
}
