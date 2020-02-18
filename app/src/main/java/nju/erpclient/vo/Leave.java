package nju.erpclient.vo;

//请假
public class Leave {

    private String id;              //id
    private String subsidiary;      //子公司
    private String relevantPeople;  //相关人
    private String type;            //请假类型
    private String startTime;       //起始时间
    private String endTime;         //结束时间
    private String ratify;          //批准人
    private String remark;          //备注

    public Leave() {
    }

    public Leave(String id, String subsidiary, String relevantPeople, String type,
                 String startTime, String endTime, String ratify, String remark) {
        this.id = id;
        this.subsidiary = subsidiary;
        this.relevantPeople = relevantPeople;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ratify = ratify;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRatify() {
        return ratify;
    }

    public void setRatify(String ratify) {
        this.ratify = ratify;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id='" + id + '\'' +
                ", subsidiary='" + subsidiary + '\'' +
                ", relevantPeople='" + relevantPeople + '\'' +
                ", type='" + type + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", ratify='" + ratify + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
