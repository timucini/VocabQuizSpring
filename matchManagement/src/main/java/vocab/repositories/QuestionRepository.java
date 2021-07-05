package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Question;
import vocab.exceptions.ResourceNotFoundException;

import java.lang.module.ResolutionException;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question getQuestionById(Long id) throws ResourceNotFoundException;
}

