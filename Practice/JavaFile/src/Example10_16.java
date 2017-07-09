import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by chengs on 17-3-31.
 */
public class Example10_16 {
    public static void main(String[] args) {
        File file=new File("baidu.txt");
        Scanner sc=null;
        int count=0;
        double sum=0;
        double score=0;
        try{
            sc=new Scanner(file);
            sc.useDelimiter("[^0-9.]+");//以所有非0-9,非0,非空格
            while(sc.hasNextDouble()){
                score=sc.nextDouble();
                count++;
                sum=sum+score;
                System.out.println(score);
            }
            System.out.println("sum:"+sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
