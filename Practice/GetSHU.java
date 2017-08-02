import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileOutputStream;
import java.io.*;

/**
 * Created by chengs on 17-7-24.
 */
public class Get {
    static Set<String> set=new HashSet<String>();
    public static void main(String[] args) throws IOException {
        String res;
        FileOutputStream out= new FileOutputStream(new File("tes.txt"));
        for(int i=10000001;i<20000000;i++){
            if((res=getInfo(Integer.toString(i)))!=null){
                System.out.println(res);
            }else{
                set.forEach(abc -> {
                    try {
                        out.write((abc+"\n").getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                return;
            }
        }
    }

    public  static  String getInfo(String userId){
        try {
            String xmlFile = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
                    "<soap:Body>"+
                    "<GetStudentInfo xmlns=\"www.lib.shu.edu.cn\">"+
                    "<UserID>"+userId+"</UserID>"+
                    "</GetStudentInfo>"+
                    "</soap:Body>"+
                    "</soap:Envelope>";
            String urlStr= "http://202.120.121.204:8888/WebService/shulibLogin.asmx?WSDL";

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setDoOutput(true);    // 可以发送数据
            conn.setDoInput(true);    // 可以接收数据
            conn.setRequestMethod("POST");    // POST方法

            conn.connect();
            //    写入的POST数据
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(xmlFile);
            osw.flush();
            osw.close();
            // 读取响应数据
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String responseStr="";
            String tempStr;
            String result=userId;
            while((tempStr=in.readLine())!=null){
                responseStr=responseStr+tempStr;
            }
            int site1=responseStr.indexOf("<GetStudentInfoResult><string>");
            String sub1=responseStr.substring(site1+30);
            //System.out.println(sub1);

            int site11=sub1.indexOf("</string>");
            result+="/";
            result+=sub1.substring(0,site11);

            int site2=sub1.indexOf("<string>");
            String sub2=sub1.substring(site2+8);
            int site22=sub2.indexOf("</string>");
            result+="/";
            result+=sub2.substring(0,site22);

            int site3=sub2.indexOf("<string>");
            String sub3=sub2.substring(site3+8);
            int site33=sub3.indexOf("</string>");
            result+="/";
            result+=sub3.substring(0,site33);
            set.add(sub3.substring(0,site33));
            return result;
        }
        catch (Exception e) {
            return null;
        }
    }

}
