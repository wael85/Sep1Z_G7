import model.data.Data;
import model.data.ReadFromTXTFile;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Data storage = new Data();
        try {
            ArrayList<String[]> dataOnFile = ReadFromTXTFile.ReadTXTFile("C:\\Users\\drwae\\Desktop\\test.txt");
            storage.setStudentsData(dataOnFile);
            System.out.println(storage.students);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
