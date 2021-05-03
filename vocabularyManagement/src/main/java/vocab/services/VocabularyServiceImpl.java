package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.GameDirection;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;
import vocab.repositories.GameDirectionRepository;

import java.util.List;

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


    @Override
    public List<GameDirection> getDirections() {
        return null;
    }

    @Override
    public List<Book> getBooksByGameDirection(GameDirection gameDirection) {
        return null;
    }

    @Override
    public List<Category> getCategoriesByBook(Book book) {
        return null;
    }
}
