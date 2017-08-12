import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by chengs on 17-3-31.
 */

public class Example10_9 {
    public static void main(String[] args) {
        RandomAccessFile in=null;
        try{
            in=new RandomAccessFile("src/Example10_9.java","rw");
            long length=in.length();
            long position =0;
            in.seek(position);
            while (position<length){
                String str=in.readLine();
                byte b[]=str.getBytes("iso-8859-1");//解决读入非ASCII字符乱码问题
                str=new String(b);
                position=in.getFilePointer();//读入当前位置
                System.out.println(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
