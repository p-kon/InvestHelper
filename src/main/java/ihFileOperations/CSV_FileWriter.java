package ihFileOperations;

import ihDataModel.Aktywo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class CSV_FileWriter {

    public static void HashMapa(Aktywo akt) {

    };
    public static void HashMap2File(HashMap<String, ?> hashMap, String filePath, boolean createHeaders, String headers, boolean appendData) throws IOException {

        File file = new File(filePath);
        createFile(file);

        Iterator<Aktywo> aktywoIterator = (Iterator<Aktywo>) hashMap.values().iterator();
        FileWriter fileWriter = new FileWriter(file, appendData);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        if (createHeaders) {
            bufferedWriter.write(headers);
        }

        while (aktywoIterator.hasNext()) {
            Aktywo aktywo = aktywoIterator.next();
            bufferedWriter.newLine();
            bufferedWriter.write(aktywo.toCSVString());
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
    }

    private static void createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("nie udało sie utworzyć pliku");
                e.printStackTrace();
            }
        }
    }


}
