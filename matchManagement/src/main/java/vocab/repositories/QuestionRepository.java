package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question getQuestionById(Long id);
}

