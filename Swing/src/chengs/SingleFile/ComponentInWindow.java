package chengs.SingleFile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chengs on 17-3-22.
 */
public class ComponentInWindow extends JFrame {
    JTextField text;//文本
    JButton button;//按钮
    JCheckBox checkBox1,checkBox2,checkBox3;
    JRadioButton radio1,radio2;
    ButtonGroup group;
    JComboBox comboBox;
    JTextArea area;

    public ComponentInWindow(){
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init(){
        setLayout(new FlowLayout());


        add(new JLabel("文本框"));


        text=new JTextField(10);//文本框,长度为10
        add(text);

        add(new JLabel("按钮:"));
        button=new JButton("确定");
        add(button);

        add(new JLabel("选择框:"));
        checkBox1=new JCheckBox("音乐");
        checkBox2=new JCheckBox("旅游");
        checkBox3=new JCheckBox("篮球");
        add(checkBox1);
        add(checkBox2);
        add(checkBox3);

        add(new JLabel("单选按钮:"));
        group=new ButtonGroup();
        radio1=new JRadioButton("A");
        radio2=new JRadioButton("B");
        group.add(radio1);
        group.add(radio2);
        add(radio1);
        add(radio2);


        add(new JLabel("下拉列表:"));
        comboBox=new JComboBox();
        comboBox.addItem("星际争霸2");
        comboBox.addItem("LOL");
        comboBox.addItem("植物大战僵尸");
        comboBox.addItem("饥荒");
        add(comboBox);

        add(new JLabel("文本区"));
        area=new JTextArea(1,12);
        add(new JScrollPane(area));//创建可以滚动的文本区

    }
}
