import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by chengs on 17-4-4.
 */
public class Client {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Socket mySocket=null;

        DataInputStream in=null;
        DataOutputStream out=null;

        Thread readData;
        Read read=null;

        try{
            mySocket=new Socket();
            read=new Read();
            readData=new Thread(read);
            System.out.print("请输入服务器的IP:");
            String IP=scanner.nextLine();
            System.out.print("请输入端口号:");
            int port=scanner.nextInt();

            if(!mySocket.isConnected()){//保险起见,无实际作用
                InetAddress address=InetAddress.getByName(IP);
                InetSocketAddress socketAddress=new InetSocketAddress(address,port);
                mySocket.connect(socketAddress);
                in=new DataInputStream(mySocket.getInputStream());
                out=new DataOutputStream(mySocket.getOutputStream());

                read.setDataInputStream(in);//将socket的发送消息工作交给另外一个线程
                readData.start();
                //主线程只负责接受消息,其实接受消息也可以交给其他线程,依据实际需求而定
            }
        } catch (UnknownHostException e) {
            System.out.println("IP地址格式错误");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("服务器连接未成功,或IP和端口错误");
            System.exit(1);//连接未成功
        }
        System.out.print("请输入圆半径");
        while(scanner.hasNext()){
            double radius=0;
            try{
                radius=scanner.nextDouble();
            }catch (InputMismatchException exp){
                System.exit(0);
            }
            try{
                out.writeDouble(radius);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("提交异常,服务器错误!");
                System.exit(1);
            }
        }
    }
}
class Read implements Runnable{
    DataInputStream dataInputStream;

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    @Override
    public void run() {
        double result=0;
        while (true){
            try {
                result=dataInputStream.readDouble();//读取服务器发送过来的信息
                System.out.println("圆的面积:"+result);
                System.out.print("输入圆的半径(放弃请输入N):");
            }catch (IOException e){
                System.out.println("接收异常,与服务器已断开!");
                break;
            }
        }
    }
}