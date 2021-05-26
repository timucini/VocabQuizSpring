package vocab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vocab.domain.Book;
import vocab.domain.Category;
import vocab.domain.Translation;
import vocab.domain.TranslationEntry;
import vocab.repositories.BookRepository;
import vocab.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public Boolean addFile(File file) {
        try {
            Book newBook = parseFile(file);
            List<Book> books = this.bookRepository.findAll();
            for (Book book: books) {
                if (book.getName().equalsIgnoreCase(newBook.getName())) {
                    book.getCategories().addAll(newBook.getCategories());
                    this.bookRepository.save(book);
                    return true;
                }
            }
            this.bookRepository.save(newBook);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Category getCategory(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    private static Book parseFile(File file) {
        List<String> input = readFile(file);
        String[] description = input.get(0).split("}\\{");
        String bookName = description[3].replaceAll("}", "").replaceAll("\\{", "");
        String bookFrom = description[1].replaceAll("}", "").replaceAll("\\{", "");
        String bookTo = description[2].replaceAll("}", "").replaceAll("\\{", "");
        String categoryName = description[0].replaceAll("}", "").replaceAll("\\{", "");
        List<Translation> translations = createTranslations(input.subList(1, input.size()));
        Category category = new Category(categoryName, translations);
        return new Book(bookName, bookFrom, bookTo, Arrays.asList(category));
    }

    private static List<String> readFile(File file){
        List<String> input = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString = reader.readLine();
            while(tempString!=null && !tempString.isEmpty()){
                input.add(tempString);
                tempString = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private static List<Translation> createTranslations(List<String> input){
        List<Translation> translations = new ArrayList<>();
        for (String string : input){
            string = string.split("\\{\\{\\{}}}")[0];
            string = string.replaceAll("\\{", "");
            string = string.replaceAll("}, ", "},");
            String[] tempArray = string.split(" : ");
            String[] from = tempArray[0].split("},");
            String[] to = tempArray[1].split("},");
            List<TranslationEntry> fromList = new ArrayList<>();
            for (String f: from) {
                fromList.add(new TranslationEntry(f.replaceAll("}","")));
            }
            List<TranslationEntry> toList = new ArrayList<>();
            for (String t: to) {
                toList.add(new TranslationEntry(t.replaceAll("}","")));
            }
            translations.add(new Translation(fromList, toList));
        }
        return translations;
    }
}
