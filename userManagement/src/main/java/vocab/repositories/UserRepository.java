package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.User;
import vocab.exceptions.ResourceNotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUserNameAndPassword(String username, String password) throws ResourceNotFoundException;

    User getUserById(Long id) throws ResourceNotFoundException;
}
