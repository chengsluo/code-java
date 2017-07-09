import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chengs on 17-3-28.
 */
public class Example10_4 {
    public static void main(String[] args) {
        int n=-1;
        byte[] a=new byte[100];
        try{
            File f=new File("baidu.txt");
            InputStream in=new FileInputStream(f);
            while ((n=in.read(a,0,100))!=-1){//最长每行只读100个字符
                String s=new String(a,0,n);//读入的字节必须经过这般转化才能利用
                System.out.println(s);
            }
            in.close();
        }catch (IOException e){
            System.out.println("File read Error:"+e.getMessage());
        }
    }
}
