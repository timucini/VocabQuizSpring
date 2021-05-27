package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vocab.domain.User;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user ) {
        User addedUser = null;
        try {
            addedUser = userService.addUser(user.getUserName(), user.getPassword());
            return new ResponseEntity<>(addedUser, HttpStatus.OK);
        } catch (SQLException throwables) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam String userName, String password) {
        User foundUser = userService.getUser(userName, password);
        if (foundUser != null) {
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
