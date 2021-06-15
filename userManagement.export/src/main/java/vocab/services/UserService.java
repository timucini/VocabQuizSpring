package vocab.services;

import vocab.domain.User;
import vocab.exceptions.ResourceNotFoundException;

import java.sql.SQLException;

/**
 * The following interface provides methods to manage users.
 * @version 0.1
 */

public interface UserService {

    /**
     * This method adds an new user.
     * @param username The name of the new user.
     * @param password The required password of the new user.
     * @return The method returns a the added user.
     * @throws SQLException The method throws a SQLException in case of the requested username is already in use.
     */
    User addUser(String username, String password) throws SQLException;

    /**
     * This method is for respective logging a user in.
     * @param username The name of the user.
     * @param password The required password of the user.
     * @return The method returns a User instance.
     * @throws ResourceNotFoundException The method throws an exception in case of the requested user could not be found.
     */
    User getUser(String username, String password) throws ResourceNotFoundException;

    /**
     * This method is used to get a user.
     * @param id The method requires the id representing the user.
     * @return The method return the requested user.
     * @throws ResourceNotFoundException The method throws an exception in case of the requested user could not be found.
     */
    User getUserById(Long id) throws ResourceNotFoundException;
}