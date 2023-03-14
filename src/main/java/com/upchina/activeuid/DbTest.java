// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DbTest.java

package com.upchina.activeuid;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbTest {

    public DbTest() {
    }

    @Test
    public void testDB() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_s_yanwenting?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8", "userName", "passwd");
        String tableName="demo";
//        DbUtils.crateTable(connection,tableName);
        System.out.println(DbUtils.isTableExist(tableName,connection));
        DbUtils.deleteTable(tableName,connection);
        connection.close();
    }
}
