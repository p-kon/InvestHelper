package ihFileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSV_FileReader {

    public static ArrayList LinesToArrayList(String filePath) throws IOException {

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList arrayList = new ArrayList();

        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            arrayList.add(readLine);
        }
        bufferedReader.close(); //new
        fileReader.close(); //new
        return arrayList;

    }
}