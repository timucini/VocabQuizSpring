package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * TODO
     * add custom Queries
     */
}
