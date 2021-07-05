package vocab.services;

import org.springframework.web.multipart.MultipartFile;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.exceptions.BadInputFileException;
import vocab.exceptions.ItemNotFoundException;

import java.io.File;
import java.util.List;
/**
 * This interface is can be used to manage the vocabulary.
 * @version 0.1
 */
public interface VocabularyService {

    /**
     * This method provides the access to all books written in a gameDirection.
     * @return The method returns a List instance of Books representing all playable Books.
     */
    List<Book> getBooks();

    /**
     * This method can be used to insert a file into the database.
     * @param file The method requires the file that should be inserted.
     * @return The method returns a boolean which indicates that te database was changed.
     * @throws BadInputFileException The method throws a exception if the provided file is not using the standard vocabulary format.
     */
    Boolean addFile(File file) throws BadInputFileException;

    /**
     * This method is used to get a category.
     * @param id The method requires the id representing the category.
     * @return The method returns the category, or null in case of not finding the requested category.
     * @throws ItemNotFoundException The method throws an exception in case there is no book with a matching id.
     */
    Category getCategory(Long id) throws ItemNotFoundException;

    /**
     * This method is used to get a book.
     * @param id The method requires the id representing the book.
     * @return The method returns the book, or null in case of not finding the requested book.
     * @throws ItemNotFoundException The method throws an exception in case there is no book with a matching id.
     */
    Book getBook(Long id) throws ItemNotFoundException;

    /**
     * This method is used to insert a multipart file into the database.
     * @param file The method requires the multipart file that should be inserted.
     * @return The method returns a boolean which indicates that te database was changed.
     * @throws BadInputFileException The method throws a exception if the provided file is not using the standard vocabulary format.
     */
    Boolean addMultipartFileHelper(MultipartFile file) throws BadInputFileException;
}
