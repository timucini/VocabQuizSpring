package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.User;
import vocab.exceptions.ResourceNotFoundException;
import vocab.repositories.UserRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User addUser(String username, String password) throws SQLException {
        try {
            User user = new User(username,password);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new SQLException("UserName already in use");
        }
    }

    @Transactional
    public User getUser(String username, String password) throws ResourceNotFoundException {
        try {
            return userRepository.getUserByUserNameAndPassword(username,password);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
