package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.Translation;
import vocab.exceptions.BadInputFileException;
import vocab.exceptions.ItemNotFoundException;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class VocabularyServiceImpl implements VocabularyService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public VocabularyServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

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
    public Category getCategory(Long id) throws ItemNotFoundException {
        try{
            return categoryRepository.getCategoryById(id);
        }catch (RuntimeException e){
            throw new ItemNotFoundException("category with id: "+id.toString()+" could not be found");
        }
    }

    @Override
    public Book getBook(Long id) throws ItemNotFoundException {
        try{
            return bookRepository.getBookById(id);
        }catch (RuntimeException e){
            throw new ItemNotFoundException("book with id: "+id.toString()+" could not be found");
        }
    }

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
