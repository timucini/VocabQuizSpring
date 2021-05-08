package vocab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import vocab.domain.User;
import vocab.repositories.UserRepository;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void setUp() {
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        this.userService = userService;

        // Stubbing
        User user = new User("Testuser", "password");
        Mockito.when(userService.getUser("Testuser", "password")).thenReturn(user);
//        Mockito.when(userService.addUser("Testuser", "password")).thenReturn(user);
    }

    @Test
    public void testGetUser(){
        //Act
        User testuser = userService.getUser("Testuser", "password");
        // Assert
        Assert.assertNotNull(testuser);
        Assert.assertEquals("Testuser", testuser.getUserName());
        Assert.assertEquals("password", testuser.getPassword());

    }
    @Test
    public void testAddUser(){
        //Act
        User userCreated = userService.addUser("Testuser", "password");
        // Assert
        Assert.assertNotNull(userCreated);
        Assert.assertEquals("Testuser", userCreated.getUserName());
        Assert.assertEquals("password", userCreated.getPassword());

    }

}
