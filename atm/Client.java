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

}