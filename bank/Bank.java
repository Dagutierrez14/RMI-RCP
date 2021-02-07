import java.rmi.*;
import java.rmi.server.*;

public class Bank implements OperationServerInterface {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Bank() throws RemoteException {
		super();
	}



	@Override
	public int validateUserId(String userId) throws RemoteException {
		return 0;
	}

	@Override
	public int authenticateUser(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String creatAccount(String username, double deposit) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createUser(String name, String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}