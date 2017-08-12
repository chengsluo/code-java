import java.io.*;

/**
 * Created by chengs on 17-3-31.
 */

//依赖于FileStream
//数据流,可以使程序按着机器无关的风格读取Java原始数据,只需要指定数据类型

public class Example10_11 {
    public static void main(String[] args) {
        File file=new File("apple.txt");
        try{
            FileOutputStream fos=new FileOutputStream(file);
            DataOutputStream dos=new DataOutputStream(fos);
            dos.writeInt(100);
            dos.writeLong(12345);
            dos.writeFloat(3.1423f);
            dos.writeChars("How are you doing");
            dos.writeInt(23124);
            dos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            FileInputStream fis=new FileInputStream(file);
            DataInputStream dis=new DataInputStream(fis);
            System.out.println(dis.readInt());
            System.out.println(dis.readLong());
            System.out.println(dis.readFloat());
            char ch;
            //注意读取字符串的写法
            while (dis.available()>0) {
                   ch=dis.readChar();
                   System.out.print(ch);
                   //注意此种写法会将所有的后面的字符串都写入
            }
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
