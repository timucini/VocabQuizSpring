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
    public Boolean addBook(Book book) {
        try {
            this.bookRepository.save(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Category getCategory(Long id) {
        return categoryRepository.getCategoryById(id);
    }
}
