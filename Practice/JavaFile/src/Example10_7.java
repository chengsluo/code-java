import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by chengs on 17-3-30.
 */
//缓冲流
//提供指定读写某行的可能性,可以结合字符流使用
public class Example10_7 {
    public static void main(String[] args) {
        File fRead =new File("baidu.txt");
        File fWrite=new File("englishCount.txt");
        try {
            Writer out=new FileWriter(fWrite);
            BufferedWriter bufferedWriter=new BufferedWriter(out);
            Reader in=new FileReader(fRead);
            BufferedReader bufferedReader=new BufferedReader(in);
            String str=null;

            while ((str=bufferedReader.readLine())!=null){
                //StringTokenizer可以将s分割,如下是利用默认的分隔符,空格|换行|回车|tab|进纸符
                StringTokenizer analysis=new StringTokenizer(str);
                //StringTokenizer(str,",;.:");这是自定义分割符的写法
                int count=analysis.countTokens();
                //统计被分割成了多少块
                str=str+" 句子中单词个数:"+count;
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                //写入一行后要换行
            }
            //先关闭缓冲流,再关闭字符流
            bufferedWriter.close();
            out.close();

            in=new FileReader(fWrite);
            bufferedReader=new BufferedReader(in);
            String s=null;
            System.out.println(fWrite.getName()+" 内容");
            while((s=bufferedReader.readLine())!=null){
                System.out.println(s);
            }
            bufferedReader.close();
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
