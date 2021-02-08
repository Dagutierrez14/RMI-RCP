import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

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

			File myObj = new File("users.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}