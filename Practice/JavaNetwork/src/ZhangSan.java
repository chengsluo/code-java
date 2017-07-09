import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by chengs on 17-4-4.
 */
//UDP通信

public class ZhangSan {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Thread readData;
        ReceiveLetterForZhang receive=new ReceiveLetterForZhang();

        try{
            readData=new Thread(receive);
            readData.start(); //启动负责接受的线程

            byte []buffer=new byte[1];//只是一个凑数的指针,后面会被替换掉
            InetAddress address=InetAddress.getByName("127.0.0.1");

            DatagramPacket dataPack=new DatagramPacket(buffer,buffer.length,address,2333);
            DatagramSocket postman=new DatagramSocket();
            System.out.println("输入发送给李四的信息");
            while(scanner.hasNext()){
                String mess=scanner.nextLine();
                buffer=mess.getBytes();

                if(mess.length()==0){
                    System.exit(0);
                }

                dataPack.setData(buffer);
                postman.send(dataPack);//发送过程不会因对方接口关闭而出现异常.
                System.out.println("继续输入发送给李四的信息");
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
class ReceiveLetterForZhang implements Runnable{

    @Override
    public void run() {
        DatagramPacket pack=null;
        DatagramSocket postman=null;
        byte data[]=new byte[8192];
        try{
            pack=new DatagramPacket(data,data.length);
            postman=new DatagramSocket(2444);
        }catch (Exception e){
            System.out.println("接受器构造过程异常");
        }

        while(true){
            if(postman==null) break;
            else{
                try {
                    postman.receive(pack);//会阻塞
                    String message=new String(pack.getData(),0,pack.getLength());
                    System.out.printf("%25s\n","收到:"+message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}