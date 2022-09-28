package bfiFileOperations;

import bfiDataModel.AktywoPL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class HashMapFromArrayList {

    public static HashMap<String, AktywoPL> hashMapLinkiAktywaPL(ArrayList<String> arrayList) throws IOException {

        HashMap<String, AktywoPL> hashMap = new HashMap<>();

        for (String line : arrayList) {
            String[] splitData = line.split(";");
            AktywoPL aktywoPL = new AktywoPL(splitData[1], splitData[2], splitData[3], splitData[4], splitData[5],splitData[6], splitData[7], splitData[0]);
            hashMap.put(splitData[0], aktywoPL);
        }
        return hashMap;
    }

    public static HashMap<String, String[]> hashMapLoginDetails(ArrayList<String> arrayList) {
        HashMap<String, String[]> hashMap = new HashMap<String, String[]>();

        for (String line : arrayList) {
            String[] splitData = line.split(";");
            hashMap.put(splitData[0], splitData);
        }
        return hashMap;
    }


}