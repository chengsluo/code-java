package chengs.SingleFile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chengs on 17-3-23.
 */
public class Example9_4 {
    public static void main(String[] args) {
        new WinGrid();
    }
}

class WinGrid extends JFrame{
    GridLayout grid;
    JPanel chessboard;
    WinGrid(){
        chessboard=new JPanel();
        grid=new GridLayout(12,12);
        chessboard.setLayout(grid);
        Label label[][]=new Label[12][12];
        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                //label还要进行新建一次!!!
                //之前仅仅是申请了相应的二维指针数组
                label[i][j]=new Label();
                if((i+j)%2==0){
                    label[i][j].setBackground(Color.BLACK);
                }else{
                    label[i][j].setBackground(Color.WHITE);
                }
                chessboard.add(label[i][j]);
            }
        }
        add(chessboard,BorderLayout.CENTER);
        add(new JButton("北方参战者"),BorderLayout.NORTH);
        add(new JButton("男方参战者"),BorderLayout.SOUTH);
        add(new JButton("东方观战"),BorderLayout.EAST);
        add(new JButton("东方观战"),BorderLayout.WEST);
        setBounds(10,10,570,390);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        validate();
    }
}