package bfiDataModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AktywoUSA implements Serializable {

    // seeking alpha symbols
    private String company;
    private String ticker;
    private String sector;
    private String id;

    // seeking alpha real time quotes
    private long marketCap;     //zaokr do mld $

    // seeking alpha
    private double dy;
    private double dividend;
    private byte yearsOfGrowth;
    private byte recordYears;
    private byte yearsWithZero;    //years where dividend was not paid
    private double fiveYearsGrowthRate;
    private double payoutRatio;
    private double pe;
    private double pb;
    private double grossProfitMargin;

    //gurufocus
    private double roe;
    private double roa;
    private double roic;
    private byte piotroskiFscore;
    private double altmanZScore;
    private double netMargin;

    //seekingalpha fundamentals_metrics?period_type=annual&statement_type=income-statement
    private long revenues;
    private long revenues_2y;
    private long revenues_3y;
    private long netIncome;
    private long netIncome_2y;
    private long netIncome_3y;
    private long revenues_y2y;
    private long revenues_y2y_2y;
    private long revenues_y2y_3y;
    private long netIncome_y2y;
    private long netIncome_y2y_2y;
    private long netIncome_y2y_3y;
    private int ebidta;
    private double ebidta_y2y;

    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString();


    //gettery i settery
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDividend() {
        return dividend;
    }

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public byte getYearsOfGrowth() {
        return yearsOfGrowth;
    }

    public void setYearsOfGrowth(byte yearsOfGrowth) {
        this.yearsOfGrowth = yearsOfGrowth;
    }

    public byte getRecordYears() {
        return recordYears;
    }

    public void setRecordYears(byte recordYears) {
        this.recordYears = recordYears;
    }

    public byte getYearsWithZero() {
        return yearsWithZero;
    }

    public void setYearsWithZero(byte yearsWithZero) {
        this.yearsWithZero = yearsWithZero;
    }

    public double getFiveYearsGrowthRate() {
        return fiveYearsGrowthRate;
    }

    public void setFiveYearsGrowthRate(double fiveYearsGrowthRate) {
        this.fiveYearsGrowthRate = fiveYearsGrowthRate;
    }

    public double getPayoutRatio() {
        return payoutRatio;
    }

    public void setPayoutRatio(double payoutRatio) {
        this.payoutRatio = payoutRatio;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    public double getGrossProfitMargin() {
        return grossProfitMargin;
    }

    public void setGrossProfitMargin(double grossProfitMargin) {
        this.grossProfitMargin = grossProfitMargin;
    }

    public double getRoe() {
        return roe;
    }

    public void setRoe(double roe) {
        this.roe = roe;
    }

    public double getRoa() {
        return roa;
    }

    public void setRoa(double roa) {
        this.roa = roa;
    }

    public double getRoic() {
        return roic;
    }

    public void setRoic(double roic) {
        this.roic = roic;
    }

    public byte getPiotroskiFscore() {
        return piotroskiFscore;
    }

    public void setPiotroskiFscore(byte piotroskiFscore) {
        this.piotroskiFscore = piotroskiFscore;
    }

    public double getAltmanZScore() {
        return altmanZScore;
    }

    public void setAltmanZScore(double altmanZScore) {
        this.altmanZScore = altmanZScore;
    }

    public double getNetMargin() {
        return netMargin;
    }

    public void setNetMargin(double netMargin) {
        this.netMargin = netMargin;
    }

    public long getRevenues() {
        return revenues;
    }

    public void setRevenues(long revenues) {
        this.revenues = revenues;
    }

    public long getRevenues_2y() {
        return revenues_2y;
    }

    public void setRevenues_2y(long revenues_2y) {
        this.revenues_2y = revenues_2y;
    }

    public long getRevenues_3y() {
        return revenues_3y;
    }

    public void setRevenues_3y(long revenues_3y) {
        this.revenues_3y = revenues_3y;
    }

    public long getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(long netIncome) {
        this.netIncome = netIncome;
    }

    public long getNetIncome_2y() {
        return netIncome_2y;
    }

    public void setNetIncome_2y(long netIncome_2y) {
        this.netIncome_2y = netIncome_2y;
    }

    public long getNetIncome_3y() {
        return netIncome_3y;
    }

    public void setNetIncome_3y(long netIncome_3y) {
        this.netIncome_3y = netIncome_3y;
    }

    public long getRevenues_y2y() {
        return revenues_y2y;
    }

    public void setRevenues_y2y(long revenues_y2y) {
        this.revenues_y2y = revenues_y2y;
    }

    public long getRevenues_y2y_2y() {
        return revenues_y2y_2y;
    }

    public void setRevenues_y2y_2y(long revenues_y2y_2y) {
        this.revenues_y2y_2y = revenues_y2y_2y;
    }

    public long getRevenues_y2y_3y() {
        return revenues_y2y_3y;
    }

    public void setRevenues_y2y_3y(long revenues_y2y_3y) {
        this.revenues_y2y_3y = revenues_y2y_3y;
    }

    public long getNetIncome_y2y() {
        return netIncome_y2y;
    }

    public void setNetIncome_y2y(long netIncome_y2y) {
        this.netIncome_y2y = netIncome_y2y;
    }

    public long getNetIncome_y2y_2y() {
        return netIncome_y2y_2y;
    }

    public void setNetIncome_y2y_2y(long netIncome_y2y_2y) {
        this.netIncome_y2y_2y = netIncome_y2y_2y;
    }

    public long getNetIncome_y2y_3y() {
        return netIncome_y2y_3y;
    }

    public void setNetIncome_y2y_3y(long netIncome_y2y_3y) {
        this.netIncome_y2y_3y = netIncome_y2y_3y;
    }

    public int getEbidta() {
        return ebidta;
    }

    public void setEbidta(int ebidta) {
        this.ebidta = ebidta;
    }

    public double getEbidta_y2y() {
        return ebidta_y2y;
    }

    public void setEbidta_y2y(double ebidta_y2y) {
        this.ebidta_y2y = ebidta_y2y;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
