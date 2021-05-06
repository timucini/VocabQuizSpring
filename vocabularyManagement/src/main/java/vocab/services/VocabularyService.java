package vocab.services;

import vocab.domain.Book;
import vocab.domain.Category;

import java.util.List;
/**This interface is can be used to manage the vocabulary.
 * @version 0.1
 */
public interface VocabularyService {

    /**This method provides the access to all books written in a gameDirection.
     * @return The method returns a List instance of Books representing all playable Books.
     */
    List<Book> getBooks();
    /**This method can used to get all categories written in the book.
     * @param book This method requires the book owning the categories.
     * @return The method returns a List instance of Category representing all categories of a book.
     */
    List<Category> getCategoriesByBook(Book book);

    /**
     * This method can be used to insert a book into the Database
     * @param book
     */
    void addBook(Book book);
}
