package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
	 /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames() {

        BufferedReader reader = null;
        List<String> names = null;
        try {

            Path path = Paths.get("firstname_males.txt");
            reader = Files.newBufferedReader(path);
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            System.out.println("-- finally block --");
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } return names;
    }



    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     * @return
     */
    public static List<String> getFemaleFirstNames() {

        List<String> names = null;

        try ( // We use parentheses here because it is try- with resources, it auto closes the resources, thus we dont need to use finally block
                BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt"))
        ) {
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
        System.out.println(e);
        }
        return names;
        }


    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException{

        List<String> names = null;
        BufferedReader reader = null;

        try{
                reader = Files.newBufferedReader(Paths.get("lastnames.txt"));
                names = reader.lines()
                .flatMap(line -> Stream.of(line.split(",")))
                .collect(Collectors.toList());


        } finally{
            if(reader != null){
                reader.close();
            }
        }
       if(names != null) {
           return names;
       } else throw new IOException("lastname does not exist");
    }


    public static void saveLastNames(List <String> lastNames) throws ExceptionCustom {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"));
        ) {
            for (String toWrite : lastNames) {
                writer.append(toWrite + ",");
                if (lastNames.equals(toWrite)) throw new ExceptionCustom(writer.toString(),"lastName was duplicate",23);
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void saveFemaleNames(List <String> femaleNames) throws ExceptionCustom {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"));
        ) {
            for (String toWrite : femaleNames) {
                writer.append(toWrite + ",");
                if (femaleNames.equals(toWrite))
                    throw new ExceptionCustom(writer.toString(), "femaleName was duplicate", 23);
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);

        }
    }



    public static void saveMaleNames(List <String> maleNames) throws ExceptionCustom{
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))
        ) {
            for(String toWrite : maleNames){
                writer.append(toWrite+",");
                if (maleNames.equals(toWrite))
                    throw new ExceptionCustom(writer.toString(), "maleName was duplicate", 23);
            }
            writer.flush();
    }catch (IOException e) {
            System.out.println(e);
        }


}

}
