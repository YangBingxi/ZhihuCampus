package edu.njupt.sw.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MysqlLink {
    private static final String Driver;
    private static final String url;
    private static final String username;
    private static final String password;

    static {
        // 加载属性文件并解析：
        Properties props = new Properties();
        // 如何获得属性文件的输入流？
        // 通常情况下使用类的加载器的方式进行获取：
        InputStream is = MysqlLink.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Driver = props.getProperty("Driver");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
    }

    /**
     * 无参构造函数
     */
    public MysqlLink() {

    }

    /**
     * 注册驱动的方法
     *
     * @throws ClassNotFoundException
     * @author swyoung
     */
    public static void loadDriver() throws ClassNotFoundException {
        Class.forName(Driver);
        System.out.println("JDBC Driver is success !");
    }

    /**
     * 获取链接的方法
     *
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        loadDriver();
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    /**
     * 释放函数
     * @param stmt
     * @param conn
     */
    public static void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    /**
     * 释放函数
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        release(stmt, conn);

    }

}
