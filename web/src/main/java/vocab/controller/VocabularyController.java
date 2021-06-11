package vocab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vocab.domain.Book;
import vocab.services.VocabularyService;
import vocab.services.VocabularyServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
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

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadFiles(@RequestParam("file") MultipartFile file) {
        try {
            File convertedFile = new File("C:/work" + file.getOriginalFilename());
            convertedFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
            fileOutputStream.write(file.getBytes());
            vocabularyService.addFile(convertedFile);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
