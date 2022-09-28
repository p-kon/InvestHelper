package bfiparsery;

import bfiDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.io.IOException;

public class Parser_BizRadarWskaznikiZadluzenia {

    public static void UpdateAktywo(AktywoPL aktywo) throws IOException {

        String adres = aktywo.getWebPage5_bizRadarZadluzenie();
        Element bizRadtable = Jsoup.connect(adres).get().select("table.report-table").first();

        aktywo.setForeignCapital(
                Double.parseDouble(
                        bizRadtable.selectFirst("tr:contains(Zastosowanie kapitału obcego)").selectFirst("td.h.newest span.value").text()
                ));
        aktywo.setNetDebt(
                Long.parseLong(
                        bizRadtable.selectFirst("tr:contains(Zadłużenie netto)").selectFirst("td.h.newest span.value").text()
                                .replaceAll(" ", "")
                ));
    }
}
