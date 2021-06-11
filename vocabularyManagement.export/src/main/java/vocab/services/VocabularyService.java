package vocab.services;

import org.springframework.web.multipart.MultipartFile;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.exceptions.BadInputFileException;

import java.io.File;
import java.util.List;
/**This interface is can be used to manage the vocabulary.
 * @version 0.1
 */
public interface VocabularyService {

    /**This method provides the access to all books written in a gameDirection.
     * @return The method returns a List instance of Books representing all playable Books.
     */
    List<Book> getBooks();

    /**
     * This method can be used to insert a file into the Database
     * @param file
     */
    Boolean addFile(File file) throws BadInputFileException;

    /**
     *
     * @param id
     * @return Category
     */
    Category getCategory(Long id);


    Book getBook(Long id);

    Boolean addMultipartFileHelper(MultipartFile file) throws  BadInputFileException;
}
