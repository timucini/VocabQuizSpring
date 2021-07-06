package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vocab.domain.Book;
import vocab.services.VocabularyService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vocab")
@CrossOrigin("*")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @Autowired
    public VocabularyController(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getBooks() {
        try {
            List<Book> books = vocabularyService.getBooks();
            if (books != null) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                String errorMsg = "Cannot get books from database";
                return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            String errorMsg = "Error while requesting";
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFiles(@RequestParam(name = "file") MultipartFile file) {
        try {
            System.out.println("uploaded");
            Boolean succeeded = vocabularyService.addMultipartFileHelper(file);
            if (succeeded)  {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else  {
                String errorMsg = "Can't add book to database";
                return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            String errorMsg = "Error while requesting";
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }
    }
}
