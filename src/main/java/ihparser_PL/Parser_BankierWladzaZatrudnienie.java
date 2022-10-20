package ihparser_PL;

import ihDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Parser_BankierWladzaZatrudnienie {

    public static void UpdateAktywo(AktywoPL aktywo) throws IOException {
        String adres = aktywo.getWebpage7_bankierWladzeZatrudnienie();
        Document document = Jsoup.connect(adres).get();
        Element element = document.selectFirst("tr:contains(Prezes Zarządu) td.textNowrap");

        if (element!=null) {
            String prezes = element.text();
            short prezesOdRoku = Short.parseShort(prezes.substring(0,4));
            aktywo.setPrezesOdLat(prezesOdRoku);
        }

    }
}
