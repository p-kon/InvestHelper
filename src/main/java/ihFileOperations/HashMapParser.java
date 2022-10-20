package ihFileOperations;

import ihDataModel.AktywoPL;
import ihDataModel.AktywoUSA;
import ihparser_PL.*;
import ihparser_USA.Parser_USA_Aktywo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapParser {

    public static void updateAktywaPL(HashMap<String, AktywoPL> hashMapAktywa, HashMap<String, String[]> hashMapLoginy) throws IOException {

        final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
        String[] bizRadar = hashMapLoginy.get("bizradar");
        String username_BizRad = bizRadar[1]; //email
        String pass_BizRad = bizRadar[2]; //password
        String webPage_BizRad = bizRadar[3];

        // Open page and Login
        Connection.Response loginResponse = Jsoup.connect(webPage_BizRad)
                .userAgent(USER_AGENT)
                .data("email", username_BizRad)
                .data("password", pass_BizRad)
                .method(Connection.Method.POST)
                .execute();

        Map<String, String> responseCookies = loginResponse.cookies();

        Iterator<AktywoPL> aktywoPLIterator = hashMapAktywa.values().iterator();

        while (aktywoPLIterator.hasNext()) {
            AktywoPL aktywo = aktywoPLIterator.next();
            System.out.println(aktywo.getCode());
            Parser_BizRadarNotowania.UpdateAktywo(aktywo);
            Parser_BizRadarRaportyNew.UpdateAktywo(aktywo);
            Parser_Bankier.UpdateAktywo(aktywo);
            Parser_BizRadarDywidendy.UpdateAktywo(aktywo, responseCookies);
            Parser_BizRadarWskaznikiRentownosci.UpdateAktywo(aktywo,responseCookies);
            Parser_BizRadarWskaznikiZadluzenia.UpdateAktywo(aktywo);

            if (!aktywo.getWebpage7_bankierWladzeZatrudnienie().equals("x"))
                Parser_BankierWladzaZatrudnienie.UpdateAktywo(aktywo);

        }
    }

    public static void updateAktywaUSA(HashMap<String, AktywoUSA> hashMapAktywa) throws IOException {
        Iterator<AktywoUSA> aktywoIterator = hashMapAktywa.values().iterator();

        while (aktywoIterator.hasNext()) {
            AktywoUSA aktywo = aktywoIterator.next();
            Parser_USA_Aktywo.UpdateAktywoUS(aktywo);
        }
    }
}
