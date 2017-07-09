import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chengs on 17-4-4.
 */
public class ClientApplication {
    public static void main(String[] args) {
        try{
            Remote remoteObject= Naming.lookup("rmi://127.0.0.1/rect");
            RemoteSubject remoteSubject=(RemoteSubject)remoteObject;
            remoteSubject.setWidth(129);
            remoteSubject.setHeight(234);
            double area=remoteSubject.getArea();
            System.out.println("面积:"+area);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
