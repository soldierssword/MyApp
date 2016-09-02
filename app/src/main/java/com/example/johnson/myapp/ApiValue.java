package com.example.johnson.myapp;

/**
 * Created by Johnson on 2016/8/29.
 */
public class ApiValue {

    private Integer errNum;
    private String errMsg;
    private RetData retData;

    public ApiValue() {
    }

    public ApiValue(String errMsg, Integer errNum, RetData retdata) {
        this.errMsg = errMsg;
        this.errNum = errNum;
        this.retData = retdata;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrNum() {
        return errNum;
    }

    public void setErrNum(Integer errNum) {
        this.errNum = errNum;
    }

    public RetData getRetdata() {
        return retData;
    }

    public void setRetdata(RetData retdata) {
        this.retData = retdata;
    }

    @Override
    public String toString() {
        return  "Result[errNum="+this.errNum+",errMsg="+this.errMsg+",retData="+this.retData+"]";
    }
}
