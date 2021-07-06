package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vocab.domain.User;
import vocab.services.UserService;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody User user ) {
        try {
            User addedUser = userService.addUser(user.getUserName(), user.getPassword());
            return new ResponseEntity<>(addedUser, HttpStatus.OK);
        } catch (SQLException throwable) {
            String errorMsg = "Username already in use";
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getUser(@RequestParam String userName, String password) {
        User foundUser = userService.getUser(userName, password);
        if (foundUser != null) {
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
        else  {
            String errorMsg = "Can't login, check your username and password";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
