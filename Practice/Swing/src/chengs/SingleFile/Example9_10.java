import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Chengs Luo on 2017/3/27.
 */
public class Example9_10 {
    public static void main(String[] args) {
        WindowMouse win=new WindowMouse();
        win.setTitle("处理鼠标事件");
        win.setBounds(10,10,460,460);

    }
}
class WindowMouse extends JFrame {
    JTextField text;
    JButton button;
    JTextArea textArea;
    MousePlice police;
    WindowMouse(){
        init();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    void init(){
        setLayout(new FlowLayout());
        text=new JTextField(10);
        textArea=new JTextArea(5,28);
        police=new MousePlice();
        police.setArea(textArea);
        text.addMouseListener(police);
        button=new JButton("按钮");
        button.addMouseListener(police);
        addMouseListener(police);
        add(button);
        add(text);
        add(new JScrollPane(textArea));
    }
}

class MousePlice implements MouseListener{
    JTextArea area;

    public void setArea(JTextArea area) {
        this.area = area;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()>=2){
            area.setText("鼠标连击，位置"+"("+e.getX()+","+e.getY()+")");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("\n鼠标按下，位置："+"("+e.getX()+","+e.getY()+")");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("\n鼠标释放，位置："+"("+e.getX()+","+e.getY()+")");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JButton){
         area.append("\n鼠标进入按钮位置，位置："+"（"+e.getX()+","+e.getY()+")");
        }
        if(e.getSource() instanceof JTextField){
            area.append("\n鼠标进入文本框位置，位置："+"（"+e.getX()+","+e.getY()+")");
        }
        if(e.getSource() instanceof JFrame){
            area.append("\n鼠标进入窗口，位置："+"（"+e.getX()+","+e.getY()+")");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        area.append("\n鼠标退出，位置："+"（"+e.getX()+","+e.getY()+")");
    }
}