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
    public ArrayList<String> getUserAccounts(String userId) throws RemoteException, FileNotFoundException;
    public String getAccountBalance(String id) throws RemoteException;
    public Integer validateUserAccount(String userId, String accountId) throws RemoteException;
    public Integer thirdPartyAccountExists(String thirdPartyUserId, String thirdPartyAccountId) throws RemoteException;
    public String getUserAccount(String accountId) throws RemoteException;
    public Integer deposit(String account, Double amount) throws RemoteException;
}