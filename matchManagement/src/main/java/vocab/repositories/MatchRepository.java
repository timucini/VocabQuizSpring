package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Match;
import vocab.exceptions.ResourceNotFoundException;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Match getMatchById(Long id) throws ResourceNotFoundException;
}

