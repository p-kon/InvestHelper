package ihparser_PL;

import ihDataModel.AktywoPL;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Parser_BankierWladzaZatrudnienie {

    public static void UpdateAktywo(AktywoPL aktywo) throws IOException {
        String adres = aktywo.getWebpage7_bankierWladzeZatrudnienie();
        String prezes = Jsoup.connect(adres).get().selectFirst("td:contains(Prezes Zarządu)").parent().selectFirst("td.textNowrap").text();
        short prezesOdRoku = Short.parseShort(prezes.substring(0,4));
        aktywo.setPrezesOdLat(prezesOdRoku);
    }
}
