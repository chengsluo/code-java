import java.sql.*;

/**
 * Created by chengs on 17-3-31.
 */

//Linux连接jdbc方式,首先需要下载 mysql-connector-java
    //将其*.jar放置在jdk里面的jdk1.6.0_22/jre/lib/ext/里面
public class Example11_2 {
    public static void main(String[] args) {
        mysqlConnect jdbc=new mysqlConnect();
    }
}
class mysqlConnect{
    public mysqlConnect(){
        String dbDriver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/shop";
        String username = "chengs";
        String password = "chengs";

        Statement mStatement = null;
        ResultSet mResultSet = null;
        Connection mConnection = null;

        String sql = "select * from goods";

        try{
            Class.forName(dbDriver).newInstance();
            mConnection = DriverManager.getConnection(url, username, password);
            mStatement = mConnection.createStatement();
            mResultSet = mStatement.executeQuery(sql);

            try{
                while(mResultSet.next()){
                    System.out.print(mResultSet.getString(1) + "\t");
                    System.out.print(mResultSet.getString(2) + "\t");
                        System.out.print(mResultSet.getDate(3) + "\t");
                        System.out.println(mResultSet.getDouble(4));
                    }
                }catch (Exception e){
                    System.out.println("数据库读取错误！ \n" + e.getMessage());
                }
                mStatement.close();
            mConnection.close();
        }catch (SQLException e){
            System.out.println("连接数据库错误: \n" + url + "\n" + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}