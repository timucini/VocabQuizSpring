package vocab.services;

import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.GameDirection;

import java.util.List;

public interface VocabularyService {

    List<GameDirection> getDirections();

    List<Book> getBooksByGameDirection(GameDirection gameDirection);

    List<Category> getCategoriesByBook(Book book);

}
