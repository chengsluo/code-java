import java.sql.*;

/**
 * Created by chengs on 17-3-31.
 */
public class Example11_1 {
    public static void main(String[] args) {
        Connection con;
        Statement sql;
        ResultSet rs;
        //Windows系统连接数据库方法(1/2:建立JDBC-ODBC桥接器)
        try{
            Class.forName("sun.jdbc.odbc.jdbcOdbcDriver");
            //1.利用Class类建立jdbc-odbc桥接器,forName为Class的静态方法,表示加载此类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.在windows系统的"ODBC数据源"管理工具里面,增改数据源和驱动
        //3.连接并执行查询
        try{
            con=DriverManager.getConnection("jdbc:odbc:myData","","");
            //数据源名字为myData,且未设置用户名和密码
            sql=con.createStatement();
            rs=sql.executeQuery("SELECT * FROM goods WHERE price>4");
            while(rs.next()){
                String number=rs.getString(1);
                String name=rs.getString(2);
                Date date=rs.getDate("madeTime");
                double price=rs.getDouble("price");

                System.out.printf("%-4s",number);
                System.out.printf("%-6s",name);
                System.out.printf("-15s",date.toString());
                System.out.printf("%6s\n",price);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
