package vocab.web;

import vocab.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vocab.domain.*;
import vocab.services.*;

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
            // User-Test
            User user1 = new User("User1","password");
            userService.addUser(user1.getUserName(), user1.getPassword());
            User user2 = new User("User2","password");
            userService.addUser(user2.getUserName(), user2.getPassword());

            User usertest =  userService.getUser(user1.getUserName(),user1.getPassword());
            System.out.println(usertest.toString());

            Translation translation1 = new Translation("From1","to1");
            Translation translation2 = new Translation("From2","to2");
            List<Translation> translationList1 = Arrays.asList(translation1,translation2);
            Category category1 = new Category("category1",translationList1);
            Translation translation3 = new Translation("From3","to3");
            Translation translation4 = new Translation("From4","to4");
            List<Translation> translationList2 = Arrays.asList(translation3,translation4);
            Category category2 = new Category("category1",translationList2);
            List<Category> categoryList = Arrays.asList(category1,category2);
            Book book1 = new Book("book1",categoryList);
            Translation translation5 = new Translation("From5","to5");
            Translation translation6 = new Translation("From6","to6");
            List<Translation> translationList3 = Arrays.asList(translation5,translation6);
            Category category3 = new Category("category3",translationList3);
            Translation translation7 = new Translation("From7","to7");
            Translation translation8 = new Translation("From8","to8");
            List<Translation> translationList4 = Arrays.asList(translation7,translation8);
            Category category4 = new Category("category4",translationList4);
            List<Category> categoryList2 = Arrays.asList(category3,category4);
            Book book2 = new Book("book1",categoryList2);
            vocabularyService.addBook(book1);
            vocabularyService.addBook(book2);

            //Match-Test
            // create One-Player Match
            User userPlayer1 = userService.getUser("User1","password");
            Book bookMatch = vocabularyService.getBooks().get(0);
            matchService.createMatch(userPlayer1,bookMatch);


            // update match
            // add player
            Match match = matchService.getAvailableMatches().get(0);
            User userPlayer2 = userService.getUser("User2","password");
            match.setPlayer2(userPlayer2);

            Question question = new Question(
                    "Question",
                    "Correct",
                    "wrong1",
                    "wrong2",
                    "wrong");
            Question question2 = new Question(
                    "Question",
                    "Correct",
                    "wrong1",
                    "wrong2",
                    "wrong");
            Question question3 = new Question(
                    "Question",
                    "Correct",
                    "wrong1",
                    "wrong2",
                    "wrong");
            Answer Answer1 = new Answer("Correct",true,userPlayer1);
            Answer Answer2 = new Answer("Correct",true,userPlayer2);
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
            User usertest2 =  userService.getUser(user1.getUserName(),user1.getPassword());
            List<Match> x = matchService.getAvailableMatches();
            Match matchtest = matchService.getMatch(15L);
            System.out.println("x");

            VocabularyInputScript inputScript = new VocabularyInputScript();
            // Input Books from Script
            List<Book> books = VocabularyInputScript.createBooks();
            for (Book book : books) {
                vocabularyService.addBook(book);
            }
        };
    }
}