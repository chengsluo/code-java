package chengs.SingleFile;

import jdk.nashorn.internal.runtime.regexp.joni.exception.JOniException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by chengs on 17-3-28.
 */
public class Example9_12 {
    public static void main(String[] args) {
        Win win=new Win();
        win.setTitle("输入序列号");
        win.setBounds(100,100,400,100);
    }
}

class Win extends JFrame implements ActionListener{
    JTextField textFields[]=new JTextField[3];
    Police police;
    JButton button;
    MyDialog myDiglog;

    Win(){
        setLayout(new FlowLayout());
        police=new Police();
        for(int i=0;i<3;i++){
            textFields[i]=new JTextField(10);
            textFields[i].addKeyListener(police);
            textFields[i].addFocusListener(police);
            add(textFields[i]);
        }
        button=new JButton("确定");
        button.addActionListener(this);
        add(button);
        myDiglog=new MyDialog(this,"自定义对话框组件");
        myDiglog.setModal(true);//设置有模式对话框,强制需要先处理的对话框

        textFields[0].requestFocusInWindow();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        validate();//立即生效的意思
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"激活完成","确认",JOptionPane.INFORMATION_MESSAGE);
        myDiglog.setVisible(true);
        String str=myDiglog.getTitle();
        setTitle(str);
    }
}
class Police implements KeyListener,FocusListener{

    @Override
    public void focusGained(FocusEvent e) {
        JTextField textField=(JTextField)e.getSource();
        textField.setText(null);
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField t=(JTextField)e.getSource();
        if(t.getCaretPosition()>=6){//Returns the position of the text insertion caret for the text component.
            t.transferFocus();//Transfers the focus to the next component, as though this Component were the focus owner.
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

class MyDialog extends JDialog implements ActionListener{
    JTextField inputTitle;
    String title;
    MyDialog(JFrame f,String s){
        super(f,s);//使用JDialog默认的构造方法
        inputTitle=new JTextField(10);
        inputTitle.addActionListener(this);
        setLayout(new FlowLayout());
        add(new JLabel("请输入窗口的新标题:"));
        add(inputTitle);
        setBounds(60,60,100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//隐藏对话框
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        title=inputTitle.getText();
        setVisible(false);
    }

    @Override
    public String getTitle() {
        return title;
    }
}