package bfiparsery;

import bfiDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Parser_BizRadarNotowania {

    public static void UpdateAktywo(AktywoPL aktywo) throws IOException {

        Document document;
        String adres = aktywo.getWebPage1_bizRadarNotowania();

            document = Jsoup.connect(adres).get();
            document.select("div#fullname-container h2").stream().findFirst().ifPresent(x->aktywo.setCompanyName(x.text()));
            document.select("tr:contains(branża) a[href]").stream().findFirst().ifPresent(x->aktywo.setSector(x.text()));
            document.select("tr:contains(kapitalizacja) td").stream().findFirst().ifPresent(x->aktywo.setCapitalization(Long.parseLong(x.text().replaceAll(" ",""))));
            document.select("tr:contains(C/Z) td.value").stream().findFirst().ifPresent(x->aktywo.setC_z(Double.parseDouble(x.text())));
            document.select("tr:contains(C/WK) td.value").stream().findFirst().ifPresent(x->aktywo.setCwk(Double.parseDouble(x.text())));
            document.select("tr:contains(ROE) td.value").stream().findFirst().ifPresent(x->aktywo.setRoe(Double.parseDouble(x.text().replaceAll("%",""))));
            document.select("tr:contains(piotroski) td.value").stream().findFirst().ifPresent(x->aktywo.setPiotroskiFscore(Byte.parseByte(x.text())));
            document.select("tr:contains(Altman EM) td.value").stream().findFirst().ifPresent(x->aktywo.setAltmanEMScore((x.text())));
            document.select("tr:contains(CEO:) td").stream().findFirst().ifPresent(x->aktywo.setCeo(x.text()));

    }
}
