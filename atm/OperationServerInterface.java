import java.rmi.*;
public interface OperationServerInterface extends Remote
{
    public int validateUserId(String userId) throws RemoteException;
    public int totalUserAccounts(String userId) throws RemoteException;
    public int authenticateUser(String username, String password) throws RemoteException;
    public String creatAccount(String username, double deposit) throws RemoteException;
    public int createUser(String name, String username, String password) throws RemoteException;
    // public Integer deposit(String account, String transactionDescription, Double amount) throws RemoteException;
    // public int getMaxTransactionId() throws RemoteException;
    // public int updateCurrentBalance (String userId, String account, Double amount) throws RemoteException;
}