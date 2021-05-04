package vocab.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vocab.domain.*;
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
    CommandLineRunner commandLineRunner(UserServiceImpl userService, VocabularyServiceImpl vocabularyService) {
        return args -> {
            User user1 = new User("User1","password");
            userService.addUser(user1.getUserName(), user1.getPassword());

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
            Book book1 = new Book("book1",gameDirection1);
            Category category1 = new Category("category1",book1);
            Translation translation1 = new Translation("From","to",category1);
            List<Translation> translationList = Arrays.asList(translation1);
            List<Category> categoryList = Arrays.asList(category1);
            List<Book> books = Arrays.asList(book1);
            category1.setTranslations(translationList);
            book1.setCategories(categoryList);
            gameDirection1.setBooks(books);
            vocabularyService.addGameDirection(gameDirection1);

            System.out.println(vocabularyService.getDirections());

            //Match-Test

        };
    }
}