import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by chengs on 17-4-4.
 */
public class BindRemoteObject {
    public static void main(String[] args) {
        try{
            RemoteConcreteSubject remoteConcreteSubject=new RemoteConcreteSubject();
            Naming.rebind("rmi://127.0.0.1/rect",remoteConcreteSubject);
            System.out.println("Be ready for client server...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
