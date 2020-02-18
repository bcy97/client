package nju.erpclient.vo;

/**
 * @Author Arthur
 * @Date 2019/10/23 16:01
 */
public class UserInfo {

    private String id;
    private String userName;
    private String password;
    private String subsidiary;
    private String role;
    private String level;
    private String remark;

    public UserInfo() {
    }

    public UserInfo(String id, String userName, String password,
                    String subsidiary, String role, String level, String remark) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.subsidiary = subsidiary;
        this.role = role;
        this.level = level;
        this.remark = remark;
    }

    public UserInfo(String userName, String password, String subsidiary,
                    String role, String level, String remark) {
        this.userName = userName;
        this.password = password;
        this.subsidiary = subsidiary;
        this.role = role;
        this.level = level;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(String subsidiary) {
        this.subsidiary = subsidiary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", subsidiary='" + subsidiary + '\'' +
                ", role='" + role + '\'' +
                ", level='" + level + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
