package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(String username,String password) {
        userService.addUser(username,password);
    }

}
