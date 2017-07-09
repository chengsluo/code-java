package chengs.SingleFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chengs on 17-3-28.
 */
public class Example9_22 {
    public static void main(String[] args) {
        WinTable win=new WinTable();
    }
}

class WinTable extends JFrame implements ActionListener{
    JTable table;
    Object a[][];//任何类型的一个二维数组形式的对象指针
    Object name[]={"姓名","英语成绩","树学成绩","总成绩"};
    JButton button;

    WinTable(){
        a=new Object[8][4];
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(j!=0){
                    a[i][j]="0";
                }else{
                    a[i][j]="姓名";
                }
            }
        }

        button =new JButton("计算每人总成绩");

        table=new JTable(a,name);//数据和标题
        button.addActionListener(this);
        Container con=getContentPane();
        con.add(new JScrollPane(table),BorderLayout.CENTER);
        con.add(new JLabel("修改或录入数据后,需回车确认返回"),BorderLayout.NORTH);
        con.add(button,BorderLayout.SOUTH);

        setSize(400,400);
        setTitle("成绩填写");
        setVisible(true);
        validate();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 8; i++) {
            boolean boo=true;
            int sum = 0;
            try {
                for (int j = 1; j < 3; j++) {
                    sum += Double.parseDouble(a[i][j].toString());
                }
            }catch(Exception ee){
                boo=false;
                JOptionPane.showMessageDialog(this,"输入有误","提示",JOptionPane.INFORMATION_MESSAGE);

                table.repaint();
            }
            if(boo==true){
                a[i][3]=""+sum;
                table.repaint();
            }
        }
    }
}