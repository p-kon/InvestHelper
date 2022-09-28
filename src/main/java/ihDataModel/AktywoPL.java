package bfiDataModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AktywoPL implements Serializable {
    private static final long serialVersionUID = 20220502L;
    private String webPage1_bizRadarNotowania;
    private String webPage2_bizRadarRaporty;
    private String webPage3_bankierPopularnosc;
    private String webPage4_bizRadarDywidendy;
    private String webPage5_bizRadarZadluzenie;
    private String webpage6_bizRadarRentownosc;
    private String webpage7_bankierWladzeZatrudnienie;
    private String code;    //ticker

    public AktywoPL(String webPage1_bizRadarNotowania, String webPage2_bizRadarRaporty,
                    String webPage3_bankierPopularnosc, String webPage4_bizRadarDywidendy,
                    String webPage5_bizRadarZadluzenie, String webpage6_bizRadarRentownosc,
                    String webpage7_bankierWladzeZatrudnienie, String code) {
        this.code = code;
        this.webPage1_bizRadarNotowania = webPage1_bizRadarNotowania;
        this.webPage2_bizRadarRaporty = webPage2_bizRadarRaporty;
        this.webPage3_bankierPopularnosc = webPage3_bankierPopularnosc;
        this.webPage4_bizRadarDywidendy = webPage4_bizRadarDywidendy;
        this.webPage5_bizRadarZadluzenie = webPage5_bizRadarZadluzenie;
        this.webpage6_bizRadarRentownosc = webpage6_bizRadarRentownosc;
        this.webpage7_bankierWladzeZatrudnienie = webpage7_bankierWladzeZatrudnienie;
    }

    //biz radar basic
    private String companyName;
    private String sector;
    private double capitalization;
    private double c_z;
    private double cwk;
    private double roe;
    private byte piotroskiFscore;
    private String altmanEMScore;
    private String ceo;

    //biz radar tabela
    private int lastYearRevenue;
    private double lastYearRevenueComparisonYY;
    private int lastYearNetProfit;
    private double lastYearNetProfitComparisonYY;
    private double lastYearEBITDAComparisonYY;
    private int lastYearEBITDA;

    private int twoYearsBackRevenue;
    private double twoYearsBackRevenueComparisonYY;
    private int twoYearsBackNetProfit;
    private double twoYearsBackNetProfitComparisonYY;

    private int threeYearsBackRevenue;
    private double threeYearsBackRevenueComparisonYY;
    private int threeYearsBackNetProfit;
    private double threeYearsBackNetProfitComparisonYY;

    // bankier ranking-popularnosci
    private String financialLiquidity;

    // strefa inwestorow notowania
    private double dividends;
    private double DY;
    private int dividendsYearsOfGrowth;
    private int dividendsRecordYears;
    private byte dividendsYearsWithoutPayout;

    // biz radar wskazniki zadluzenia
    private double foreignCapital;
    private long netDebt;

    // biz radar wskazniki rentownosci
    private double roa;
    private double netProfitMargin;
    private double profitMarginOnSales;
    private double roic;

    //bankier wladza i zatrudnienie
    private byte PrezesOdLat;

    //inne
    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString();

    @Override
    public String toString() {
        return "code= " + code;
    }

    public static String toCSVStringHeaders() {
        return
                "Company;" +
                "Ticker;" +
                "Sektor GPW;" +
                "Kapitalizacja;" +
                "Płynność;" +
                "DY;" +
                "Dyw na akcje (pln);" +
                "Years of growth;" +
                "Record years;" +
                "Years with 0;" +
                "C/Z;" +
                "CWK;" +
                "ROE;" +
                "ROA;" +
                "ROIC;" +
                "Zastosowanie kapitału obcego;" +
                "Zadłużenie netto;" +
                "Piotroski F-Score;" +
                "Altman EM-Score;" +
                "Marża;" +
                "Marża zysku netto;" +
                "Marża zysku ze sprzedaży;" +
                "Przychody ze sprzedaży;" +
                "Przychody ze sprzedaży (2 lata wstecz);" +
                "Przychody ze sprzedaży (3 lata wstecz);" +
                "Zysk Netto;" +
                "Zysk Netto  (2 lata wstecz);" +
                "Zysk Netto  (3 lata wstecz);" +
                "Przychody ze sprzedaży r/r;" +
                "Przychody ze sprzedaży r/r  (2 lata wstecz);" +
                "Przychody ze sprzedaży r/r  (3 lata wstecz);" +
                "Zysk Netto r/r;" +
                "Zysk Netto r/r  (2 lata wstecz);" +
                "Zysk Netto r/r  (3 lata wstecz);" +
                "EBIDTA;" +
                "EBITDA r/r;" +
                "CEO;" +
                "Prezes Od Lat;" +
                "Data";
    }

    public String toCSVString() {
        Double marza = (double) lastYearNetProfit/lastYearRevenue;
        String stringwithdots = companyName + ";" +
                code + ";" +
                sector + ";" +
                capitalization + ";" +
                financialLiquidity + ";" +
                DY+ ";" +
                dividends + ";" +
                dividendsYearsOfGrowth + ";" +
                dividendsRecordYears + ";" +
                dividendsYearsWithoutPayout + ";" +
                c_z + ";" +
                cwk + ";" +
                roe+ ";" +
                roa+ ";" +
                roic+ ";" +
                foreignCapital + ";" +
                netDebt + ";" +
                piotroskiFscore + ";" +
                altmanEMScore + ";" +
                marza + ";" +
                netProfitMargin+ ";" +
                profitMarginOnSales+ ";" +
                lastYearRevenue + ";" +
                twoYearsBackRevenue + ";" +
                threeYearsBackRevenue + ";" +
                lastYearNetProfit + ";" +
                twoYearsBackNetProfit + ";" +
                threeYearsBackNetProfit + ";" +
                lastYearRevenueComparisonYY+ ";" +
                twoYearsBackRevenueComparisonYY+ ";" +
                threeYearsBackRevenueComparisonYY+ ";" +
                lastYearNetProfitComparisonYY+ ";" +
                twoYearsBackNetProfitComparisonYY+ ";" +
                threeYearsBackNetProfitComparisonYY+ ";" +
                lastYearEBITDA + ";" +
                lastYearEBITDAComparisonYY+ ";" +
                ceo + ";" +
                PrezesOdLat + ";";
        String stringWithCommasInsteadOfDots = stringwithdots.replaceAll("[.]",",");
        return stringWithCommasInsteadOfDots + date;
    }

                    @Override
                    public boolean equals(Object o) {
                        if (this == o) return true;
                        if (o == null || getClass() != o.getClass()) return false;
                        AktywoPL aktywoPL = (AktywoPL) o;
                        return code.equals(aktywoPL.code) &&
                                Objects.equals(companyName, aktywoPL.companyName);
                    }

                    @Override
                    public int hashCode() {
                        return Objects.hash(code, companyName);
                    }
                    public String getWebPage1_bizRadarNotowania() {
                        return webPage1_bizRadarNotowania;
                    }
                    public String getWebPage2_bizRadarRaporty() {
                        return webPage2_bizRadarRaporty;
                    }
                    public String getWebPage3_bankierPopularnosc() {
                        return webPage3_bankierPopularnosc;
                    }
                    public String getWebPage4_BizRadarDywidendy() {
                        return webPage4_bizRadarDywidendy;
                    }
                    public String getWebPage5_bizRadarZadluzenie() {
                        return webPage5_bizRadarZadluzenie;
                    }
                    public String getWebpage6_bizRadarRentownosc() {
                        return webpage6_bizRadarRentownosc;
                    }
                    public String getWebpage7_bankierWladzeZatrudnienie() {
                        return webpage7_bankierWladzeZatrudnienie;
                    }
                    public String getCode() {
                        return code;
                    }
                    public void setCompanyName(String companyName) {
                        this.companyName = companyName;
                    }

                    public String getSector() {
                        return sector;
                    }

                    public void setSector(String sector) {
                        this.sector = sector;
                    }

                    public double getCapitalization() {
                        return capitalization;
                    }

                    public void setCapitalization(double capitalization) {
                        this.capitalization = capitalization/1_000_000_000;
                    }

                    public double getC_z() {
                        return c_z;
                    }

                    public void setC_z(double c_z) {
                        this.c_z = c_z;
                    }

                    public double getCwk() {
                        return cwk;
                    }

                    public void setCwk(double cwk) {
                        this.cwk = cwk;
                    }

                    public double getRoe() {
                        return roe;
                    }

                    public void setRoe(double roe) {
                        this.roe = roe/100;
                    }

                    public byte getPiotroskiFscore() {
                        return piotroskiFscore;
                    }

                    public void setPiotroskiFscore(byte piotroskiFscore) {
                        this.piotroskiFscore = piotroskiFscore;
                    }

                    public String getAltmanEMScore() {
                        return altmanEMScore;
                    }

                    public void setAltmanEMScore(String altmanEMScore) {
                        this.altmanEMScore = altmanEMScore;
                    }

                    public String getCeo() {
                        return ceo;
                    }

                    public void setCeo(String ceo) {
                        this.ceo = ceo;
                    }

                    public int getLastYearRevenue() {
                        return lastYearRevenue;
                    }

                    public void setLastYearRevenue(int lastYearRevenue) {
                        this.lastYearRevenue = lastYearRevenue;
                    }

                    public double getLastYearRevenueComparisonYY() {
                        return lastYearRevenueComparisonYY;
                    }

                    public void setLastYearRevenueComparisonYY(double lastYearRevenueComparisonYY) {
                        this.lastYearRevenueComparisonYY = lastYearRevenueComparisonYY/100;
                    }

                    public int getLastYearNetProfit() {
                        return lastYearNetProfit;
                    }

                    public void setLastYearNetProfit(int lastYearNetProfit) {
                        this.lastYearNetProfit = lastYearNetProfit;
                    }

                    public double getLastYearNetProfitComparisonYY() {
                        return lastYearNetProfitComparisonYY;
                    }

                    public void setLastYearNetProfitComparisonYY(double lastYearNetProfitComparisonYY) {
                        this.lastYearNetProfitComparisonYY = lastYearNetProfitComparisonYY/100;
                    }

                    public double getLastYearEBITDAComparisonYY() {
                        return lastYearEBITDAComparisonYY;
                    }

                    public void setLastYearEBITDAComparisonYY(double lastYearEBITDAComparisonYY) {
                        this.lastYearEBITDAComparisonYY = lastYearEBITDAComparisonYY/100;
                    }

                    public int getLastYearEBITDA() {
                        return lastYearEBITDA;
                    }

                    public void setLastYearEBITDA(int lastYearEBITDA) {
                        this.lastYearEBITDA = lastYearEBITDA;
                    }

                    public int getTwoYearsBackRevenue() {
                        return twoYearsBackRevenue;
                    }

                    public void setTwoYearsBackRevenue(int twoYearsBackRevenue) {
                        this.twoYearsBackRevenue = twoYearsBackRevenue;
                    }

                    public double getTwoYearsBackRevenueComparisonYY() {
                        return twoYearsBackRevenueComparisonYY;
                    }

                    public void setTwoYearsBackRevenueComparisonYY(double twoYearsBackRevenueComparisonYY) {
                        this.twoYearsBackRevenueComparisonYY = twoYearsBackRevenueComparisonYY/100;
                    }

                    public int getTwoYearsBackNetProfit() {
                        return twoYearsBackNetProfit;
                    }

                    public void setTwoYearsBackNetProfit(int twoYearsBackNetProfit) {
                        this.twoYearsBackNetProfit = twoYearsBackNetProfit;
                    }

                    public double getTwoYearsBackNetProfitComparisonYY() {
                        return twoYearsBackNetProfitComparisonYY;
                    }

                    public void setTwoYearsBackNetProfitComparisonYY(double twoYearsBackNetProfitComparisonYY) {
                        this.twoYearsBackNetProfitComparisonYY = twoYearsBackNetProfitComparisonYY/100;
                    }

                    public int getThreeYearsBackRevenue() {
                        return threeYearsBackRevenue;
                    }

                    public void setThreeYearsBackRevenue(int threeYearsBackRevenue) {
                        this.threeYearsBackRevenue = threeYearsBackRevenue;
                    }

                    public double getThreeYearsBackRevenueComparisonYY() {
                        return threeYearsBackRevenueComparisonYY;
                    }

                    public void setThreeYearsBackRevenueComparisonYY(double threeYearsBackRevenueComparisonYY) {
                        this.threeYearsBackRevenueComparisonYY = threeYearsBackRevenueComparisonYY/100;
                    }

                    public int getThreeYearsBackNetProfit() {
                        return threeYearsBackNetProfit;
                    }

                    public void setThreeYearsBackNetProfit(int threeYearsBackNetProfit) {
                        this.threeYearsBackNetProfit = threeYearsBackNetProfit;
                    }

                    public double getThreeYearsBackNetProfitComparisonYY() {
                        return threeYearsBackNetProfitComparisonYY;
                    }

                    public void setThreeYearsBackNetProfitComparisonYY(double threeYearsBackNetProfitComparisonYY) {
                        this.threeYearsBackNetProfitComparisonYY = threeYearsBackNetProfitComparisonYY/100;
                    }

                    public String getFinancialLiquidity() {
                        return financialLiquidity;
                    }

                    public void setFinancialLiquidity(String financialLiquidity) {
                        this.financialLiquidity = financialLiquidity;
                    }

                    public double getDividends() {
                        return dividends;
                    }

                    public void setDividends(double dividends) {
                        this.dividends = dividends;
                    }

                    public double getDY() {
                        return DY;
                    }

                    public void setDY(double DY) {
                        this.DY = DY/100;
                    }

                    public int getDividendsYearsOfGrowth() {
                        return dividendsYearsOfGrowth;
                    }

                    public void setDividendsYearsOfGrowth(int dividendsYearsOfGrowth) {
                        this.dividendsYearsOfGrowth = dividendsYearsOfGrowth;
                    }

                    public int getDividendsRecordYears() {
                        return dividendsRecordYears;
                    }

                    public void setDividendsRecordYears(int dividendsRecordYears) {
                        this.dividendsRecordYears = dividendsRecordYears;
                    }

                    public byte getDividendsYearsWithoutPayout() {
                        return dividendsYearsWithoutPayout;
                    }

                    public void setDividendsYearsWithoutPayout(byte dividendsYearsWithoutPayout) {
                        this.dividendsYearsWithoutPayout = dividendsYearsWithoutPayout;
                    }

                    public double getForeignCapital() {
                        return foreignCapital;
                    }

                    public void setForeignCapital(double foreignCapital) {
                        this.foreignCapital = foreignCapital;
                    }

                    public long getNetDebt() {
                        return netDebt;
                    }

                    public void setNetDebt(long netDebt) {
                        this.netDebt = netDebt;
                    }

                    public double getRoa() {
                        return roa;
                    }

                    public void setRoa(double roa) {
                        this.roa = roa;
                    }

                    public double getNetProfitMargin() {
                        return netProfitMargin;
                    }

                    public void setNetProfitMargin(double netProfitMargin) {
                        this.netProfitMargin = netProfitMargin;
                    }

                    public double getProfitMarginOnSales() {
                        return profitMarginOnSales;
                    }

                    public void setProfitMarginOnSales(double profitMarginOnSales) {
                        this.profitMarginOnSales = profitMarginOnSales;
                    }

                    public double getRoic() {
                        return roic;
                    }

                    public void setRoic(double roic) {
                        this.roic = roic;
                    }


    public byte getPrezesOdLat() {
        return PrezesOdLat;
    }

    public void setPrezesOdLat(short PrezesOdRoku) {
        short currentYear = (short) Year.now().getValue();
        short years = (short) (currentYear - PrezesOdRoku);
        this.PrezesOdLat = (byte) (years);
    }
}
