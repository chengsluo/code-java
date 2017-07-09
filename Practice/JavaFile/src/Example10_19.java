import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by chengs on 17-3-31.
 */
public class Example10_19{
    public static void main(String[] args) {
            JDKWindow win=new JDKWindow();
    }
}

class JDKWindow extends JFrame implements ActionListener{
    JTextField javaSourceFileName;//输入Java文件名
    JTextField javaMainClassName;

    JButton compile,run,edit;

    public JDKWindow(){
        edit=new JButton("文件");
        compile=new JButton("编译");
        run=new JButton("运行");
        javaSourceFileName=new JTextField(10);
        javaMainClassName=new JTextField(10);
        setLayout(new FlowLayout());

        add(edit);
        add(compile);
        add(run);
        add(new JLabel("主类名:"));
        add(javaMainClassName);
        add(new JLabel("源文件名:"));
        add(javaSourceFileName);

        edit.addActionListener(this);
        compile.addActionListener(this);
        run.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,100,200,130);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==edit){
            Runtime ce=Runtime.getRuntime();//
            //Windows的写法

//            File file=new File("c:/windows","Notepad.exe");
//            try{
//                ce.exec(file.getAbsolutePath());
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }

            //Ubuntu的写法
            try {
                ce.exec("gedit");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource()==compile){
            CompileDialog compileDialog=new CompileDialog();
            String name=javaSourceFileName.getText();
            compileDialog.compile(name);
            compileDialog.setVisible(true);
        }else if(e.getSource()==run){
            RunDialog runDialog=new RunDialog();
            String name=javaMainClassName.getText();
            runDialog.run(name);
            runDialog.setVisible(true);

        }
    }
}

class CompileDialog extends JDialog{
    JTextArea showError;
    public CompileDialog(){

        setTitle("编译过程");
        showError=new JTextArea();

        Font f=new Font("宋体",Font.BOLD,20);
        showError.setFont(f);
        add(new JScrollPane(showError),BorderLayout.CENTER);
        setBounds(10,10,500,300);

    }
    public void compile(String name){
        try{
            Runtime ce=Runtime.getRuntime();//获取当前
            Process process=ce.exec("javac "+name);
            InputStream in=process.getErrorStream();

            BufferedInputStream bis=new BufferedInputStream(in);
            int n;
            boolean bn=true;
            byte error[]=new byte[100];

            while((n=bis.read(error,0,100))!=-1){
                String s=null;
                s=new String(error,0,n);
                showError.append(s);
                if(s!=null) bn=false;
            }
            if(bn) showError.append("编译正确!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RunDialog extends JDialog{
    JTextArea showOut;
    public RunDialog(){
        setTitle("运行过程");
        showOut=new JTextArea();
        Font f=new Font("宋体",Font.BOLD,15);
        showOut.setFont(f);
        add(new JScrollPane(showOut),BorderLayout.CENTER);
        setBounds(210,10,500,300);
    }
    public void run(String name){
        try{
            Runtime ce=Runtime.getRuntime();
            //此处有错误,无法输出java 程序运行后的结果
            //Process process=ce.exec("java "+name);
            Process process=ce.exec("ls");
            InputStream in=process.getInputStream();
            BufferedInputStream bis=new BufferedInputStream(in);
            int n;
            boolean bn=true;
            byte mess[]=new byte[100];
            while((n=bis.read(mess,0,100))!=-1){
                System.out.println(n);
                String s=null;
                s=new String(mess,0,n);
                showOut.append(s);
                if(s!=null) bn=false;
                if(bn) showOut.append("程序中没有时候out流输出信息");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}