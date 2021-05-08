package vocab.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vocab.domain.*;
import vocab.services.MatchServiceImpl;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;
import vocab.services.VocabularyServiceImpl;

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
    CommandLineRunner commandLineRunner(UserServiceImpl userService, VocabularyServiceImpl vocabularyService, MatchServiceImpl matchService) {
        return args -> {
            // User-Test
            User user1 = new User("User1","password");
            userService.addUser(user1.getUserName(), user1.getPassword());

            User usertest =  userService.getUser(user1.getUserName(),user1.getPassword());
            System.out.println(usertest.toString());
            /*
            // Vocab-Test bidirectional
            GameDirection gameDirection1 = new GameDirection("Deutsch","English");
            GameDirection gameDirection2 = new GameDirection("Deutsch","Spanisch");
            Book book1 = new Book("book1",gameDirection1);
            Book book2 = new Book("book2",gameDirection2);
            Category category1 = new Category("category1",book1);
            Category category2 = new Category("category2",book1);
            Category category3 = new Category("category3",book2);
            Translation translation1 = new Translation("From","to",category1);
            Translation translation2 = new Translation("From","to",category1);
            Translation translation3 = new Translation("From","to",category2);
            Translation translation4 = new Translation("From","to",category3);
            List<Translation> translationList = Arrays.asList(translation1,translation2,translation3,translation4);
            category1.setTranslations(translationList);
            category2.setTranslations(translationList);
            category3.setTranslations(translationList);
            List<Category> categoryList = Arrays.asList(category1,category2,category3);
            book1.setCategories(categoryList);
            book2.setCategories(categoryList);
            List<Book> books = Arrays.asList(book1,book2);
            gameDirection1.setBooks(books);
            gameDirection2.setBooks(books);
            vocabularyService.addGameDirection(gameDirection1);
            vocabularyService.addGameDirection(gameDirection2);
             */

            GameDirection gameDirection1 = new GameDirection("Deutsch","English");
            Book book1 = new Book("book1");
            Book book2 = new Book("book2");
            Category category1 = new Category("category1");
            Category category2 = new Category("category2");
            Translation translation1 = new Translation("From1","to1");
            Translation translation2 = new Translation("From2","to2");
            List<Translation> translationList1 = Arrays.asList(translation1,translation2);
            Translation translation3 = new Translation("From3","to3");
            Translation translation4 = new Translation("From4","to4");
            List<Translation> translationList2 = Arrays.asList(translation3,translation4);
            category1.setTranslations(translationList1);
            category2.setTranslations(translationList2);
            List<Category> categoryList = Arrays.asList(category1,category2);
            book1.setCategories(categoryList);
            Category category3 = new Category("category3");
            Category category4 = new Category("category4");
            Translation translation5 = new Translation("From5","to5");
            Translation translation6 = new Translation("From6","to6");
            List<Translation> translationList3 = Arrays.asList(translation5,translation6);
            Translation translation7 = new Translation("From7","to7");
            Translation translation8 = new Translation("From8","to8");
            List<Translation> translationList4 = Arrays.asList(translation7,translation8);
            category3.setTranslations(translationList3);
            category4.setTranslations(translationList4);
            List<Category> categoryList2 = Arrays.asList(category3,category4);
            book2.setCategories(categoryList2);
            List<Book> books = Arrays.asList(book1,book2);
            gameDirection1.setBooks(books);
            vocabularyService.addGameDirection(gameDirection1);
            System.out.println(vocabularyService.getDirections().toString());

            //Match-Test
            // create One-Player Match
            User userPlayer1 = new User("player1","abc");
            matchService.createMatch(userPlayer1,"bookName");

            // update match -> normally just update
            Match match = matchService.getMatch(16L);
            Answer answer = new Answer("answer1",true);
            Answer answer2 = new Answer("answer2",false);
            Question question = new Question("question","answer1","answer2","answer3","answer4");
            question.setAnswerPlayer1(answer);
            question.setAnswerPlayer2(answer2);
            List<Question> questionList = Arrays.asList(question);
            Round round = new Round(questionList);
            round.setQuestions(questionList);
            User userPlayer2 = new User("player2","abc");
            match.setPlayer2(userPlayer2);
            User userPlayer3 = new User("player3","abc");
            match.setPlayer2(userPlayer3);
            match.setScorePlayer1(5);
            match.setScorePlayer2(6);
            Book matchbook = new Book("MatchBook");
            match.setCurrentRound(round);
            match.setBook(matchbook);
            matchService.updateMatch(match);
            User usertest2 =  userService.getUser(user1.getUserName(),user1.getPassword());
            System.out.println("yoo"+ userService.existsUserById(1L));
            List<Match> x = matchService.getAvailableMatches();
            System.out.println("x");
        };
    }
}