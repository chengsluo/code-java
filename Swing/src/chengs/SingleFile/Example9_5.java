package chengs.SingleFile;

import javax.swing.*;

/**
 * Created by chengs on 17-3-23.
 */
public class Example9_5 {
    public static void main(String[] args) {
        WindowBoxLayout win=new WindowBoxLayout();
        win.setTitle("嵌套盒式布局");
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setBounds(100,100,310,260);
    }
}

class WindowBoxLayout extends JFrame {
    Box baseBox,BoxV1,BoxV2;
    public WindowBoxLayout(){
        setLayout(new java.awt.FlowLayout());
        init();
        setVisible(true);
    }
    void init(){
        BoxV1=Box.createVerticalBox();
        BoxV1.add(new JLabel("姓名"));
        BoxV1.add(Box.createVerticalStrut(8));
        BoxV1.add(new JLabel("Email"));
        BoxV1.add(Box.createVerticalStrut(8));
        BoxV1.add(new JLabel("职业"));
        BoxV2=Box.createVerticalBox();
        BoxV2.add(new JTextField(10));
        BoxV2.add(Box.createVerticalStrut(8));
        BoxV2.add(new JTextField(10));
        BoxV2.add(Box.createVerticalStrut(8));
        BoxV2.add(new JTextField(10));
        baseBox=Box.createHorizontalBox();
        baseBox.add(BoxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(BoxV2);
        add(baseBox);
    }
}
