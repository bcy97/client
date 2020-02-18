package nju.erpclient.vo;

/**
 * API统一返回状态码
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 失败状态码 */
    FAILURE(2, "失败"),

    /* CRUD状态码: 100-999 */
    SUCCESS_UPDATE(101, "更新成功"),
    SUCCESS_ADD(102, "添加成功"),
    SUCCESS_DELETE(103, "删除成功"),
    SUCCESS_QUERY(104, "查询成功"),

    FAILURE_UPDATE(105, "更新失败"),
    FAILURE_ADD(106, "添加失败"),
    FAILURE_DELETE(107, "删除失败"),
    FAILURE_QUERY(108, "查询失败"),

    /* 参数错误:10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误:20001-29999 */
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBINDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),

    /* 业务错误:30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出线问题"),

    /* 系统错误:40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙,请稍后重试"),

    /* 数据错误:50001-59999 */
    RESULT_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误:60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),

    /* 权限错误:70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(int code) {
        for (ResultCode res : ResultCode.values()) {
            if (res.code == code) {
                return res.message;
            }
        }
        return "失败";
    }


    @Override
    public String toString() {
        return this.name();
    }

    public static void main(String[] args) {
    }

}
