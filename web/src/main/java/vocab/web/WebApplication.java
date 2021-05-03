package vocab.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vocab.domain.User;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"vocab"})
@EntityScan(basePackages = {"vocab"})
@ComponentScan(basePackages = {"vocab"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserServiceImpl userService) {
        return args -> {
            User user1 = new User("User1","password");
            userService.addUser(user1.getUserName(), user1.getPassword());
        };
    }
}