package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vocab.domain.User;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestParam String username, String password ) {
        User addedUser =  userService.addUser(username, password);
        if (addedUser != null) {
            return new ResponseEntity<>(addedUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam String username, String password) {
        User foundUser = userService.getUser(username,password);
        if (foundUser != null) {
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }


}
