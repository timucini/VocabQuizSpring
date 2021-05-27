package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vocab.domain.Book;
import vocab.services.VocabularyService;
import vocab.services.VocabularyServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vocab")
@CrossOrigin("*")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @Autowired
    public VocabularyController(VocabularyServiceImpl vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Book>> getBooks() {
        try {
            List<Book> books = vocabularyService.getBooks();
            if (books != null) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
