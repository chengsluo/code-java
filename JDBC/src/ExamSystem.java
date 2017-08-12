import java.sql.*;
import java.util.Scanner;

/**
 * Created by chengs on 17-4-1.
 */
public class ExamSystem {
    public static void main(String[] args) {
        ReadExamPaper reader;
        reader=new ReadExamPaper();
        reader.setSourceName("test");
        reader.setTableName("examTest");
        Scanner scanner=new Scanner(System.in);
        int amount=reader.getAmount();

        if(amount==0||amount==-1){
            System.out.println("没有试题,无法考试!请核查系统");
            System.exit(0);
        }

        System.out.printf("----试卷共有%d道题目----\n",amount);
        System.out.printf("输入提号:如1,2,..开始考试: \n");
        try{
            while (scanner.hasNextInt()){
                int number=scanner.nextInt();
                //String enterKey=scanner.next();//消耗掉用户输入的回车
                if(number>=1&&number<=amount){
                    String[]question=reader.getExamQuestion(number);
                    for(int i=0;i<question.length-1;i++){
                        System.out.println(question[i]);
                    }
                    System.out.println("请输入选项");
                    String ans=scanner.next("[ABCDabcd]");
                    if(ans.equalsIgnoreCase(question[question.length-1])){
                        System.out.println("回答正确");
                    }else{
                        System.out.println("回答错误");
                    }
                }
            }
            System.out.println("非法输入,考试结束");
        }catch (Exception e){
            System.out.println("非法输入,考试结束");
        }
    }
}
class  ReadExamPaper{
    String sourceName;
    String tableName;
    Connection connection;
    Statement sql;
    ResultSet resultSet;
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    ReadExamPaper(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getAmount(){
        try{
            connection = DriverManager.getConnection(("jdbc:mysql://localhost/"+ sourceName), "test", "test");
            sql=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=sql.executeQuery("SELECT * from "+tableName);
            resultSet.last();
            int rows=resultSet.getRow();
            connection.close();
            return rows;
        } catch (SQLException e) {
            System.out.println("数据库连接错误:" + e.getMessage());
        }
        return -1;
    }

    public String[] getExamQuestion(int number){

        String []examPaper=new String [6];

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + sourceName, "test", "test");
            sql=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=sql.executeQuery("SELECT * from "+tableName);
            resultSet.absolute(number);
            //定位到这道题
            examPaper[0]=""+number+"."+resultSet.getString(2);
            examPaper[1]="A."+resultSet.getString(3);
            examPaper[2]="B."+resultSet.getString(4);
            examPaper[3]="C."+resultSet.getString(5);
            examPaper[4]="D."+resultSet.getString(6);
            examPaper[5]=resultSet.getString(7);
            connection.close();
            return examPaper;
        } catch (SQLException e) {
            System.out.println("无法获得试题:" + e.getMessage());
        }
        for(int i=0;i<6;i++)
            examPaper[i]="获取错误";
        return examPaper;
    }
}