import java.io.*;

/**
 * Created by chengs on 17-3-30.
 */
//字符流
//操作以字符为单位,能够有效解决Unicode由于字符字节长度不一致出现的问题
public class Example10_6 {
    public static void main(String[] args) {
        File sourceFile= new File("a.txt");
        File targetFile=new File("b.txt");
        char c[]=new char[19];
        try{
            Writer out=new FileWriter(targetFile,true);//末尾
            Reader in=new FileReader(sourceFile);//指向源的输入流

            int n=-1;
            while ((n=in.read(c))!=-1){
                out.write(c,0,n);
            }
            out.flush();//可以在不关闭的情况下提前写入目的地

            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
