import java.io.*;

/**
 * Created by chengs on 17-3-28.
 */

//字节流
//在中英文混杂的Unicode字符串情况下很容易处理不当
public class Example10_5 {
    public static void main(String[] args) {
        byte []a="新年快乐吧".getBytes();
        System.out.println(a.length);
        byte []b="Happy new Year".getBytes();
        File file=new File("a.txt");  //指定输出的目的地
        try{
            OutputStream out=new FileOutputStream(file);//准备重建文件
            System.out.println(file.getName()+"的大小:"+file.length()+"字节");

            out.write(a);
            out.close();//使内存缓存保存到硬盘
            out=new FileOutputStream(file,true);//准备向文件尾加内容
            System.out.println(file.getName()+"的大小:"+file.length()+"字节");

            out.write(b,0,b.length);
            System.out.println(b.length);
            System.out.println(file.getName()+"的大小:"+file.length()+"字节");

            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
