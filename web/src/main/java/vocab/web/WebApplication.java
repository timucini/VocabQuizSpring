package vocab.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vocab.exceptions.BadInputFileException;
import vocab.services.MatchService;
import vocab.services.UserService;
import vocab.services.VocabularyService;
import java.io.File;

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
        };
    }
}