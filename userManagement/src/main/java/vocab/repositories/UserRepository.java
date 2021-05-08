package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vocab.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User getUserByUserNameAndPassword(String username, String password);
    public boolean existsUserById(Long id);
}
