import java.io.*;

/**
 * Created by chengs on 17-3-31.
 */

//对象序列化,与克隆
//未注释部分结合内存使用了字节数组流
//注释部分结合硬盘使用了文件流
public class Example10_13 {
    public static void main(String[] args) {
        TV changhong=new TV();
        changhong.setName("长虹电视");
        changhong.setPrice(5678);
        File file=new File("television.txt");
        try{
            FileOutputStream fos=new FileOutputStream(file);

            ByteArrayOutputStream aos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(aos);
            //ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(changhong);
            oos.close();

            FileInputStream fis=new FileInputStream(file);
            ByteArrayInputStream  ais=new ByteArrayInputStream(aos.toByteArray());//注意此处是aos
            ObjectInputStream ois=new ObjectInputStream(ais);
            //ObjectInputStream ois=new ObjectInputStream(fis);
            TV xinfei=(TV)ois.readObject();
            ois.close();
            System.out.println(xinfei.getName());
            xinfei.setName("新飞电视");
            xinfei.setPrice(4444);
            System.out.println(xinfei.getName());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class TV implements Serializable {
    String name;
    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}