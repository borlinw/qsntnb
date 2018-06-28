package com.hdsx.jhsjgx.api.services.impl;

import com.google.inject.Inject;
import com.hdsx.jhsjgx.api.mapper.DataReader;

import org.sqlite.SQLiteConfig;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author jkYishon
 */
public class Export2sql {
    // 将需要导入数据的Sqlite数据库复制到DataMigrationPlugin项目的一级目录下
    public static final String DB_FILE_NAME = "jxjhxcPro.sqlite";

    @Inject
    private DataReader reader;

    private Connection conn = null;

    public boolean exportDataFromOracle(String url2,String unit)  {
        boolean succeed = false;
        try {
            System.out.println("----------------开始执行数据导出----------------\n");
            for (int flag = 1; flag < 43; flag++) {
                doingSql(url2,flag,unit);
            }
            System.out.println("----------------已经结束数据导出----------------\n");
            succeed = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            succeed =false;
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    succeed =false;
                }
        }

        return succeed;
    }

    public void doingSql(String url2,int flag,String unit) throws Exception {
        String tableName = null;
        List<HashMap> dataList = new ArrayList<HashMap>();

        // 标识sql开始执行
       // System.out.println("begin insert:" + flag + "\n");

        switch (flag) {
            case 1:
                tableName = "CBSJ_LMGZ";
                dataList = reader.selectCBSJ_LMGZ(unit);
                break;
            case 2:
                tableName = "CBSJ_SH";
                dataList = reader.selectCBSJ_SH(unit);
                break;
            case 3:
                tableName = "CBSJ_SJGZ";
                dataList = reader.selectCBSJ_SJGZ(unit);
                break;
            case 4:
                tableName = "CBSJ_XJ";
                dataList = reader.selectCBSJ_XJ(unit);
                break;
            case 5:
                tableName = "CBSJ_YHDZX";
                dataList = reader.selectCBSJ_YHDZX(unit);
                break;
            case 6:
                tableName = "GCGL_ABGC";
                dataList = reader.selectGCGL_ABGC(unit);
                break;
            case 7:
                tableName = "GCGL_JGYS";
                dataList = reader.selectGCGL_JGYS(unit);
                break;
            case 8:
                tableName = "GCGL_WQGZ";
                dataList = reader.selectGCGL_WQGZ(unit);
                break;
            case 9:
                tableName = "GCGL_XMJD";
                dataList = reader.selectGCGL_XMJD(unit);
                break;
            case 10:
                tableName = "GCGL_ZHFZ";
                dataList = reader.selectGCGL_ZHFZ(unit);
                break;
            case 11:
                tableName = "JHSH_LMGZ";
                dataList = reader.selectJHSH_LMGZ(unit);
                break;
            case 12:
                tableName = "JHSH_SH";
                dataList = reader.selectJHSH_SH(unit);
                break;
            case 13:
                tableName = "JHSH_SJGZ";
                dataList = reader.selectJHSH_SJGZ(unit);
                break;
            case 14:
                tableName = "JHSH_XJ";
                dataList = reader.selectJHSH_XJ(unit);
                break;
            case 15:
                tableName = "JHSH_YHDZX";
                dataList = reader.selectJHSH_YHDZX(unit);
                break;
            case 16:
                tableName = "KXXYJ_LMGZ";
                dataList = reader.selectKXXYJ_LMGZ(unit);
                break;
            case 17:
                tableName = "KXXYJ_SJGZ";
                dataList = reader.selectKXXYJ_SJGZ(unit);
                break;
            case 18:
                tableName = "KXXYJ_XJ";
                dataList = reader.selectKXXYJ_XJ(unit);
                break;
            case 19:
                tableName = "LKPD_LKHZB";
                dataList = reader.selectLKPD_LKHZB(unit);
                break;
            case 20:
                tableName = "LKPD_LKHZB_DATA";
                dataList = reader.selectLKPD_LKHZB_DATA(unit);
                break;
            case 21:
                tableName = "LKPD_MXB_DATA_2014";
                dataList = reader.selectLKPD_MXB_DATA_2014(unit);
                break;
            case 22:
                tableName = "LKPD_MXB2014";
                dataList = reader.selectLKPD_MXB2014(unit);
                break;
            case 23:
                tableName = "LXSH_LMGZ";
                dataList = reader.selectLXSH_LMGZ(unit);
                break;
            case 24:
                tableName = "LXSH_LX";
                dataList = reader.selectLXSH_LX(unit);
                break;
            case 25:
                tableName = "LXSH_SJGZ";
                dataList = reader.selectLXSH_SJGZ(unit);
                break;
            case 26:
                tableName = "LXSH_XJ";
                dataList = reader.selectLXSH_XJ(unit);
                break;
            case 27:
                tableName = "PLAN_AF";
                dataList = reader.selectPLAN_AF(unit);
                break;
            case 28:
                tableName = "PLAN_WQGZ";
                dataList = reader.selectPLAN_WQGZ(unit);
                break;
            case 29:
                tableName = "PLAN_ZHFZ";
                dataList = reader.selectPLAN_ZHFZ(unit);
                break;
            case 30:
                tableName = "PLAN_ZJXD";
                dataList = reader.selectPLAN_ZJXD(unit);
                break;
            case 31:
                tableName = "SCK_AQSMFH";
                dataList = reader.selectSCK_AQSMFH(unit);
                break;
            case 32:
                tableName = "SCK_AQSMFH_LD";
                dataList = reader.selectSCK_AQSMFH_LD(unit);
                break;
            case 33:
                tableName = "SCK_WQGZ";
                dataList = reader.selectSCK_WQGZ(unit);
                break;
            case 35:
                tableName = "SCK_ZHFZ";
                dataList = reader.selectSCK_ZHFZ(unit);
                break;
            case 36:
                tableName = "SJGX_TARGETS";
                dataList = reader.selectSJGX_TARGETS(unit);
                break;
            case 37:
                tableName = "XMK_AQSMFH";
                dataList = reader.selectXMK_AQSMFH(unit);
                break;
            case 38:
                tableName = "XMK_WQGZ";
                dataList = reader.selectXMK_WQGZ(unit);
                break;
            case 39:
                tableName = "XMK_ZHFZ";
                dataList = reader.selectXMK_ZHFZ(unit);
                break;
            case 40:
                tableName = "XMSQ_SH";
                dataList = reader.selectXMSQ_SH(unit);
                break;
            case 41:
                tableName = "XMSQ_YHDZX";
                dataList = reader.selectXMSQ_YHDZX(unit);
                break;
            case 42:
                tableName = "ZGB_ZXLWZHTX_LXSH";
                dataList = reader.selectZGB_ZXLWZHTX_LXSH(unit);
                break;
        }

        getSql(url2,dataList, tableName);
        // 标识sql执行完成
        System.out.println("insert finished flag = " + flag + " tableName = " + tableName + "  total count:" + dataList.size() + "\n");
    }

    public void getSql(String url2,List<HashMap> dataList, String tablname) throws Exception{
        StringBuffer sqlStr = new StringBuffer();
        for (int i = 0; i < dataList.size(); i++) {
            sqlStr.append("insert into " + tablname + " (");
            // 拼接key
            Iterator iter = dataList.get(i).entrySet().iterator();
            while (iter.hasNext()) {
                Entry entry = (Entry) iter.next();
                sqlStr.append(entry.getKey()).append(",");
            }

            int length = sqlStr.length();
            sqlStr.replace(length - 1, length, "");
            sqlStr.append(") values (");

            Iterator iter1 = dataList.get(i).entrySet().iterator();
            while (iter1.hasNext()) {
                Entry entry = (Entry) iter1.next();
                Object object = entry.getValue();
                if (object instanceof String) {
                    String data = (String) entry.getValue();
                    if (data != null && data.contains("\"")) {
                        data = data.replace("\"", "");
                        sqlStr.append("\"").append(data).append("\"").append(",");
                    } else {
                        sqlStr.append("\"").append(object).append("\"").append(",");
                    }
                } else {
                    sqlStr.append("\"").append(object).append("\"").append(",");
                }
            }

            length = sqlStr.length();
            sqlStr.replace(length - 1, length, "");

            sqlStr.append(");\n");
        }

        //System.out.print(sqlStr.toString());
        executeSql(url2,sqlStr.toString());
    }

    /**
     * 写入到文件
     */
    public void export2File(String pathName, String sqlStr) {
        byte[] buff = new byte[]{};
        try {
            buff = sqlStr.getBytes();
            FileOutputStream out = new FileOutputStream(pathName);
            out.write(buff, 0, buff.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeSql(String url2,String sqlStr) throws Exception {
        Class.forName("org.sqlite.JDBC");
        // enabling dynamic extension loading  
        // absolutely required by SpatiaLite
        SQLiteConfig config = new SQLiteConfig();
        config.enableLoadExtension(true);

        // create a database connection
       // String url = "jdbc:sqlite:" + DB_FILE_NAME;
        String url = "jdbc:sqlite:"+url2;
        if (conn == null) {
            conn = DriverManager.getConnection(url, config.toProperties());
        }
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(30); // set timeout to 30 sec.
        conn.setAutoCommit(false);
        stmt.executeUpdate(sqlStr);
        conn.commit();
    }
}
