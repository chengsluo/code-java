package chengs.SingleFile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chengs on 17-3-20.
 */
public class Example9_1 {
    public static void main(String[] args) {
        JFrame window1=new JFrame("第一个窗口");
        JFrame window2=new JFrame("第二个窗口");
        Container con=window1.getContentPane();
        con.setBackground(Color.green);
        window1.setBounds(200,150,250,250);//设置位置及大小,左边距,上边距,宽度,高度(单位像素)
        window2.setBounds(600,150,250,250);

        window1.setVisible(true);
        window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                //释放当前窗口
        window2.setVisible(true);
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                //退出程序
    }
}
