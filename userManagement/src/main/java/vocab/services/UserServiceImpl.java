package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.User;
import vocab.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User addUser(String username, String password) {
        User user = new User(username,password);
        try {
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public User getUser(String username, String password) {
        return userRepository.getUserByUserNameAndPassword(username,password);
    }
}