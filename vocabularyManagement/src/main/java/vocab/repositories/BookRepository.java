package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Book;
import vocab.domain.GameDirection;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    /**
     * TODO
     * add custom Queries
     */
}
