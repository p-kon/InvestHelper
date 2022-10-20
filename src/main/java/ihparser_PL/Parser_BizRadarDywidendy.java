package ihparser_PL;

import ihDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Year;
import java.util.Map;

public class Parser_BizRadarDywidendy {

    public static void UpdateAktywo(AktywoPL aktywo, Map<String, String> responseCookies) throws IOException {

        int previousYear = Year.now().getValue() - 1;
        String adres = aktywo.getWebPage4_BizRadarDywidendy();
        Element bizRadtable = Jsoup.connect(adres).cookies(responseCookies).execute().parse().select("div.table-c > table").first();

        if (bizRadtable!=null) {
            int indexDywidendaNaAkcje = bizRadtable.selectFirst("th:contains(łącznie dywidenda na akcję (zł))").elementSiblingIndex();
            int indexStopaDywidendy = bizRadtable.selectFirst("th:contains(stopa dywidendy*)").elementSiblingIndex();
            Elements previousYearRowEl = bizRadtable.select("tr:has(td:matches(^" + previousYear + "$)) td");

            if(previousYearRowEl!=null) {
                double dywNaAkcje = Double.parseDouble(
                        previousYearRowEl.get(indexDywidendaNaAkcje).text().replaceAll("-", "0"));
                double stopaDywidendy = Double.parseDouble(
                        previousYearRowEl.get(indexStopaDywidendy).text()
                                .replaceAll("%", "")
                                .replaceAll("-", "0")
                                .trim());
                aktywo.setDividends(dywNaAkcje);
                aktywo.setDY(stopaDywidendy);
            }

            int ileLatWzrost = 0;

            Double[] columnDywidendaNaAkcje = bizRadtable.select("tr td:eq(" + indexDywidendaNaAkcje + ")").stream()
                    .map(el -> Double.parseDouble(el.text().replaceAll("-", "0").trim()))
                    .toArray(Double[]::new);

                for (int i = 1; i < columnDywidendaNaAkcje.length; i++) {
                    if (columnDywidendaNaAkcje[i] > columnDywidendaNaAkcje[i - 1]) {
                        ileLatWzrost = i - 1;
                        break;
                    } else {
                        ileLatWzrost = i;
                    }
                }

            aktywo.setDividendsYearsOfGrowth(ileLatWzrost);
            aktywo.setDividendsRecordYears(columnDywidendaNaAkcje.length);

            long ileZer = bizRadtable.select("tr td:eq(" + indexDywidendaNaAkcje + ")").stream()
                    .filter(el -> el.text().equals("-"))
                    .count();

            aktywo.setDividendsYearsWithoutPayout((byte) ileZer);
        }
    }
}

