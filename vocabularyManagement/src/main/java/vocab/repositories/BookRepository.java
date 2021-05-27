package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    Book getBookById(Long id);
}
