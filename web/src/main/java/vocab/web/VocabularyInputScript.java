package vocab.web;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jdk.jfr.Description;
import vocab.domain.*;

public final class VocabularyInputScript {

    private static final String resourceString = "\\web\\src\\main\\resources\\vocabulary_input";
    private static final File resourceDir = new File(System.getProperty("user.dir")+resourceString);

    private static List<String> readFile(File file){
        List<String> input = new ArrayList<String>();
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
        List<Translation> translations = new ArrayList<Translation>();
        for (String string : input){
            string = string.replaceAll("}", "");
            string = string.replaceAll("\\{", "");
            String[] tempArray = string.split(" : ");
            translations.add(new Translation(tempArray[0], tempArray[1]));
        }
        return translations;
    }

    public static List<Book> createBooks(){
        Map<String, Book> books = new HashMap<String, Book>();
        for (File file : resourceDir.listFiles()){
            List<String> input = readFile(file);
            String[] description = input.get(0).split("}\\{");
            String bookName = description[3].replaceAll("}", "").replaceAll("\\{", "");
            String categoryName = description[0].replaceAll("}", "").replaceAll("\\{", "");
            List<Translation> translations = createTranslations(input.subList(1, input.size()));
            Category category = new Category(categoryName, translations);
            books.put(bookName, new Book());
            Book newBook = books.get(bookName);
            newBook.setName(bookName);
            newBook.getCategories().add(category);
        }
        return new ArrayList<Book>(books.values());
    }

    //Test
    public static void main(String[] args) {
        List<Book> books = createBooks();
        for (Book book : books) {
            System.out.println(book);
            System.out.println("");
        }
    }
}