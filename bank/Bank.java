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
	
}