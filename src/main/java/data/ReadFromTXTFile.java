package data;

import courses.Course;
import person.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ReadFromTXTFile {
    public static ArrayList<String[]> ReadTXTFile(String filePath) throws FileNotFoundException {
        ArrayList<String[]> FileData = new ArrayList<>();
      try {
          File dataFile = new File(filePath);
          Scanner readFile = new Scanner(dataFile);
          while (readFile.hasNext()){
              String line = readFile.nextLine();
              String[] data = line.split(",");
              FileData.add(data);
          }
          readFile.close();
      }catch (FileNotFoundException e){
          throw e;
      }
      return FileData;
    }

}
