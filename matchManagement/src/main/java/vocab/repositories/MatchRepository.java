package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Match;
import vocab.domain.User;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    /**
     * TODO
     * add custom Queries
     */
    public Match getMatchById(Long id);

}

