import java.rmi.*;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;

public class Client implements OperationServerInterface{
  OperationServerInterface bankServer;
	Client() {
		try{
			Registry registry = LocateRegistry.getRegistry(null); 
			bankServer = (OperationServerInterface) registry.lookup("BANK");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public int validateUserId(String userId) throws RemoteException {
		int response = bankServer.validateUserId(userId);
		return response;
	}

	@Override
	public int totalUserAccounts(String userId) throws RemoteException {
		int response = bankServer.totalUserAccounts(userId);
		return response;
	}

	@Override
	public int authenticateUser(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		int response = bankServer.authenticateUser(username, password);
		return response;
	}

	@Override
	public String creatAccount(String username, double deposit) throws RemoteException {
		// TODO Auto-generated method stub
		return "343434-34";
	}

	@Override
	public int createUser(String name, String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}