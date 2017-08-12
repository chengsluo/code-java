import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by chengs on 17-4-4.
 */
public class RemoteConcreteSubject extends UnicastRemoteObject implements RemoteSubject{
    double width,height;
    public RemoteConcreteSubject() throws RemoteException{}

    @Override
    public void setHeight(double height) throws RemoteException {
        this.height=height;
    }

    @Override
    public void setWidth(double width) throws RemoteException {
        this.width=width;
    }

    @Override
    public double getArea() throws RemoteException {
        return width*height;
    }
}
