package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.User;
import vocab.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean addUser(String username, String password) {
        User user = new User(username,password);
        userRepository.save(user);
        /**
         * TODO
         */
        return true;
    }

    public User getUser(String username, String password) {
        /**
         * TODO
         */
        return null;
    }
}
