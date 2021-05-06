package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VocabularyServiceImpl implements VocabularyService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public VocabularyServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    @Transactional
    @Override
    public List<Category> getCategoriesByBook(Book book) {
        return this.categoryRepository.getCategoriesByBook(book);
    }

    @Transactional
    @Override
    public void addBook(Book book) {
        this.bookRepository.save(book);
    }
}
