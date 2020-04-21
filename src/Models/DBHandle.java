package Models;

import java.sql.Connection;
import java.sql.Statement;

public class DBHandle {
    private String driver;
    private String url = "jdbc:mysql://remotemysql.com:3306/1ZIMXA3JFm?useSSL=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String userName="1ZIMXA3JFm";
    private final String password = "a7Pj1JKbF8";
    Connection connection;
    Statement statement;
}
