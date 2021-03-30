package sun.study.note.base;


import lombok.*;

import java.io.Serializable;

/**
 * BaseResult:
 *
 * @author: sunzhen
 * Date: 2021-03-26
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult implements Serializable {

    private static final int OK = 1;
    private static final int FAIL = 0;
    private static final int DEFAULT_OK_CODE = 200;
    private static final int DEFAULT_ERROR_CODE = 500;
    // 返回码
    private int code;
    // 请求状态：0-失败，1-成功
    private int status;
    // 返回信息
    private String msg;
    // 返回数据
    private Object data;


    public static BaseResult success() {
        return new BaseResult(DEFAULT_OK_CODE, OK, null, null);
    }

    public static BaseResult success(Object data) {
        return success(DEFAULT_OK_CODE, data);
    }

    public static BaseResult success(Object data, String msg) {
        return success(DEFAULT_OK_CODE, msg, data);
    }

    public static BaseResult success(int code, Object data) {
        return success(code, "", data);
    }

    public static BaseResult successMsg(String msg) {
        return successMsg(DEFAULT_OK_CODE, msg);
    }

    public static BaseResult successMsg(int code, String msg) {
        return success(code, msg, null);
    }

    public static BaseResult success(int code, String msg, Object data) {
        return new BaseResult(code, OK, msg, data);
    }

    public static BaseResult error(String msg) {
        return new BaseResult(DEFAULT_ERROR_CODE, FAIL, msg, null);
    }

    public static BaseResult error(int code, String msg) {
        return new BaseResult(code, FAIL, msg, null);
    }

}
