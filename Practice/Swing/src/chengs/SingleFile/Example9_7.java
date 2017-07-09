package chengs.SingleFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chengs on 17-3-23.
 */
public class Example9_7 {
    public static void main(String[] args) {
        new WindowAct();
    }
}

class WindowAct extends JFrame{
    JTextArea text;
    JTextField field;
    JButton button;
    WindowAct(){
        setLayout(new FlowLayout());
        text=new JTextArea(5,10);
        field=new JTextField(10);
        button=new JButton("确定");
        setEvent(new CheckListen());
        add(field);
        add(button);
        add(text);
        setVisible(true);
        setTitle("Event");
        setBounds(100,100,200,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    void setEvent(MyCommandListener mylistear){
        mylistear.setJTextArea(text);
        mylistear.setJTextField(field);
        button.addActionListener(mylistear);//通过对接口的重写实现了listener同时操纵多个对象

        //一个监视器listener可以注册到多个对象上,只要他们处理的事件相同
        field.addActionListener(mylistear);
    }
}

interface MyCommandListener extends ActionListener{
    public void setJTextField(JTextField field);
    public void setJTextArea(JTextArea area);
}

class CheckListen implements MyCommandListener{
    JTextField jTextField;
    JTextArea jTextArea;

    @Override
    public void actionPerformed(ActionEvent e) {
        String str=jTextField.getText();
        jTextArea.append(str+":"+str.length()+"\n");
    }

    @Override
    public void setJTextField(JTextField field) {
        jTextField=field;
    }

    @Override
    public void setJTextArea(JTextArea area) {
        jTextArea=area;
    }
}