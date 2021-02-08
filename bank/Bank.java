import java.rmi.*;
import java.rmi.server.*;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

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
		int userExists = 0;

		try {
			File myObj = new File("users.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine() && userExists == 0) {
				String data = myReader.nextLine();

				String documentId = data.split(",")[0];

				if (documentId == userId) {
					userExists = 1;	
					System.out.println(data);
				}
			}
			myReader.close();
			
			return userExists;
		} catch (Exception e) {
			System.out.println(e);
		}

		return userExists;		
	}


	@Override
	public int totalUserAccounts(String userId) throws RemoteException {
		return 3;
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

	@Override
	public ArrayList<String> getUserAccounts(Integer userId) throws RemoteException, FileNotFoundException {
		
		File myObj = new File("accounts.txt");
		Scanner myReader = new Scanner(myObj);
		String[] currentAccount;
		ArrayList<String> accounts = new ArrayList<String>();

		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			System.out.println(data);
			currentAccount = data.split(",");
			if((userId.toString()).equals(currentAccount[0])){
				accounts.add(currentAccount[1]);
			}
		}

		myReader.close();

		return accounts;
	}
	
	
}