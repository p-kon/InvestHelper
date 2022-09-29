package ihparser_USA;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import ihDataModel.AktywoUSA;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Year;
import java.util.HashMap;

public class Parser_SeekingAlphaBasic {

    public static void UpdateAktywoUS(AktywoUSA aktywo) throws IOException {

        setCompanyName_Id_Sector (aktywo);
        setMarketCap (aktywo);
        setMetricsFromApi(aktywo);
        setYearsOfGrowth(aktywo);
        setPEandPB(aktywo);
        setProfitabilityIndicators(aktywo);
        setIncomeStatement(aktywo);
    }

    private static void setCompanyName_Id_Sector(AktywoUSA aktywo) throws IOException {

        String ticker = aktywo.getTicker();
        String adres = "https://seekingalpha.com/api/v3/symbols/" + ticker;

        JSONObject jsonObject = createJsonObjectFromAdres(adres);

        String companyName = jsonObject.query("/data/attributes/companyName").toString();
        String id = jsonObject.query("/data/id").toString();
        String sector = jsonObject.query("/included/0/attributes/name").toString();

        aktywo.setCompany(companyName);
        aktywo.setId(id);
        aktywo.setSector(sector);
    }

    private static void setMarketCap(AktywoUSA aktywo) throws IOException {
        String id = aktywo.getId();
        String adres = "https://finance-api.seekingalpha.com/real_time_quotes?sa_ids=" + id;

        JSONObject jsonObject = createJsonObjectFromAdres(adres);

        String market_cap_string = jsonObject.query("/real_time_quotes/0/market_cap").toString();
        long market_cap = Long.parseLong(market_cap_string);
        aktywo.setMarketCap(market_cap);
    }

