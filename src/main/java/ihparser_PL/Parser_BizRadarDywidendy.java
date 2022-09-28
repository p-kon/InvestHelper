package bfiparsery;

import bfiDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.time.Year;
import java.util.Map;

public class Parser_BizRadarDywidendy {

    public static void UpdateAktywo(AktywoPL aktywo, Map<String, String> responseCookies) throws IOException {

        String adres = aktywo.getWebPage4_BizRadarDywidendy();
        Element bizRadtable = Jsoup.connect(adres).cookies(responseCookies).execute().parse().select("div.table-c > table").first();

        int previousYear = Year.now().getValue() - 1;


        if (bizRadtable.selectFirst("td:containsOwn(" + previousYear + ")") != null) {

            int indexDywidendaNaAkcje = bizRadtable.selectFirst("th:contains(łącznie dywidenda na akcję (zł))").elementSiblingIndex();
            double dywNaAkcje = Double.parseDouble(bizRadtable.selectFirst("tr td:contains(" + previousYear + ")").parent().select("td").get(indexDywidendaNaAkcje).text().replaceAll("-", "0"));

            int indexStopaDywidendy = bizRadtable.selectFirst("th:contains(stopa dywidendy*)").elementSiblingIndex();
            double stopaDywidendy = Double.parseDouble(bizRadtable.selectFirst("tr td:contains(" + previousYear + ")").parent().select("td").get(indexStopaDywidendy).text().replaceAll("%", "").replaceAll("-", "0").trim());


            int ileLatWzrost = 0;
            Double[] ccolumnDywidendaNaAkcje = bizRadtable.select("tr td:eq(" + indexDywidendaNaAkcje + ")").stream()
                    .map(el -> Double.parseDouble(el.text().replaceAll("-", "0").trim()))
                    .toArray(Double[]::new);

            for (int i = 1; i < ccolumnDywidendaNaAkcje.length; i++) {
                if (ccolumnDywidendaNaAkcje[i] > ccolumnDywidendaNaAkcje[i - 1]) {
                    ileLatWzrost = i - 1;
                    break;
                } else {
                    ileLatWzrost = i;
                }
            }
            long ileZer = bizRadtable.select("tr td:eq(" + indexDywidendaNaAkcje + ")").stream()
                    .filter(el -> el.text().equals("-"))
                    .count();

            aktywo.setDividends(dywNaAkcje);
            aktywo.setDividendsYearsOfGrowth(ileLatWzrost);
            aktywo.setDividendsRecordYears(ccolumnDywidendaNaAkcje.length);
            aktywo.setDY(stopaDywidendy);
            aktywo.setDividendsYearsWithoutPayout((byte) ileZer);


        }
    }
}

