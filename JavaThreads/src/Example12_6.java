import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by chengs on 17-4-4.
 */
//守护线程区别与用户线程,当本进程里面的其他所有用户线程都结束后,不管守护线程有没有执行完它的run(),它都立即中断结束,可以用他来处理一些不是特别重要的事情,比如日志记录
public class Example12_6 {
    public static void main(String[] args) {
        Daemon daemon=new Daemon();
        daemon.A.start();
        daemon.B.setDaemon(true);//设置B为守护线程
        daemon.B.start();

    }
}
class Daemon implements Runnable{
    Thread A,B;
    Daemon(){
        A=new Thread(this);
        B=new Thread(this);
    }

    @Override
    public void run() {
        if(Thread.currentThread()==A){
            for(int i=0;i<8;i++){
                System.out.println("i="+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else if(Thread.currentThread()==B){
            while (true){
                System.out.println("线程B是守护线程");
                try{
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("守护线程提前自己结束了");//如果守护线程提前结束,它会立即重启守护线程,这就是所谓的守护!!

            }
        }
    }
}