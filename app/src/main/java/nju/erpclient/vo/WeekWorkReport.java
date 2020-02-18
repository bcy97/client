package nju.erpclient.vo;

//每周工作汇报
public class WeekWorkReport {

    private String subsidiary;
    private String relevantPeople;
    private String principal;
    private String year;
    private int weeklyNum;
    private String mondayWorkContent;
    private String tuesdayWorkContent;
    private String wednesdayWorkContent;
    private String thursdayWorkContent;
    private String fridayWorkContent;
    private String saturdayWorkContent;
    private String sundayWorkContent;

    public WeekWorkReport() {
    }

    public WeekWorkReport(String subsidiary, String relevantPeople, String principal, String year, int weeklyNum, String mondayWorkContent,
                          String tuesdayWorkContent, String wednesdayWorkContent, String thursdayWorkContent, String fridayWorkContent,
                          String saturdayWorkContent, String sundayWorkContent) {
        this.subsidiary = subsidiary;
        this.relevantPeople = relevantPeople;
        this.principal = principal;
        this.year = year;
        this.weeklyNum = weeklyNum;
        this.mondayWorkContent = mondayWorkContent;
        this.tuesdayWorkContent = tuesdayWorkContent;
        this.wednesdayWorkContent = wednesdayWorkContent;
        this.thursdayWorkContent = thursdayWorkContent;
        this.fridayWorkContent = fridayWorkContent;
        this.saturdayWorkContent = saturdayWorkContent;
        this.sundayWorkContent = sundayWorkContent;
    }

    public String getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(String subsidiary) {
        this.subsidiary = subsidiary;
    }

    public String getRelevantPeople() {
        return relevantPeople;
    }

    public void setRelevantPeople(String relevantPeople) {
        this.relevantPeople = relevantPeople;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getWeeklyNum() {
        return weeklyNum;
    }

    public void setWeeklyNum(int weeklyNum) {
        this.weeklyNum = weeklyNum;
    }

    public String getMondayWorkContent() {
        return mondayWorkContent;
    }

    public void setMondayWorkContent(String mondayWorkContent) {
        this.mondayWorkContent = mondayWorkContent;
    }

    public String getTuesdayWorkContent() {
        return tuesdayWorkContent;
    }

    public void setTuesdayWorkContent(String tuesdayWorkContent) {
        this.tuesdayWorkContent = tuesdayWorkContent;
    }

    public String getWednesdayWorkContent() {
        return wednesdayWorkContent;
    }

    public void setWednesdayWorkContent(String wednesdayWorkContent) {
        this.wednesdayWorkContent = wednesdayWorkContent;
    }

    public String getThursdayWorkContent() {
        return thursdayWorkContent;
    }

    public void setThursdayWorkContent(String thursdayWorkContent) {
        this.thursdayWorkContent = thursdayWorkContent;
    }

    public String getFridayWorkContent() {
        return fridayWorkContent;
    }

    public void setFridayWorkContent(String fridayWorkContent) {
        this.fridayWorkContent = fridayWorkContent;
    }

    public String getSaturdayWorkContent() {
        return saturdayWorkContent;
    }

    public void setSaturdayWorkContent(String saturdayWorkContent) {
        this.saturdayWorkContent = saturdayWorkContent;
    }

    public String getSundayWorkContent() {
        return sundayWorkContent;
    }

    public void setSundayWorkContent(String sundayWorkContent) {
        this.sundayWorkContent = sundayWorkContent;
    }

    @Override
    public String toString() {
        return "WeekWorkReport{" +
                "subsidiary='" + subsidiary + '\'' +
                ", relevantPeople='" + relevantPeople + '\'' +
                ", principal='" + principal + '\'' +
                ", year='" + year + '\'' +
                ", weeklyNum=" + weeklyNum +
                ", mondayWorkContent='" + mondayWorkContent + '\'' +
                ", tuesdayWorkContent='" + tuesdayWorkContent + '\'' +
                ", wednesdayWorkContent='" + wednesdayWorkContent + '\'' +
                ", thursdayWorkContent='" + thursdayWorkContent + '\'' +
                ", fridayWorkContent='" + fridayWorkContent + '\'' +
                ", saturdayWorkContent='" + saturdayWorkContent + '\'' +
                ", sundayWorkContent='" + sundayWorkContent + '\'' +
                '}';
    }
}
