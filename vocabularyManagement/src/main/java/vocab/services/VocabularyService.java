package vocab.services;

import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.GameDirection;

import java.util.List;
/**This interface is can be used to manage the vocabulary.
 * @version 0.1
 */
public interface VocabularyService {
    /**This method is used for access all playable GameDirections.
     * @return The method returns a List instance of GameDirection representing all playable GameDirections.
     */
    List<GameDirection> getDirections();
    /**This method provides the access to all books written in a gameDirection.
     * @param gameDirection The method requires an instance of GameDirection.
     * @return The method returns a List instance of Book representing all playable Books of a gameDirection.
     */
    List<Book> getBooksByGameDirection(GameDirection gameDirection);
    /**This method can used to get all categories written in the book.
     * @param book This method requires the book owning the categories.
     * @return The method returns a List instance of Category representing all categories of a book.
     */
    List<Category> getCategoriesByBook(Book book);

}
