package vocab.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vocab.domain.*;
import vocab.services.MatchService;
import vocab.services.UserService;
import vocab.services.VocabularyService;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"vocab"})
@EntityScan(basePackages = {"vocab"})
@ComponentScan(basePackages = {"vocab"})
@Transactional
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
            List<String> to1 = Arrays.asList("to1");
            List<String> to2 = Arrays.asList("to2");
            List<String> to3 = Arrays.asList("to3");
            List<String> to4 = Arrays.asList("to4");
            List<String> to5 = Arrays.asList("to5");
            List<String> to6 = Arrays.asList("to6");
            List<String> to7 = Arrays.asList("to7");
            List<String> to8 = Arrays.asList("to8");

            Translation translation1 = new Translation(to8,to1);
            Translation translation2 = new Translation(to7,to2);
            List<Translation> translationList1 = Arrays.asList(translation1,translation2);
            Category category1 = new Category("category1",translationList1);
            Translation translation3 = new Translation(to6,to3);
            Translation translation4 = new Translation(to5,to4);
            List<Translation> translationList2 = Arrays.asList(translation3,translation4);
            Category category2 = new Category("category1",translationList2);
            List<Category> categoryList = Arrays.asList(category1,category2);
            Book book1 = new Book("book1","Deutsch","English",categoryList);
            Translation translation5 = new Translation(to4,to5);
            Translation translation6 = new Translation(to3,to6);
            List<Translation> translationList3 = Arrays.asList(translation5,translation6);
            Category category3 = new Category("category3",translationList3);
            Translation translation7 = new Translation(to2,to7);
            Translation translation8 = new Translation(to1,to8);
            List<Translation> translationList4 = Arrays.asList(translation7,translation8);
            Category category4 = new Category("category4",translationList4);
            List<Category> categoryList2 = Arrays.asList(category3,category4);
            Book book2 = new Book("book2","Deutsch","English",categoryList2);
            vocabularyService.addBook(book1);
            vocabularyService.addBook(book2);

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

            String resourceString = File.separator+"web"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"vocabulary_input";
            File resourceDir = new File(System.getProperty("user.dir")+resourceString);

            // Input Books from Script
            /**
            List<Book> books = VocabularyInputScript.parseFilesToLibrary(resourceDir);
            for (Book book : books) {
                vocabularyService.addBook(book);
            }
            for (File file : resourceDir.listFiles()){
                vocabularyService.addFile(file);
            }
             **/
            Match testMatch = matchService.getMatch(22L);
            User testUser = userService.getUserById(1L);
            Boolean testbool = matchService.submitAnswer("false",testMatch.getCurrentRound().getQuestions().get(1),testMatch.getId(),testUser);
            System.out.println(testbool);
            Match updatedMatch = matchService.getMatch(22L);
            System.out.println("test:" + updatedMatch.getCurrentRound().getQuestions().get(1).getAnswers().get(1).getCorrect());
        };
    }
}