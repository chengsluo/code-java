import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by chengs on 17-3-31.
 */
public class Example10_17 {
    public static void main(String[] args) {
        WindowReader win=new WindowReader();
    }
}
class WindowReader extends JFrame implements ActionListener{
    JFileChooser fileChooser;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem itemSave,itemOpen;

    JTextArea textArea;

    BufferedReader in;
    FileReader fileReader;

    FileInputStream fileInputStream;
    ProgressMonitorInputStream pin;

    BufferedWriter out;
    FileWriter fileWriter;

    WindowReader(){
        textArea=new JTextArea(10,10);
        textArea.setFont(new Font("楷体_gb2312",Font.PLAIN,16));
        add(new JScrollPane(textArea),BorderLayout.CENTER);
        //这种写法就可以实现控件占满整个窗口
        menuBar=new JMenuBar();
        menu=new JMenu("文件");

        itemOpen=new JMenuItem("打开");
        itemSave=new JMenuItem("保存");

        itemOpen.addActionListener(this);
        itemSave.addActionListener(this);

        menu.add(itemOpen);
        menu.add(itemSave);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        fileChooser=new JFileChooser(); //文件对话框
        FileNameExtensionFilter filter=new FileNameExtensionFilter("lcs文件","lcs");//文件筛选框组件
        fileChooser.setFileFilter(filter); //添加此筛选框

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("神的编辑器");
        setBounds(100,100,600,700);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemSave){
            int state=fileChooser.showSaveDialog(this);
            if(state==JFileChooser.APPROVE_OPTION){//等待选择完成
                try{
                    File dir=fileChooser.getCurrentDirectory();//获取选择结果,返回存放文件的路径
                    String name=fileChooser.getSelectedFile().getName();
                    File file=new File(dir,name);//利用路径加文件名创建文件对象
                    fileWriter=new FileWriter(file);

                    out=new BufferedWriter(fileWriter);
                    out.write(textArea.getText());//利用缓冲输出流写文件
                    out.close();
                    fileWriter.close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }else if(e.getSource()==itemOpen){
            int state=fileChooser.showOpenDialog(this);
            if(state==JFileChooser.APPROVE_OPTION){
                try{
                    File dir=fileChooser.getCurrentDirectory();
                    String name=fileChooser.getSelectedFile().getName();
                    File file=new File(dir,name);
                    fileReader=new FileReader(file);

                    in=new BufferedReader(fileReader);
                    String str;
                    textArea.setText("");
                    while((str=in.readLine())!=null){
                        textArea.append(str+"\n");
                    }
//                    //带进度条的实现方式,并不是特别好用!!
//                    byte b[]=new byte[30];
//                    fileInputStream=new FileInputStream(file);
//                    pin=new ProgressMonitorInputStream(null,"正在读取文件",fileInputStream);
//
//                    ProgressMonitor p=pin.getProgressMonitor();//获取进度条
//                    while((pin.read(b))!=-1){
//                        String s=new String(b);
//                        System.out.println(s);
//                        Thread.sleep(1000);//暂停1000毫秒
//                    }

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
                }
            }

        }

    }
}