package bfiparsery;

import bfiDataModel.AktywoPL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.Year;

public class Parser_BizRadarRaportyNew {

    Document document;

    public static void UpdateAktywo(AktywoPL aktywo) throws IOException {

        String adres = aktywo.getWebPage2_bizRadarRaporty();

        int currentYear = Year.now().getValue();
        Element bizRadtable = Jsoup.connect(adres).get().select("table.report-table").first();

        if (bizRadtable.selectFirst("th:contains(" + (currentYear-1) + ")") != null)
            updatePreviousYear(aktywo, bizRadtable, currentYear);

        if (bizRadtable.selectFirst("th:contains(" + (currentYear-2) + ")") != null)
            updateTwoYearsBack(aktywo, bizRadtable, currentYear);

        if (bizRadtable.selectFirst("th:contains(" + (currentYear-3) + ")") != null)
            updateThreeYearsBack(aktywo, bizRadtable, currentYear);
    }

    private static void updatePreviousYear(AktywoPL aktywo,Element bizRadtable, int currentYear) {
        int index = bizRadtable.selectFirst("th:contains(" + (currentYear-1) + ")").elementSiblingIndex();
        aktywo.setLastYearRevenue(Integer.parseInt(bizRadtable.selectFirst("tr:contains(Przychody ze sprzedaży)").select("td").get(index).selectFirst("span.value").text().replaceAll(" ", "")));
        aktywo.setLastYearRevenueComparisonYY(Double.parseDouble(bizRadtable.selectFirst("tr:contains(Przychody ze sprzedaży)").select("td").get(index).selectFirst("div.changeyy span.q_ch_per").text().replaceAll("[+%]","")));
        aktywo.setLastYearNetProfit(Integer.parseInt(bizRadtable.selectFirst("tr:contains(Zysk Netto)").select("td").get(index).selectFirst("span.value").text().replaceAll(" ", "")));
        aktywo.setLastYearNetProfitComparisonYY(Double.parseDouble(bizRadtable.selectFirst("tr:contains(Zysk Netto)").select("td").get(index).selectFirst("div.changeyy span.q_ch_per").text().replaceAll("[+%]","")));
        aktywo.setLastYearEBITDAComparisonYY(Double.parseDouble(bizRadtable.selectFirst("tr:contains(EBITDA)").select("td").get(index).selectFirst("div.changeyy span.q_ch_per").text().replaceAll("[+%]","")));
        aktywo.setLastYearEBITDA(Integer.parseInt(bizRadtable.selectFirst("tr:contains(EBITDA)").select("td").get(index).selectFirst("span.value").text().replaceAll("[ ]","")));
    }

    private static void updateTwoYearsBack(AktywoPL aktywo,Element bizRadtable, int currentYear) {
        int index = bizRadtable.selectFirst("th:contains(" + (currentYear-2) + ")").elementSiblingIndex();
        aktywo.setTwoYearsBackRevenue(Integer.parseInt(bizRadtable.selectFirst("tr:contains(Przychody ze sprzedaży)").select("td").get(index).selectFirst("span.value").text().replaceAll(" ", "")));
        aktywo.setTwoYearsBackRevenueComparisonYY(Double.parseDouble(bizRadtable.selectFirst("tr:contains(Przychody ze sprzedaży)").select("td").get(index).selectFirst("div.changeyy span.q_ch_per").text().replaceAll("[+%]","")));
        aktywo.setTwoYearsBackNetProfit(Integer.parseInt(bizRadtable.selectFirst("tr:contains(Zysk Netto)").select("td").get(index).selectFirst("span.value").text().replaceAll(" ", "")));
        aktywo.setTwoYearsBackNetProfitComparisonYY(Double.parseDouble(bizRadtable.selectFirst("tr:contains(Zysk Netto)").select("td").get(index).selectFirst("div.changeyy span.q_ch_per").text().replaceAll("[+%]","")));
    }

    private static void updateThreeYearsBack(AktywoPL aktywo,Element bizRadtable, int currentYear) {
        int index = bizRadtable.selectFirst("th:contains(" + (currentYear-3) + ")").elementSiblingIndex();
        aktywo.setThreeYearsBackRevenue(Integer.parseInt(bizRadtable.selectFirst("tr:contains(Przychody ze sprzedaży)").select("td").get(index).selectFirst("span.value").text().replaceAll(" ", "")));
        aktywo.setThreeYearsBackRevenueComparisonYY(Double.parseDouble(bizRadtable.selectFirst("tr:contains(Przychody ze sprzedaży)").select("td").get(index).selectFirst("div.changeyy span.q_ch_per").text().replaceAll("[+%]","")));
        aktywo.setThreeYearsBackNetProfit(Integer.parseInt(bizRadtable.selectFirst("tr:contains(Zysk Netto)").select("td").get(index).selectFirst("span.value").text().replaceAll(" ", "")));
        aktywo.setThreeYearsBackNetProfitComparisonYY(Double.parseDouble(bizRadtable.selectFirst("tr:contains(Zysk Netto)").select("td").get(index).selectFirst("div.changeyy span.q_ch_per").text().replaceAll("[+%]","")));
    }
    }

