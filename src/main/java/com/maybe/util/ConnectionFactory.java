package com.maybe.util;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @Description:数据库连接池
 * @author:Bodyle
 */
public class ConnectionFactory {
    protected static Logger logger = Logger.getLogger(ConnectionFactory.class);

    private static final String DBDRIVER = "com.mysql.jdbc.Driver"; // 驱动串

    /* 0.228 - Linux */
//    private static final String DBURL = "jdbc:mysql://192.168.0.228:3306/orcl?useUnicode=true&characterEncoding=GBK";//
//    private static final String DBUSER = "bmacsj"; // 用户名
//    private static final String DBPASSWORD = "sa"; // 数据库密码

    //    /* 0.253 */
    private static final String DBURL = "jdbc:mysql://192.168.0.253:3306/quick4j";//
    //private static final String DBURL = "jdbc:mysql://heigoserver-BDA:3306/orcl?useUnicode=true&characterEncoding=GBK";//
    private static final String DBUSER = "bmacsj"; // 用户名
    private static final String DBPASSWORD = "sa"; // 数据库密码

	/* 本机 */
    // private static final String DBURL =
    // "jdbc:mysql://127.0.0.1:3306/orcl?useUnicode=true&characterEncoding=GBK";//
    // private static final String DBUSER = "root"; // 用户名
    // private static final String DBPASSWORD = "root"; // 数据库密码

	/* 阿里云 */
//	 private static final String DBURL = "jdbc:mysql://rdsz84r41i79b9ya6648.mysql.rds.aliyuncs.com:3306/orcl?useUnicode=true&characterEncoding=GBK";//
//	 private static final String DBUSER = "bmacsj"; // 用户名
//	 private static final String DBPASSWORD = "123456"; // 数据库密码

    /**
     * 打开MySql数据库连接
     *
     * @return [conn] 连接对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() {
        Connection conn = null; // 声明一个连接对象
        try {
            Class.forName(DBDRIVER); // 注册驱动
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        } catch (Exception e) {
            logger.error("ERRNO: 0x000009");
            /* 打开数据库连接失败 */
        }
        return conn;
    }

    /**
     * 关闭数据库连接 释放资源
     *
     * @params [rset] 结果集
     * @params [pstmt] 语句
     * @params [conn] 连接对象
     */
    public static void closeResources(ResultSet rset, PreparedStatement pstmt, Connection conn) {
        try {
            if (rset != null) {
                rset.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("ERRNO: 0x000008");
            /* 关闭数据库连接异常 */
        }
    }

    /**
     * 关闭数据库连接 释放资源
     *
     * @params [pstmt] 语句
     * @params [conn] 连接对象
     */
    public static void closeResources(PreparedStatement pstmt, Connection conn) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("ERRNO: 0x000007");
            /* 关闭数据库连接异常 */
        }
    }

    /* 连接oracle */
    private static Connection conn = null;// 创建一个数据库连接

    /**
     * 打开Oracle数据库连接
     *
     * @return [conn] 连接对象
     */
    public static Connection getConnectionOracle() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            String url = "jdbc:oracle:thin:@heigoServer:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = "bmacsj"; // 用户名
            String password = "sa"; // 数据库密码
            conn = DriverManager.getConnection(url, user, password);// 获取连接
        } catch (Exception e) {
            logger.error("ERRNO: 0x000010");
        }
        return conn;
    }

    public static void main(String[] args) {
        long startDateTime = System.currentTimeMillis();
        System.out.println("Start: " + startDateTime);
        getConnection();
        long endDateTime = System.currentTimeMillis();
        System.out.println("End: " + endDateTime);
        long result = endDateTime - startDateTime;
        System.out.println("耗时: " + result);
    }
}

