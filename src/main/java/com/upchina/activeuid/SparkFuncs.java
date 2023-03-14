package com.upchina.activeuid;

import org.apache.spark.api.java.function.Function;

import java.io.Serializable;

/**
 * Created by anjunli on  2021/1/26
 **/

/**
 * Function<LoginRecord,Boolean>
 * 传入LoginRecord  返回一个Boolean值
 * */
class FilterLoginLogFunction implements Function<LoginRecord, Boolean>, Serializable {

    private String Channel;
    FilterLoginLogFunction(String Channel) {
        this.Channel=Channel;
    }

    @Override
    public Boolean call(LoginRecord stLog) throws Exception {
        if (stLog.sUid == null || stLog.sXua == null || stLog.sType == null || stLog.sType == null || stLog.sChannel == null || stLog.sVersion == null || stLog.sPlat == null || stLog.sApp == null) {
            return Boolean.valueOf(false);
        }
        if (stLog.sApp.equals("hummerpro_guniuniu")&&stLog.sChannel.equals(Channel)) {
            return Boolean.valueOf(true);
        } else {
            return Boolean.valueOf(false);
        }
    }
}

class ParseLoginLogFunction implements Function<String,LoginRecord>, Serializable {

    ParseLoginLogFunction() {
    }

    @Override
    public LoginRecord call(String s) throws Exception {
        LoginRecord stLog = new LoginRecord(s);
        return stLog;
    }

}
