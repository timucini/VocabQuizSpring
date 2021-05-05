package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.GameDirection;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;
import vocab.repositories.GameDirectionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VocabularyServiceImpl implements VocabularyService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final GameDirectionRepository gameDirectionRepository;

    @Autowired
    public VocabularyServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, GameDirectionRepository gameDirectionRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.gameDirectionRepository = gameDirectionRepository;
    }
    @Transactional
    public void addGameDirection(GameDirection gameDirection) {
        gameDirectionRepository.save(gameDirection);
    }


    @Override
    @Transactional
    public List<GameDirection> getDirections() {
        return gameDirectionRepository.findAll();
    }

    // No GameDirection in Book -> Bidirectional Relationship better?
    @Override
    @Transactional
    public List<Book> getBooksByGameDirection(GameDirection gameDirection) {
        return null;
    }

    // No Book in Categories -> Bidirectional Relationship better?
    @Override
    @Transactional
    public List<Category> getCategoriesByBook(Book book) {
        return null;
    }
}
