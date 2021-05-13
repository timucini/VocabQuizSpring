package vocab.services;

import vocab.domain.User;
import vocab.exceptions.BadRequestException;
import vocab.exceptions.ResourceNotFoundException;

/**The following interface provides methods to manage users.
 * @version 0.1
 */

public interface UserService {
    /**This method add an new User.
     * @param username The name of the new user.
     * @param password The required password of the new user.
     * @return The method returns a boolean representing the success of the method.
     */
    User addUser(String username, String password) throws BadRequestException;
    /**This method is for respective logging a user in.
     * @param username The name of the user.
     * @param password The required password of the user.
     * @return The method returns a User instance, or null in case of not finding a user with the same username and password.
     */
    User getUser(String username, String password) throws ResourceNotFoundException;
}