    private static void setMetricsFromApi(AktywoUSA aktywo) throws IOException {
        String ticker = aktywo.getTicker();
        String adres = "https://seekingalpha.com/api/v3/metrics?filter[fields]=div_yield_fwd%2Cdiv_rate_fwd%2Cdividend_growth%2Cdiv_grow_rate5%2Cpayout_ratio%2Cpe_nongaap_fy1%2Cpb_fy1_ratio%2Cgross_margin%2C&filter[slugs]=" + ticker;

        JSONObject jsonObject = createJsonObjectFromAdres(adres);
        JSONArray jsArrayIncluded = jsonObject.getJSONArray("included");
        JSONArray jaArrayData = jsonObject.getJSONArray("data");
        HashMap<String, String> hashMapNameAndID = new HashMap<String, String>();
        HashMap<String, String> hashMapIdAndValue = new HashMap<String, String>();

        jsArrayIncluded.forEach(item -> {
            JSONObject jsonObjectItem = (JSONObject) item;
            Object id = jsonObjectItem.get("id");
            Object name = jsonObjectItem.query("/attributes/field");
            if (name!=null)
                hashMapNameAndID.put(name.toString(),id.toString());
        });

        jaArrayData.forEach(item -> {
            JSONObject jsonObjectItem = (JSONObject) item;
            Object id = jsonObjectItem.query("/relationships/metric_type/data/id");
            Object value = jsonObjectItem.query("/attributes/value");
            if (value!=null)
                hashMapIdAndValue.put(id.toString(),value.toString());
        });

        double div_yield_fwd = Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("div_yield_fwd")));
        double div_rate_fwd = Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("div_rate_fwd")));
        //byte dividend_growth = (byte) Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("dividend_growth")));
        double div_grow_rate5 = Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("div_yield_fwd")));
        double payout_ratio = Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("payout_ratio")));
        //double pe_nongaap_fy1 = Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("pe_nongaap_fy1")));
        //double pb_fy1_ratio = Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("pb_fy1_ratio")));
        double gross_margin = Double.parseDouble(hashMapIdAndValue.get(hashMapNameAndID.get("gross_margin")));

        aktywo.setDy(div_yield_fwd);
        aktywo.setDividend(div_rate_fwd);
        //aktywo.setYearsOfGrowth(dividend_growth);
        aktywo.setFiveYearsGrowthRate(div_grow_rate5);
        aktywo.setPayoutRatio(payout_ratio);
        //aktywo.setPe(pe_nongaap_fy1);
        //aktywo.setPb(pb_fy1_ratio);
        aktywo.setGrossProfitMargin(gross_margin);


    }

    private static void setYearsOfGrowth(AktywoUSA aktywo) throws IOException {

        String adress = aktywo.getWebPage1_dividend();
        Document document = Jsoup.connect(adress).get();
        Element element = document.selectFirst("div.t-flex:containsOwn(Years of Dividend Increase)").parent().selectFirst("div.t-leading-snug");
        byte yearsOfGrowth = Byte.parseByte(element.text().split(" ")[0]);

        aktywo.setYearsOfGrowth(yearsOfGrowth);
    }

    private static void setPEandPB(AktywoUSA aktywo) throws IOException {

        String addressPE = aktywo.getWebPage2_macrotrendsPE();
        Document documentPE = Jsoup.connect(addressPE).get();
        Element elementPE = documentPE.selectFirst("div#main_content div p strong");
        Double payoutRatio =    Double.parseDouble( elementPE.text() );

        aktywo.setPe(payoutRatio);

        String addressPB = aktywo.getWebPage3_macrotrendsPB();
        Document documentPB = Jsoup.connect(addressPB).get();
        Element elementPB = documentPB.selectFirst("div#main_content div p strong");
        Double payoutBookRatio = Double.parseDouble( elementPB.text() );

        aktywo.setPb(payoutBookRatio);
    }

    private static void setProfitabilityIndicators(AktywoUSA aktywo) throws IOException {

        String address = "https://www.gurufocus.com/stock/" + aktywo.getTicker();

        Document document = Jsoup.connect(address).get();

        Element el_roe = document.selectFirst("tr.stock-indicators-table-row a[href*=/term/ROE/]").parent().parent();
            String roe = el_roe.selectFirst("span.p-l-sm").text();

        Element el_roa = document.selectFirst("tr.stock-indicators-table-row a[href*=/term/ROA/]").parent().parent();
            String roa = el_roa.selectFirst("span.p-l-sm").text();

        Element el_roic = document.selectFirst("tr.stock-indicators-table-row a[href*=/term/ROIC/]").parent().parent();
            String roic = el_roic.selectFirst("span.p-l-sm").text();

        Element el_fscore = document.selectFirst("tr.stock-indicators-table-row a[href*=/term/fscore/]").parent().parent();
            String fscore = el_fscore.selectFirst("span.p-l-sm").text();

        Element el_zscore = document.selectFirst("tr.stock-indicators-table-row a[href*=/term/zscore/]").parent().parent();
            String zscore = el_zscore.selectFirst("span.p-l-sm").text();

        Element el_netMargin = document.selectFirst("tr.stock-indicators-table-row a[href*=/term/netmargin/]").parent().parent();
            String netMargin = el_netMargin.selectFirst("span.p-l-sm").text();

        aktywo.setRoe(Double.parseDouble(roe));
        aktywo.setRoa(Double.parseDouble(roa));
        aktywo.setRoic(Double.parseDouble(roic));
        aktywo.setPiotroskiFscore(Byte.parseByte(fscore));
        aktywo.setAltmanZScore(Double.parseDouble(zscore));
        aktywo.setNetMargin(Double.parseDouble(fscore));
    }

    private static void setIncomeStatement(AktywoUSA aktywo) throws IOException {
        String ticker = aktywo.getTicker();
        String adres = "https://seekingalpha.com/api/v3/symbols/" + ticker + "/fundamentals_metrics?period_type=annual&statement_type=income-statement";

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        String jsonText = webClient.getPage(adres).getWebResponse().getContentAsString();

        JSONArray jsonArrayTop = new JSONArray(jsonText);
        String xml = XML.toString(jsonArrayTop);
        Document doc = Jsoup.parse(xml, Parser.xmlParser());

        int previousYear = Year.now().getValue()-1;
        int previous2Year = Year.now().getValue()-2;
        int previous3Year = Year.now().getValue()-3;

        Elements revenues1yearBack = doc.selectFirst("rows value:matches(^Revenues$)").siblingElements().select("cells name:matches( " + previousYear + "$)").first().siblingElements();
        Elements netIncomes1yearBack = doc.selectFirst("rows value:matches(^Net Income$)").siblingElements().select("cells name:matches( " + previousYear + "$)").first().siblingElements();
        Elements ebidta1yearBack = doc.selectFirst("rows value:matches(^EBITDA$)").siblingElements().select("cells name:matches( " + previousYear + "$)").first().siblingElements();

        Elements revenues2yearBack = doc.selectFirst("rows value:matches(^Revenues$)").siblingElements().select("cells name:matches( " + previous2Year + "$)").first().siblingElements();
        Elements netIncomes2yearBack = doc.selectFirst("rows value:matches(^Net Income$)").siblingElements().select("cells name:matches( " + previous2Year + "$)").first().siblingElements();
        //Elements ebidta2yearBack = doc.selectFirst("rows value:matches(^EBITDA$)").siblingElements().select("cells name:matches( " + previous2Year + "$)").first().siblingElements();

        Elements revenues3yearBack = doc.selectFirst("rows value:matches(^Revenues$)").siblingElements().select("cells name:matches( " + previous3Year + "$)").first().siblingElements();
        Elements netIncomes3yearBack = doc.selectFirst("rows value:matches(^Net Income$)").siblingElements().select("cells name:matches( " + previous3Year + "$)").first().siblingElements();
        //Elements ebidta3yearBack = doc.selectFirst("rows value:matches(^EBITDA$)").siblingElements().select("cells name:matches( " + previous3Year + "$)").first().siblingElements();

        String raw_value1r = revenues1yearBack.select("raw_value").text().replaceAll("[%()]","");
        String raw_value2r = revenues2yearBack.select("raw_value").text().replaceAll("[%()]","");
        String raw_value3r = revenues3yearBack.select("raw_value").text().replaceAll("[%()]","");

        String raw_value1i = netIncomes1yearBack.select("raw_value").text().replaceAll("[%()]","");
        String raw_value2i = netIncomes2yearBack.select("raw_value").text().replaceAll("[%()]","");
        String raw_value3i = netIncomes3yearBack.select("raw_value").text().replaceAll("[%()]","");

        String yoy_value1r = revenues1yearBack.select("yoy_value").text().replaceAll("[%()]","");
        String yoy_value2r = revenues2yearBack.select("yoy_value").text().replaceAll("[%()]","");
        String yoy_value3r = revenues3yearBack.select("yoy_value").text().replaceAll("[%()]","");

        String yoy_value1i = netIncomes1yearBack.select("yoy_value").text().replaceAll("[%()]","");
        String yoy_value2i = netIncomes2yearBack.select("yoy_value").text().replaceAll("[%()]","");
        String yoy_value3i = netIncomes3yearBack.select("yoy_value").text().replaceAll("[%()]","");

        String raw_value1e = ebidta1yearBack.select("raw_value").text().replaceAll("[%()]","");
        String yoy_value1e = ebidta1yearBack.select("yoy_value").text().replaceAll("[%()]","");

        aktywo.setRevenues(Long.parseLong(raw_value1r));
        aktywo.setRevenues_2y(Long.parseLong(raw_value2r));
        aktywo.setRevenues_3y(Long.parseLong(raw_value3r));

        aktywo.setNetIncome(Long.parseLong(raw_value1i));
        aktywo.setNetIncome_2y(Long.parseLong(raw_value2i));
        aktywo.setNetIncome_3y(Long.parseLong(raw_value3i));

        aktywo.setRevenues_y2y(Double.parseDouble(yoy_value1r));
        aktywo.setRevenues_y2y_2y(Double.parseDouble(yoy_value2r));
        aktywo.setRevenues_y2y_3y(Double.parseDouble(yoy_value3r));

        aktywo.setNetIncome_y2y(Double.parseDouble(yoy_value1i));
        aktywo.setNetIncome_y2y_2y(Double.parseDouble(yoy_value2i));
        aktywo.setNetIncome_y2y_3y(Double.parseDouble(yoy_value3i));

        aktywo.setEbidta(Integer.parseInt(raw_value1e));
        aktywo.setEbidta_y2y(Double.parseDouble(yoy_value1e));

    }

    private static JSONObject createJsonObjectFromAdres(String adres) throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        String jsonText = webClient.getPage(adres).getWebResponse().getContentAsString();
        return new JSONObject(jsonText);
    }
}
