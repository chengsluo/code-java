import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chengs on 17-4-4.
 */
public class Example12_5 {
    public static void main(String[] args) {
        Win win=new Win();
    }
}

class Win extends JFrame implements Runnable,ActionListener{
    Thread showTime;

    JTextArea text=null;

    JButton buttonStart=new JButton("Start"),buttonStop=new JButton("Stop");
    boolean die;
    SimpleDateFormat m=new SimpleDateFormat("hh:mm:ss");
    Date date;

    public  Win(){
        showTime=new Thread(this);
        die=false;

        text=new JTextArea();
        add(new JScrollPane(text), BorderLayout.CENTER);
        JPanel p=new JPanel();
        p.add(buttonStart);
        p.add(buttonStop);

        buttonStart.addActionListener(this);
        buttonStop.addActionListener(this);

        add(p,BorderLayout.NORTH);

        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonStart){
            if(!showTime.isAlive()){
                showTime=new Thread(this);
                die=false;
                try{
                    showTime.start();
                }catch (Exception e1){
                    text.setText("Thread is not end,you couldn't start() again!");
                }
            }
        }else if(e.getSource()==buttonStop){
            die=true;
        }
    }

    @Override
    public void run() {
        while(true){
            date=new Date();
            text.append("\n"+m.format(date));
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(die==true){
                return;
            }
        }
    }
}