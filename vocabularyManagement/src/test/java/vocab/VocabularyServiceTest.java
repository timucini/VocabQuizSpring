package vocab;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.Translation;
import vocab.exceptions.BadInputFileException;
import vocab.exceptions.ItemNotFoundException;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;
import vocab.services.VocabularyService;
import vocab.services.VocabularyServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(MockitoJUnitRunner.class)
public class VocabularyServiceTest {

    @Mock
    private BookRepository mockedBookRepository;
    @Mock
    private CategoryRepository mockedCategoryRepository;

    private VocabularyService vocabularyService;

    @Before
    public void setUp() {
        vocabularyService = new VocabularyServiceImpl(mockedBookRepository, mockedCategoryRepository);
    }

    @Test
    public void testGetBooks(){
        //Arrange
        Mockito.when(mockedBookRepository.findAll()).thenReturn(new ArrayList<>());
        //Act
        List<Book> bookList = vocabularyService.getBooks();
        // Assert
        Mockito.verify(mockedBookRepository, Mockito.times(1)).findAll();
        Assert.assertNotNull(bookList);
    }

    @Test
    public void testAddFile(){
        //Arrange
        File testFile = new File(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"test.txt");
        Mockito.when(mockedBookRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(mockedBookRepository.save(Mockito.any(Book.class))).then(returnsFirstArg());
        Boolean isChanged = null;
        //Act
        try {
            isChanged = vocabularyService.addFile(testFile);
        } catch (BadInputFileException e) {
            e.printStackTrace();
        }
        // Assert
        Mockito.verify(mockedBookRepository, Mockito.times(1)).save(Mockito.any(Book.class));
        Mockito.verify(mockedBookRepository, Mockito.times(1)).findAll();
        Assert.assertTrue(isChanged);
    }

    @Test
    public void testAddFileNoChange(){
        //Arrange
        File testFile = new File(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"test.txt");
        Boolean isChanged = null;
        Category testCategory = new Category("category1",Arrays.asList(new Translation(Arrays.asList("language1word1"), Arrays.asList("language2word1"))));
        Book testBook = new Book("book1", "language1", "language2", Arrays.asList(testCategory));
        Mockito.when(mockedBookRepository.findAll()).thenReturn(Arrays.asList(testBook));
        //Act
        try {
            isChanged = vocabularyService.addFile(testFile);
        } catch (BadInputFileException e) {
            e.printStackTrace();
        }
        // Assert
        Mockito.verify(mockedBookRepository, Mockito.times(1)).findAll();
        Assert.assertFalse(isChanged);
    }

    @Test
    public void testAddFileException(){
        //Arrange
        File testFile = new File(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"badTest.txt");
        // Assert
        Assert.assertThrows(BadInputFileException.class, () -> vocabularyService.addFile(testFile));
    }

    @Test
    public void testGetCategory(){
        //Arrange
        long id = 123L;
        long wrong_id = 0L;
        Category testCategory = new Category();
        testCategory.setId(id);
        Mockito.when(mockedCategoryRepository.getCategoryById(id)).thenReturn(testCategory);
        Mockito.when(mockedCategoryRepository.getCategoryById(wrong_id)).thenThrow(new RuntimeException());
        //Act
        Category requestedCategory = vocabularyService.getCategory(id);
        // Assert
        Assert.assertThrows(ItemNotFoundException.class, () -> vocabularyService.getCategory(wrong_id));
        Mockito.verify(mockedCategoryRepository, Mockito.times(1)).getCategoryById(id);
        Mockito.verify(mockedCategoryRepository, Mockito.times(1)).getCategoryById(wrong_id);
        Assert.assertEquals(id, requestedCategory.getId().longValue());
    }

    @Test
    public void testGetBook(){
        //Arrange
        long id = 123L;
        long wrong_id = 0L;
        Book testBook = new Book();
        testBook.setId(id);
        Mockito.when(mockedBookRepository.getBookById(id)).thenReturn(testBook);
        Mockito.when(mockedBookRepository.getBookById(wrong_id)).thenThrow(new RuntimeException());
        //Act
        Book requestedBook = vocabularyService.getBook(id);
        // Assert
        Assert.assertThrows(ItemNotFoundException.class, () -> vocabularyService.getBook(wrong_id));
        Mockito.verify(mockedBookRepository, Mockito.times(1)).getBookById(id);
        Mockito.verify(mockedBookRepository, Mockito.times(1)).getBookById(wrong_id);
        Assert.assertEquals(id, requestedBook.getId().longValue());
    }

    @Test
    public void testAddMultipartFile(){
        //Arrange
        String fileName = "test.txt";
        File testFile = new File(System.getProperty("user.dir")+File.separator+"resources"+File.separator+fileName);
        byte[] content = null;
        try {
            content = new FileInputStream(testFile).readAllBytes();
        }catch (IOException e) {
            e.printStackTrace();
        }
        MultipartFile testMultipartFile = new MockMultipartFile(fileName, fileName, null, content);
        Mockito.when(mockedBookRepository.save(Mockito.any(Book.class))).thenReturn(null);
        Mockito.when(mockedBookRepository.findAll()).thenReturn(new ArrayList<>());
        Boolean isChanged = null;
        //Act
        try {
            isChanged = vocabularyService.addMultipartFileHelper(testMultipartFile);
        } catch (BadInputFileException e) {
            e.printStackTrace();
        }
        // Assert
        Mockito.verify(mockedBookRepository, Mockito.times(1)).save(Mockito.any(Book.class));
        Mockito.verify(mockedBookRepository, Mockito.times(1)).findAll();
        Assert.assertTrue(isChanged);
    }

    @Test
    public void testAddMultipartFileNoChange(){
        //Arrange
        String fileName = "test.txt";
        File testFile = new File(System.getProperty("user.dir")+File.separator+"resources"+File.separator+fileName);
        byte[] content = null;
        try {
            content = new FileInputStream(testFile).readAllBytes();
        }catch (IOException e) {
            e.printStackTrace();
        }
        MultipartFile testMultipartFile = new MockMultipartFile(fileName, fileName, null, content);
        Boolean isChanged = null;
        Category testCategory = new Category("category1",Arrays.asList(new Translation(Arrays.asList("language1word1"), Arrays.asList("language2word1"))));
        Book testBook = new Book("book1", "language1", "language2", Arrays.asList(testCategory));
        Mockito.when(mockedBookRepository.findAll()).thenReturn(Arrays.asList(testBook));
        //Act
        try {
            isChanged = vocabularyService.addMultipartFileHelper(testMultipartFile);
        } catch (BadInputFileException e) {
            e.printStackTrace();
        }
        // Assert
        Mockito.verify(mockedBookRepository, Mockito.times(1)).findAll();
        Assert.assertFalse(isChanged);
    }

    @Test
    public void testAddMultipartFileException(){
        //Arrange
        String fileName = "badTest.txt";
        File testFile = new File(System.getProperty("user.dir")+File.separator+"resources"+File.separator+fileName);
        byte[] content = null;
        try {
            content = new FileInputStream(testFile).readAllBytes();
        }catch (IOException e) {
            e.printStackTrace();
        }
        MultipartFile testMultipartFile = new MockMultipartFile(fileName, content);
        // Assert
        Assert.assertThrows(BadInputFileException.class, () -> vocabularyService.addMultipartFileHelper(testMultipartFile));
    }
}