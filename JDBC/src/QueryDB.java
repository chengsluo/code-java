import org.omg.CORBA.PUBLIC_MEMBER;
import java.sql.*;
import javax.swing.*;

/**
 * Created by chengs on 17-4-2.
 */
public class QueryDB {
    public static void main(String[] args) {
        JTable table;
        Query query;
        query=new Query();
        query.setDatasourceName("test");
        query.setTableName("examTest");
        Object a[][]=query.getRecord();
        int m=a[0].length;
        String b[]=new String[m];
        for(int i=0;i<m;i++){
            b[i]="字段"+(i+1);
        }
        table=new JTable(a,b);
        JFrame frame=new JFrame();

        frame.add(new JScrollPane(table));
        frame.setVisible(true);
        frame.setBounds(20,20,300,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}

class Query{
    String datasourceName="";
    String tableName="";

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    Object a[][];
    public Query(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch (Exception e){
            System.out.println("数据库驱动异常:"+e.getMessage());
        }
    }

    public Object[][] getRecord(){
        Connection con;
        PreparedStatement sql;
        ResultSet rs;

        try{
            String uri="jdbc:mysql://localhost/"+datasourceName;
            String id="test";
            String password="test";
            con=DriverManager.getConnection(uri,id,password);

            DatabaseMetaData metaData=con.getMetaData();
            ResultSet rs1=metaData.getColumns(null,null,tableName,null);

            int CountColumns=0;
            while (rs1.next()){
                CountColumns++;
            }

            sql=con.prepareStatement("SELECT * FROM "+tableName);
            rs=sql.executeQuery();
            rs.last();
            int rows=rs.getRow();
            a=new Object[rows][CountColumns];

            rs.beforeFirst();
            int m=0;
            while(rs.next()){
                for(int k=1;k<CountColumns;k++){
                    a[m][k-1]=rs.getString(k);
                }
                m++;
            }
            con.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}