package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Answer;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}

