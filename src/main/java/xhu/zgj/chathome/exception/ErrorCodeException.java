package xhu.zgj.chathome.exception;

import xhu.zgj.chathome.enums.inter.Code;

/**
 * 返回错误码
 */
public class ErrorCodeException extends Exception {

    private Code code;

    public ErrorCodeException(Code code) {
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
