package ihparser_PL;

import ihDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Parser_Bankier {

    public static void UpdateAktywo(AktywoPL aktywo) throws IOException {

        String adres = aktywo.getWebPage3_bankierPopularnosc();
        Document document = Jsoup.connect(adres).get();
        Element element = document.selectFirst("tr:has(td.colTicker:contains(" + aktywo.getCode() + ")) td[data-value]");
        if (element!=null) {
            aktywo.setFinancialLiquidity(element.text());
        } else {
            aktywo.setFinancialLiquidity("brak danych");
        }

    }
}
