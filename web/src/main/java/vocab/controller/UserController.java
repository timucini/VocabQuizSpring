package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vocab.domain.User;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Boolean addUser(@RequestParam String username, String password ) {
        return userService.addUser(username, password);
    }

    @GetMapping("/get")
    public User getUser(@RequestParam String username, String password) {
        return userService.getUser(username,password);
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

}
