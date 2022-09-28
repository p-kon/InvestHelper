package bfiparsery_USA;

import bfiDataModel.AktywoUSA;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Parser_SeekingAlphaBasic {

    public static void UpdateAktywoUS(AktywoUSA aktywo) throws IOException {

        setCompanyNameTickerSector (aktywo);
        setMarketCap (aktywo);
        setMetricsFromApi(aktywo);
        setIncomeStatement(aktywo);
    }

    private static void setIncomeStatement(AktywoUSA aktywo) throws IOException {
        String ticker = aktywo.getTicker();
        String adres = "https://seekingalpha.com/api/v3/symbols/" + ticker + "/fundamentals_metrics?period_type=annual&statement_type=income-statement";

        createJsonObjectFromAdres(adres);


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

    private static void setMarketCap(AktywoUSA aktywo) throws IOException {
        String id = aktywo.getId();
        String adres = "https://finance-api.seekingalpha.com/real_time_quotes?sa_ids=" + id;

        JSONObject jsonObject = createJsonObjectFromAdres(adres);

        String market_cap_string = jsonObject.query("/real_time_quotes/0/market_cap").toString();
        long market_cap = Long.parseLong(market_cap_string);
        aktywo.setMarketCap(market_cap);
    }

    private static void setCompanyNameTickerSector(AktywoUSA aktywo) throws IOException {

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

    private static JSONObject createJsonObjectFromAdres(String adres) throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        String jsonText = webClient.getPage(adres).getWebResponse().getContentAsString();
        return new JSONObject(jsonText);
    }
}
