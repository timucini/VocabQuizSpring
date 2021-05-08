package vocab;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vocab.domain.Book;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;
import vocab.services.VocabularyService;
import vocab.services.VocabularyServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class VocabularyServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private CategoryRepository categoryRepository;
    private VocabularyService vocabularyService;

    @Before
    public void setUp() {
        VocabularyServiceImpl vocabularyService = new VocabularyServiceImpl(bookRepository, categoryRepository);
        this.vocabularyService = vocabularyService;

        // Stubbing

//        User user = new User("Testuser", "password");
//        Mockito.when(userService.getUser("Testuser", "password")).thenReturn(user);
//        Mockito.when(userService.addUser("Testuser", "password")).thenReturn(user);
        Book book = new Book();
        book.setName("access 3");
        Book book2 = new Book();
        book.setName("access 2");
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);
        Mockito.when(vocabularyService.addBook(book)).thenReturn(book);
//        Mockito.when(vocabularyService.getBooks()).thenReturn(bookList);
    }

    @Test
    public void testAddBook(){
        //Act
        Book book = new Book();
        book.setName("access 3");
        Book addedBook = vocabularyService.addBook(book);
        // Assert
        Assert.assertNotNull(addedBook);
        Assert.assertEquals("access 3", addedBook.getName());

    }

    @Test
    public void testGetBooks(){
        //Act
        List<Book> bookList = vocabularyService.getBooks();
        // Assert
        Assert.assertNotNull(bookList);
//        Assert.assertTrue(bookList.contains());

    }
}
