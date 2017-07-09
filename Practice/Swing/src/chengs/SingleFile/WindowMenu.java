package chengs.SingleFile;

import jdk.internal.util.xml.impl.Input;
import kotlin.reflect.jvm.internal.impl.descriptors.EffectiveVisibility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by chengs on 17-3-20.
 */
public class WindowMenu extends JFrame{
    JMenuBar menubar;
    JMenu menu,subMenu;
    JMenuItem item1,item2;

    WindowMenu(){}
    WindowMenu(String s,int x,int y,int w,int h){
        init(s);
        setLocation(x,y);
        setSize(w,h);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    void init(String s){
        setTitle(s);
        menubar=new JMenuBar();
        menu=new JMenu("菜单");
        subMenu=new JMenu("软件项目");
        item1=new JMenuItem("保存");
        item2=new JMenuItem("退出");
        item1.setAccelerator(KeyStroke.getKeyStroke('A'));
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        menu.add(subMenu);
        menu.addSeparator();
        menu.add(item1);
        menu.addSeparator();
        menu.add(item2);
        subMenu.add(new JMenuItem("HelloJava"));
        subMenu.add(new JMenuItem("HelloSwing"));

        menubar.add(menu);
        setJMenuBar(menubar);

    }
}
