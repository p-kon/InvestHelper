package bfiparsery;

import bfiDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Parser_Bankier {

    public static void UpdateAktywo(AktywoPL aktywo) throws IOException {

        String adres = aktywo.getWebPage3_bankierPopularnosc();
        Document document = Jsoup.connect(adres).get();

        try {
            String plynnosc = document
                    .selectFirst("table.sortTableMixedData")
                    .selectFirst("td.colTicker:contains(" + aktywo.getCode() + ")")
                    .parent()
                    .selectFirst("td[data-value]")
                    .text();
            aktywo.setFinancialLiquidity(plynnosc);
        } catch (NullPointerException e) {
        }
    }
}
