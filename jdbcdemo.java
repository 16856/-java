/**
 * Created by Administrator on 2017-05-25.




mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true

 --pom.xml
 <dependencies>
 <!-- 添加oracle jdbc driver -->
 <dependency>
 <groupId>com.oracle</groupId>
 <artifactId>ojdbc6</artifactId>
 <version>11.2.0.4</version>
 </dependency>
 </dependencies>
*/

import java.sql.*;

public class jdbcdemo {
    public static void main (String args []) throws SQLException
    {
        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL=TCP) (HOST=10.0.5.97)(PORT=1521)) (ADDRESS = (PROTOCOL=TCP)(HOST=10.0.5.99) (PORT=1521))) (CONNECT_DATA = (SERVICE_NAME = cnmcprod)))","apps","apps");
// @machineName:port:SID, userid, password
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select INSTANCE_NUMBER,INSTANCE_NAME,STATUS from gv$instance");
        while (rset.next())
            System.out.println (rset.getString(1)+" "+ rset.getString(2)+" "+ rset.getString(3)); // Print col 1
        stmt.close();
    }
}


/*
* run.
* export CLASSPATH=/u02/cnmcsit/db/tech_st/11.2.0/jdbc/lib/ojdbc5.jar
* export PATH=/u02/cnmcsit/db/tech_st/11.2.0/jdk/bin:$PATH
* javac hello.java
* java  hello.class
*
* */