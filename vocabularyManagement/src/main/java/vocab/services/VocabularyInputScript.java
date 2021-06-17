package vocab.services;

import java.io.*;
import java.util.*;

import jdk.jfr.Description;
import vocab.domain.*;

public final class VocabularyInputScript {

    private static List<String> readFile(File file) throws IOException {
        List<String> input = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = reader.readLine();
        while(tempString!=null && !tempString.isEmpty()){
            input.add(tempString);
            tempString = reader.readLine();
        }
        reader.close();
        return input;
    }

    private static List<Translation> createTranslations(List<String> input){
        List<Translation> translations = new ArrayList<Translation>();
        for (String string : input){
            string = string.split("\\{\\{\\{}}}")[0];
            string = string.replaceAll("\\{", "");
            string = string.replaceAll("}, ", "},");
            String[] tempArray = string.split(" : ");
            String[] from = tempArray[0].split("},");
            String[] to = tempArray[1].split("},");
            List<String> fromList = new ArrayList<>();
            for (String f: from) {
                fromList.add(f.replaceAll("}",""));
            }
            List<String> toList = new ArrayList<>();
            for (String t: to) {
                toList.add(t.replaceAll("}",""));
            }
            translations.add(new Translation(fromList, toList));
        }
        return translations;
    }

    public static Book parseFileToBook(File file) throws IOException {
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
}