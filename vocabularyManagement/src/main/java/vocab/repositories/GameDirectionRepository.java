package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.GameDirection;

@Repository
public interface GameDirectionRepository extends JpaRepository<GameDirection, Long> {
    /**
     * TODO
     * add custom Queries
     */
}
