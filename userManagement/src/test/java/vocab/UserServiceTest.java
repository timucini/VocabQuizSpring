package vocab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vocab.domain.User;
import vocab.repositories.UserRepository;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

import java.sql.SQLException;

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
        user.setId(123L);
        Mockito.when(userService.getUser("Testuser", "password")).thenReturn(user);
    }

    @Test
    public void testGetUser(){
        Long userId = 123L;
        //Act
        User requestedUser = userService.getUser("Testuser", "password");
        // Assert
        Assert.assertNotNull(requestedUser);
        Assert.assertEquals("Testuser", requestedUser.getUserName());
        Assert.assertEquals("password", requestedUser.getPassword());
        Assert.assertEquals(userId, requestedUser.getId());

    }
    @Test
    public void testAddUser() throws SQLException {
        //Act
        User userCreated = userService.addUser("Testuser", "password");
        // Assert
        Assert.assertNotNull(userCreated);
        Assert.assertEquals("Testuser", userCreated.getUserName());
        Assert.assertEquals("password", userCreated.getPassword());

    }

}
