import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chengs on 17-4-4.
 */
//网络汉字字符串交换可以用 readUTF()和writeUTF();

public class Server {
    public static void main(String[] args) {
        ServerSocket server=null;
        ServerThread serverThread;
        Socket MianSocket=null;
        while(true) {
            try {
                server = new ServerSocket(2010);
            }catch (IOException e){
                System.out.println("此端口已经在监听了,不能再建立ServerSocket了");
                //ServerSocket对象不能重复创建,不能使用已使用的端口号
                //除了首次进入,其他次都会在此抛出异常
                //这里也可以不处理
            }

            try{
                System.out.println(" 开始等待客户呼叫");
                MianSocket=server.accept();//注意返回类型是Socket
                System.out.println(" 有客户访问,其地址:"+MianSocket.getInetAddress());
            }catch (IOException e){
                System.out.println(" 等待中出现错误");
            }
            if(MianSocket!=null){//这种写法可以使得等待出现错误时,再次重新等待

                new ServerThread(MianSocket).start();//为每个客户启动一个专有服务线程
                //MianSocket=null;这一句也可以不处理,正常流程下来,MainSocket肯定被更新了,并且不为看空
            }
        }
    }
}
class ServerThread extends Thread{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    String str;

    ServerThread(Socket sock){
        socket=sock;
        try{
            out=new DataOutputStream(socket.getOutputStream());
            in=new DataInputStream(socket.getInputStream());
        }catch (IOException e){
            System.out.println("专有服务进程初始化失败!");
        }
    }
    public void run(){
        while(true){
            try{
                double r=in.readDouble();
                double area=Math.PI*r*r;
                out.writeDouble(area);
            } catch (IOException e) {
                System.out.println("客户离线");
                return;
            }
        }
    }
}
