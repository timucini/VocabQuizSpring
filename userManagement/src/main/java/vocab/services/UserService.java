package vocab.services;

import vocab.domain.User;

public interface UserService {

    /**
     * TODO add JAVADOC
     */

    public Boolean addUser(String username, String password);

    public User getUser(String username, String password);
}

