import java.io.*;

/**
 * Created by chengs on 17-3-31.
 */

//使用计算机内存作为流的源和目的地

public class Example10_10 {
    public static void main(String[] args) {
        try{
            ByteArrayOutputStream outByte=new ByteArrayOutputStream();
            byte[]byteContent=" mid-autumn festival 百q度 ".getBytes();

            outByte.write(byteContent);

            ByteArrayInputStream inByte=new ByteArrayInputStream(outByte.toByteArray());//直接构造输入流
            byte []backBytes=new byte[outByte.toByteArray().length];
            inByte.read(backBytes);//将数据输入到数组

            System.out.println(new String(backBytes));
            //用同样的办法可以创建字符数组流
            CharArrayWriter outChar=new CharArrayWriter();
            char []charContent="中秋快乐".toCharArray();
            outChar.write(charContent);
            CharArrayReader inChar=new CharArrayReader(outChar.toCharArray());
            char[]backChars=new char[outChar.toCharArray().length];
            inChar.read(backChars);
            System.out.println(new String(backChars));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
