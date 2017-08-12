import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chengs on 17-4-4.
 */
public interface RemoteSubject extends Remote{
    public void setHeight(double height) throws RemoteException;
    public void setWidth(double width) throws RemoteException;
    public double getArea() throws RemoteException;
}
