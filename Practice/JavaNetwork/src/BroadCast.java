import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by chengs on 17-4-4.
 */
public class BroadCast {
    String str="上海大学有新邮箱啦,@shu.edu.cn 奔走相告吧!";
    int port=2344;
    InetAddress group=null;
    MulticastSocket socket=null;

    public static void main(String[] args) {
            new BroadCast().play();
    }
    BroadCast(){
        try{
            group=InetAddress.getByName("234.234.234.234"); //设置广播组的地址为239.255.8.0
            socket=new MulticastSocket(port);    //多点广播套接字将在port端口广播
            socket.setTimeToLive(10);   //如果参数为1,则多点套接字发送数据报范围为本地网络
            socket.joinGroup(group);    //加入group后,socket发送的数据报被group中的成员接收到

        }catch (Exception e){
            System.out.println("Error:"+e);
        }
    }
    public void play(){
        while(true){
            try{
                DatagramPacket packet=null;
                byte data[]= str.getBytes();
                packet=new DatagramPacket(data,data.length,group,port);
                System.out.println(new String(data));
                socket.send(packet);
                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
