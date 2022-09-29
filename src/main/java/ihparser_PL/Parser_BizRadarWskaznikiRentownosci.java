package ihparser_PL;

import ihDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Map;

public class Parser_BizRadarWskaznikiRentownosci {

    //roic wymaga premium!!
    public static void UpdateAktywo(AktywoPL aktywo, Map<String, String> responseCookies) throws IOException {

        String adres = aktywo.getWebpage6_bizRadarRentownosc();
        Element bizRadtable = Jsoup.connect(adres).cookies(responseCookies).execute().parse().select("table.report-table").first();

        aktywo.setRoa(Double.parseDouble(
                bizRadtable.selectFirst("tr:contains(ROA)").selectFirst("td.h.newest span.value").text().replaceAll("%", "")
            )/100);

        aktywo.setNetProfitMargin(Double.parseDouble(
                bizRadtable.selectFirst("tr:contains(Marża zysku netto)").selectFirst("td.h.newest span.value").text().replaceAll("%", "")
            )/100);

        aktywo.setProfitMarginOnSales(Double.parseDouble(
                bizRadtable.selectFirst("tr:contains(Marża zysku ze sprzedaży)").selectFirst("td.h.newest span.value").text().replaceAll("%", "")
            )/100);

        System.out.println(aktywo.getCode());

        Element roic = bizRadtable.selectFirst("tr:contains(ROIC)").selectFirst("td.h.newest span.value");
        if (roic != null)
        aktywo.setRoic(Double.parseDouble(roic.text().replaceAll("%", ""))/100);
    }
}
