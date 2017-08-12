package chengs.SingleFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by chengs on 17-3-25.
 */
public class Example9_9 {
    public static void main(String[] args) {
        WindowDocment win=new WindowDocment();
        win.setTitle("简单文本编辑器");
        win.setBounds(100,100,400,400);
    }
}
class WindowDocment extends JFrame {
    JTextArea inputText,showText;
    JMenuBar menubar;
    JMenu menu;
    JMenuItem itemCopy,itemCut,itemPaste;

    TextListener textChangeListener; //inputText的监视器
    HandleListener handleListener; //itemCopy,itemCut,itemPaste的监视器

    WindowDocment(){
        init();
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void init(){
        inputText=new JTextArea(15,20);
        showText=new JTextArea(15,20);

        //showText.setLineWrap(true);//设置文本自动换行
        //showText.setWrapStyleWord(true);//文本区以单词为界 自动换行

        menubar=new JMenuBar();
        menu=new JMenu("编辑");

        itemCopy=new JMenuItem("复制(C)");
        itemCut=new JMenuItem("剪贴(X)");
        itemPaste=new JMenuItem("粘贴(V)");

        itemCopy.setAccelerator(KeyStroke.getKeyStroke("c"));
        itemCut.setAccelerator(KeyStroke.getKeyStroke("x"));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke("v"));


        itemCopy.setActionCommand("copy");
        itemCut.setActionCommand("cut");
        itemPaste.setActionCommand("paste");

        menu.add(itemCopy);
        menu.add(itemCut);
        menu.add(itemPaste);

        menubar.add(menu);
        setJMenuBar(menubar);
        add(new JScrollPane(inputText));
        add(new JScrollPane(showText));//JScrollPane带滚动条


        textChangeListener=new TextListener();
        textChangeListener.setInputArea(inputText);
        textChangeListener.setOutputArea(showText);


        handleListener=new HandleListener();
        handleListener.setInputText(inputText);
        handleListener.setShowText(showText);

        (inputText.getDocument()).addDocumentListener(textChangeListener);//向文档注册监视器

        itemCopy.addActionListener(handleListener);
        itemCut.addActionListener(handleListener);
        itemPaste.addActionListener(handleListener);
    }
}

class TextListener implements DocumentListener {
    JTextArea inputArea,outputArea;

    public void setInputArea(JTextArea inputArea) {
        this.inputArea = inputArea;
    }

    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String str=inputArea.getText();
        String regex="[\\s\\d\\p{Punct}]+";
        String words[]=str.split(regex);
        Arrays.sort(words);
        outputArea.setText(null);
        for(int i=0;i<words.length;i++){
            outputArea.append(words[i]+";");
        }
        outputArea.append("\n总计单词数:"+words.length);
    }
}
class HandleListener implements ActionListener{
    JTextArea inputText,showText;

    public void setInputText(JTextArea inputText) {
        this.inputText = inputText;
    }

    public void setShowText(JTextArea showText) {
        this.showText = showText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();
        if(str.equals("copy")){
            showText.copy();
        }else if(str.equals("cut")){
            showText.cut();
        }else if(str.equals("paste")){
            showText.paste();
        }

    }
}