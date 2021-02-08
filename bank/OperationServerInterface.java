import java.io.FileNotFoundException;
import java.rmi.*;
import java.util.ArrayList;
public interface OperationServerInterface extends Remote
{
    public int validateUserId(String userId) throws RemoteException;
    public int totalUserAccounts(String userId) throws RemoteException;
    public int authenticateUser(String username, String password) throws RemoteException;
    public String creatAccount(String username, double deposit) throws RemoteException;
    public int createUser(String name, String username, String password) throws RemoteException;
    public ArrayList<String> getUserAccounts(Integer userId) throws RemoteException, FileNotFoundException;
    public String getAccountBalance(Integer id) throws RemoteException;
}