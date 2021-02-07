import java.rmi.*;
public interface OperationServerInterface extends Remote
{
public int validateUserId(String userId) throws RemoteException;
}