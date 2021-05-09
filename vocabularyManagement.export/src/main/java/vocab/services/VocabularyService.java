package vocab.services;

import vocab.domain.Book;

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
     * This method can be used to insert a book into the Database
     * @param book
     */
    Boolean addBook(Book book);
}
