import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by chengs on 17-4-4.
 */
public class Example13_1 {
    public static void main(String[] args) {
        Scanner scanner;
        URL url;
        Thread readUrl;
        Look look=new Look();
        readUrl=new Thread(look);
        System.out.println("输入访问方式地址和资源,如:https://www.baidu.com");
        scanner=new Scanner(System.in);
        String source=scanner.nextLine();
        try{
            url=new URL(source);
            look.setUrl(url);
            readUrl.start();//开一个新线程读写网页,就可以避免主线程阻塞

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
class Look implements Runnable{
    URL url;

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public void run() {
        try{
            InputStream in=url.openStream();//在接受的过程中有可能阻塞,真实爬虫环境下必须进行考虑
            byte [] b=new byte[1024];
            int n=-1;
            while((n=in.read(b))!=-1){
                String str=new String(b,0,n);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}