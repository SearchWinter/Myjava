package com.upchina.activeuid;

import java.io.Serializable;

/**
 * Created by 99162 on 2016/11/13.
 */
public class LoginRecord implements Serializable
{
    public String sUid;
    public String sXua;
    public String sType;
    public String sPlat; //系统平台
    public String sApp; //app的模块
    public String sVersion;//app版本
    public String sChannel;//渠道号
    public String sOS;//操作系统版本号
    public String sTime;

    public String getsUid() {
        return sUid;
    }

    public void setsUid(String sUid) {
        this.sUid = sUid;
    }

    public String getsXua() {
        return sXua;
    }

    public void setsXua(String sXua) {
        this.sXua = sXua;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getsPlat() {
        return sPlat;
    }

    public void setsPlat(String sPlat) {
        this.sPlat = sPlat;
    }

    public String getsApp() {
        return sApp;
    }

    public void setsApp(String sApp) {
        this.sApp = sApp;
    }

    public String getsVersion() {
        return sVersion;
    }

    public void setsVersion(String sVersion) {
        this.sVersion = sVersion;
    }

    public String getsChannel() {
        return sChannel;
    }

    public void setsChannel(String sChannel) {
        this.sChannel = sChannel;
    }

    public String getsOS() {
        return sOS;
    }

    public void setsOS(String sOS) {
        this.sOS = sOS;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public LoginRecord(String s)
    {
        String[] lines = s.split("\\|");
        if(lines.length < 7)
        {
            return ;
        }
        this.sTime= lines[1];
        this.sXua  = lines[2];
        this.sUid  = lines[3];
        this.sType = lines[5];

        XuaInfo info = new XuaInfo(sXua);
        info.parseXua();

        if(info.sPlat != null && info.sPlat.equals("IOS") && (info.sChannel == null || info.sChannel.length() == 0))
        {
            //IOS 不填渠道号,自动补全
            info.sChannel = "9001";
        }

        this.sApp = info.sModule;
        this.sPlat = info.sPlat;
        this.sChannel = info.sChannel;
        this.sVersion = info.sVersion;
        this.sOS=info.sOs;
    }
}
