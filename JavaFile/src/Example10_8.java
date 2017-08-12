import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Created by chengs on 17-3-30.
 */
//RandomAccessFile 随机流,既可做输入流,又可做输出流,对文件同时进行读写操作时用
public class Example10_8 {
    public static void main(String[] args) {
        RandomAccessFile inAndOut=null;
        int data[]={1,2,3,4,5,6,7,8,9,10};
        try{
            inAndOut=new RandomAccessFile("tom.dat","rw");
            for (int i=0;i<data.length;i++){
                inAndOut.writeInt(data[i]);
            }
            for(long i=data.length-1;i>=0;i--){
                //一个int型数据占4个字节,inAndOut从文件的第36个字节读取出最后面的一个整数
                inAndOut.seek(i*4);
                //定位到某个字节位置
                System.out.printf("\t%d",inAndOut.readInt());
            }
            inAndOut.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
