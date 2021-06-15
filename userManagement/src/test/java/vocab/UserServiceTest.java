package vocab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vocab.domain.User;
import vocab.exceptions.ResourceNotFoundException;
import vocab.repositories.UserRepository;
import vocab.services.UserService;
import vocab.services.UserServiceImpl;

import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockedUserRepository;

    private UserService userService;

    @Before
    public void setUp() {
        this.userService = new UserServiceImpl(mockedUserRepository);
    }

    @Test
    public void testGetUser(){
        //Arrange
        String userName = "testUser";
        String password = "password";
        User testUser = new User(userName, password);
        Mockito.when(mockedUserRepository.getUserByUserNameAndPassword(userName, password)).thenReturn(testUser);
        //Act
        User requestedUser = userService.getUser(userName, password);
        //Assert
        Assert.assertEquals(testUser, requestedUser);
        Mockito.verify(mockedUserRepository, Mockito.times(1)).getUserByUserNameAndPassword(userName, password);
    }

    @Test
    public void testGetUserException(){
        //Arrange
        String userName = "testUser";
        String password = "password";
        Mockito.when(mockedUserRepository.getUserByUserNameAndPassword(userName, password)).thenThrow(new RuntimeException());
        //Assert
        Assert.assertThrows(ResourceNotFoundException.class, () -> userService.getUser(userName, password));
        Mockito.verify(mockedUserRepository, Mockito.times(1)).getUserByUserNameAndPassword(userName, password);
    }

    @Test
    public void testGetUserById(){
        //Arrange
        String userName = "testUser";
        String password = "password";
        long id = 123L;
        User testUser = new User(userName, password);
        testUser.setId(id);
        Mockito.when(mockedUserRepository.getUserById(id)).thenReturn(testUser);
        //Act
        User requestedUser = userService.getUserById(id);
        //Assert
        Assert.assertEquals(id, requestedUser.getId().longValue());
        Mockito.verify(mockedUserRepository, Mockito.times(1)).getUserById(id);
    }

    @Test
    public void testGetUserByIdException(){
        //Arrange
        long id = 123L;
        Mockito.when(mockedUserRepository.getUserById(id)).thenThrow(new RuntimeException());
        //Assert
        Assert.assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(id));
        Mockito.verify(mockedUserRepository, Mockito.times(1)).getUserById(id);
    }

    @Test
    public void testAddUser(){
        //Arrange
        String userName = "testUser";
        String password = "password";
        User testUser = new User(userName, password);
        Mockito.when(mockedUserRepository.save(testUser)).thenReturn(testUser);
        //Act
        User addedUser = null;
        try {
            addedUser = userService.addUser(userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Assert
        Assert.assertNotNull(addedUser);
        Mockito.verify(mockedUserRepository, Mockito.times(1)).save(testUser);
    }

    @Test
    public void testAddUserException(){
        //Arrange
        String userName = "testUser";
        String password = "password";
        User testUser = new User(userName, password);
        Mockito.when(mockedUserRepository.save(testUser)).thenThrow(new RuntimeException());
        //Assert
        Assert.assertThrows(SQLException.class, () -> userService.addUser(userName, password));
        Mockito.verify(mockedUserRepository, Mockito.times(1)).save(testUser);
    }
}
