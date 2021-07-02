package vocab.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vocab.domain.*;
import vocab.exceptions.BadInputFileException;
import vocab.services.MatchService;
import vocab.services.UserService;
import vocab.services.VocabularyService;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"vocab"})
@EntityScan(basePackages = {"vocab"})
@ComponentScan(basePackages = {"vocab"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService, VocabularyService vocabularyService, MatchService matchService) {
        return args -> {
            // Input Script

            String resourceString = File.separator+"resources";
            File resourceDir = new File(System.getProperty("user.dir")+resourceString);

            // Input Books from Script

            for (File file : resourceDir.listFiles()){
                try {
                    vocabularyService.addFile(file);
                } catch (BadInputFileException e) {
                    e.printStackTrace();
                }
            }


            // User-Test
            User user1 = new User("User1","password");
            userService.addUser(user1.getUserName(), user1.getPassword());
            User user2 = new User("User2","password");
            userService.addUser(user2.getUserName(), user2.getPassword());

            User usertest =  userService.getUser(user1.getUserName(),user1.getPassword());
            System.out.println(usertest.toString());

            //Match-Test
            // create One-Player Match
            User userPlayer1 = userService.getUser("User1","password");
            Book bookMatch = vocabularyService.getBooks().get(0);
            matchService.createMatch(userPlayer1,bookMatch);


            // update match
            // add player
            Match match = matchService.getAvailableMatches(userPlayer1).get(0);
            User userPlayer2 = userService.getUser("User2","password");
            match.setPlayer2(userPlayer2);
            Translation translationRight = vocabularyService.getBooks().get(0).getCategories().get(0).getTranslations().get(0);
            Translation translationWrong = vocabularyService.getBooks().get(0).getCategories().get(0).getTranslations().get(1);


            Question question = new Question(
                    "Question",
                    translationRight,
                    translationWrong,
                    translationWrong,
                    translationWrong);
            Question question2 = new Question(
                    "Question",
                    translationRight,
                    translationWrong,
                    translationWrong,
                    translationWrong);
            Question question3 = new Question(
                    "Question",
                    translationRight,
                    translationWrong,
                    translationWrong,
                    translationWrong);
            Answer Answer1 = new Answer("Correct",true,userPlayer1,question);
            Answer Answer2 = new Answer("Correct",true,userPlayer2,question2);
            List<Answer> answerListList = Arrays.asList(Answer1,Answer2);
            question.setAnswers(answerListList);
            List<Question> questionList = Arrays.asList(question,question2,question3);
            Category testCategory = vocabularyService.getBooks().get(0).getCategories().get(0);
            Round newRound = new Round(questionList,testCategory);
            match.setCurrentRound(newRound);
            match.setScorePlayer1(5);
            match.setFinished(true);
            match.setId(12L);
            matchService.updateMatch(match);
        };
    }
}