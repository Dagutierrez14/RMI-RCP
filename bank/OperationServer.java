import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class OperationServer {
	public static void main(String args[]) {
		try {
			OperationServerInterface addService = new Bank();
			// Exporting the object of implementation class
			// (here we are exporting the remote object to the stub)
			OperationServerInterface stub = (OperationServerInterface) UnicastRemoteObject.exportObject(addService, 0);

			// Binding the remote object (stub) in the registry
			Registry registry = LocateRegistry.getRegistry();

			registry.rebind("BANK", stub);

			System.err.println("Server ready");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}