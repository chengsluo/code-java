import java.io.IOException;
import java.net.*;

/**
 * Created by chengs on 17-4-4.
 */
//利用D类地址进行广播数据报
public class Receiver {
    public static void main(String[] args) {
        int port=2344;                  //组播的端口号
        InetAddress group=null;         //组播组的地址
        MulticastSocket socket=null;    //多点广播套接字
        try{
            group=InetAddress.getByName("234.234.234.234");
            socket=new MulticastSocket(port);   //多点广播将在port端口上广播
            socket.joinGroup(group);    //加入此组播组

        } catch (UnknownHostException e) {
            System.out.println("IP地址格式错误");
        }catch (Exception e){
            System.out.println("接收端异常!");
        }

        while(true){
            byte data[]=new byte[8192];
            DatagramPacket packet=null;
            packet=new DatagramPacket(data,data.length,group,port);
            //注意构造接收包方式的不同
            try{
                socket.receive(packet);
                String mess=new String(packet.getData(),0,packet.getLength());
                System.out.println("接收的内容:\n"+mess);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
