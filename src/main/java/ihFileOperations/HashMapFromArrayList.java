package ihFileOperations;

import ihDataModel.Aktywo;
import ihDataModel.AktywoPL;
import ihDataModel.AktywoUSA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class HashMapFromArrayList {

    //remove bellow after positive tests
    public static HashMap<String, Aktywo> hashMapLinkiAktywaPL(ArrayList<String> arrayList) throws IOException {

        HashMap<String, Aktywo> hashMap = new HashMap<>();

        for (String line : arrayList) {
            String[] splitData = line.split(";");
            if (splitData[0].equals("PL")) {
                AktywoPL aktywoPL = new AktywoPL(splitData[1], splitData[2], splitData[3], splitData[4], splitData[5], splitData[6], splitData[7], splitData[8]);
                hashMap.put(splitData[1], aktywoPL);
            } else if (splitData[0].equals("US")) {
                AktywoUSA aktywoUSA = new AktywoUSA(splitData[1], splitData[2], splitData[3], splitData[4]);
                hashMap.put(splitData[1], aktywoUSA);
            }
        }
        return hashMap;
    }

    public static HashMap<String, ?> aktywaHashMapFromArrayList(ArrayList<String> arrayList, String mapType) {

        HashMap<String, Aktywo> hashMap = new HashMap<>();

        for (String line : arrayList) {
            String[] splitData = line.split(";");

            if (splitData[0].equals(mapType)) {
                switch (mapType) {
                    case "PL":
                        AktywoPL aktywoPL = new AktywoPL(splitData[1], splitData[2], splitData[3], splitData[4], splitData[5], splitData[6], splitData[7], splitData[8]);
                        hashMap.put(splitData[1], aktywoPL);
                        break;
                    case "US":
                        AktywoUSA aktywoUSA = new AktywoUSA(splitData[1], splitData[2], splitData[3], splitData[4]);
                        hashMap.put(splitData[1], aktywoUSA);
                        break;
                }
            }
        }
        return hashMap;
    }


    public static HashMap<String, String[]> loginHashMapArrayList(ArrayList<String> arrayList) {
        HashMap<String, String[]> hashMap = new HashMap<String, String[]>();

        for (String line : arrayList) {
            String[] splitData = line.split(";");
            hashMap.put(splitData[0], splitData);
        }
        return hashMap;
    }


}