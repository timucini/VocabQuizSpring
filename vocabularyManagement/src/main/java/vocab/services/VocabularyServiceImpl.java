package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.Translation;
import vocab.exceptions.BadInputFileException;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public Boolean addFile(File file) throws BadInputFileException {
        List<Book> books = bookRepository.findAll();
        Book newBook;
        try {
            newBook = VocabularyInputScript.parseFileToBook(file);
        } catch (RuntimeException | IOException e) {
            throw new BadInputFileException(e.getMessage());
        }
        int oldBookIndex = books.indexOf(newBook);
        if (oldBookIndex<0){
            this.bookRepository.save(newBook);
            return true;
        }else{
            Book oldBook = books.get(oldBookIndex);
            List<Category> oldCategories = oldBook.getCategories();
            Category newCategory = newBook.getCategories().get(0);
            int oldCategoryIndex = oldCategories.indexOf(newCategory);
            if (oldCategoryIndex<0){
                oldBook.getCategories().add(newCategory);
                this.bookRepository.save(oldBook);
                return true;
            }else{
                Category oldCategory = oldCategories.get(oldCategoryIndex);
                boolean changed = false;
                for (Translation newTranslation: newCategory.getTranslations()){
                    if (!oldCategory.getTranslations().contains(newTranslation)){
                        oldCategory.getTranslations().add(newTranslation);
                        changed = true;
                    }
                }
                categoryRepository.save(oldCategory);
                return changed;
            }
        }
    }

    @Override
    @Transactional
    public Category getCategory(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    @Transactional
    public Book getBook(Long id) {
        return bookRepository.getBookById(id);
    }

    @Transactional
    @Override
    public Boolean addMultipartFileHelper(MultipartFile file) throws BadInputFileException {
        String resourceString = File.separator+"resources"+File.separator;
        File convertedFile = new File(System.getProperty("user.dir")+ resourceString + "temp" + file.getOriginalFilename());
        try {
            convertedFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
            fileOutputStream.write(file.getBytes());
            return addFile(convertedFile);
        } catch (RuntimeException | IOException e) {
            throw new BadInputFileException(e.getMessage());
        }
    }
}
