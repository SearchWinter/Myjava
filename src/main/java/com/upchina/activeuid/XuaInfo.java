package com.upchina.activeuid;



import java.io.Serializable;

/**
 * Created by 99162 on 2016/10/8.
 */
public class XuaInfo implements Serializable
{
    public String sXua;
    public String sModule;
    public String sPlat;
    public String sChannel;
    public String sVersion;
    public String sVendor;
    public String sMachine;
    public String sOs;
    public String sPkg;

    public boolean parseXua()
    {
        String[] vtTmp = sXua.split("&");

        for(int i = 0 ; i < vtTmp.length; i++)
        {
            String[] vtItem = vtTmp[i].split("=");

            if(vtItem.length <= 1)
            {
                System.out.println((vtTmp[i]));
                continue;
            }

            String sKey    = vtItem[0];

            if(sKey.equals("SDK"))
            {
                continue;
            }

            String sValue  = vtItem[1];

            if(sKey.equals("MN"))
            {//模块
                sModule = sValue;
            }
            else if(sKey.equals("VC"))
            {//制造厂商
                sVendor = sValue;
            }
            else if(sKey.equals("MO"))
            {//型号
                sMachine = sValue;
            }
            else if(sKey.equals("CHID"))
            {//渠道号
                String[] vtChannel = sValue.split("_");

                if (vtChannel.length == 1)
                {
                    sChannel = vtChannel[0];
                }
                else if(vtChannel.length == 2)
                {
                    if(vtChannel[0].length() > 0)
                    {
                        sChannel = vtChannel[0];
                    }
                    else if(vtChannel[1].length() > 0)
                    {
                        sChannel = vtChannel[1];
                    }else{
                    	 sChannel = null;
                    }
                }

                if(sChannel == null)
                {
                    continue;
                }

                if(sChannel.length() > 0)
                {
                    String sTmp = "";

                    for(int j = 0 ; j < sChannel.length(); j++)
                    {
                        //弥补pc的channel的 4,040的坑
                        if(sChannel.charAt(j) == ',')
                        {
                            continue;
                        }

                        sTmp += sChannel.charAt(j);
                    }

                    sChannel = sTmp;
                }
            }
            else if(sKey.equals("SN"))
            {
                //平台
                String[] vtPlats = sValue.split("_");

                if(vtPlats.length >= 2)
                {
                    sPlat = vtPlats[0];
                    sPkg  = vtPlats[1];
                }
                else
                {
                    System.out.println(("error user xua data " + sXua));
                }
            }
            else if(sKey.equals("VN"))
            {
                //版本
                String[] vtVersions = sValue.split("_");

                if(vtVersions.length == 1)
                {
                    sVersion = vtVersions[0];
                }
                else if(vtVersions.length > 1)
                {
                    if(sXua.indexOf("WIN") > -1)
                    {//pc
                        sVersion = vtVersions[0];
                    }
                    else
                    {//app
                        sVersion = vtVersions[1];
                    }
                }
            }
            else if(sKey.equals("OS"))
            {
                sOs = sValue;
            }
        }

        return true;
    }

    public XuaInfo(String sXua)
    {
        this.sXua = sXua;
    }
}
