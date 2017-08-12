package chengs.SingleFile;

import jdk.internal.org.objectweb.asm.tree.FrameNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chengs on 17-3-23.
 */
public class Example9_6 {
    public static void main(String[] args) {
        WindowActionEvent win=new WindowActionEvent();
        win.setTitle("处理ActionEvent");
        win.setBounds(100,100,200,260);
    }
}

class WindowActionEvent extends JFrame {
    JTextField textField;
    ActionListener listener;
    WindowActionEvent(){
        setLayout(new FlowLayout());
        textField=new JTextField(10);
        add(textField);
        listener=new ReaderListen(); //创建监视器
        textField.addActionListener(listener);//将listener注册为textField的监视器
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
class ReaderListen implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();
        System.out.println(str+":"+str.length());
    }
}