package com.ccg.io.file;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Administrator
 *
 */
public class MyFileUtilsTest {
	
	
	/**
     * 将数据库数据ResultSet写到文件.Txt里
     */
	@Test
	public void writeResultSetToTxt() throws Exception {
        Connection conn = null;
        //测试库
        /*String url="jdbc:oracle:thin:@134.96.188.186:1521:HZCW";
        String username="zjcw";  
        String password="qwer1234";*/
        
        //本地环境  (局部变量final修饰，可调用时初始化，只能初始化一次)
        final String url="jdbc:oracle:thin:@localhost:1521/orcl";
        final String username="test";  
        final String password="123456";
  
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //连接测试环境
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            // executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            ResultSet rs = stmt.executeQuery("select * from daily_debt_total");
            List<String> writeStrList = new ArrayList<String>();
            while (rs.next()) {
                String stringDes = rs.getString(1) +","+rs.getString(2) +"," +rs.getString(3) +","+rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6);
                writeStrList.add(stringDes);
            }
          
            String filePath = "E:\\all_tables.dat";
            MyFileUtils.writeStringToFile(writeStrList,filePath,true);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
  
    }
	
	@Test
	public void readStringFromUrlTest() throws MalformedURLException{
		URL url = new URL( "http://commons.apache.org" );
		System.out.println(MyFileUtils.readStringFromUrl(url));
	}
}
