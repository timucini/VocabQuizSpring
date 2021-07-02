package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Match getMatchById(Long id);
}

