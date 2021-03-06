import java.rmi.*;
import java.rmi.server.*;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.List;

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

				if (documentId.equals(userId)) {
					userExists = 1;	
				}
			}
			myReader.close();

			this.updateCurrentBalance(userId, "1", 38.85);
			
			return userExists;
		} catch (Exception e) {
			System.out.println(e);
		}

		return userExists;		
	}


	@Override
	public int totalUserAccounts(String userId) throws RemoteException {
		int totalAccounts = 0;

		try {
			File myObj = new File("accounts.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine() && totalAccounts < 3) {
				String data = myReader.nextLine();

				String documentId = data.split(",")[0];

				if (documentId.equals(userId)) {
					totalAccounts++;
				}
			}
			myReader.close();
			
			return totalAccounts;
		} catch (Exception e) {
			System.out.println(e);
		}

		return totalAccounts;	
	}



	@Override
	public int authenticateUser(String username, String password) throws RemoteException {
		int userExists = 0;

		try {
			File myObj = new File("users.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine() && userExists == 0) {
				String data = myReader.nextLine();
				String user = data.split(",")[1];
				String psswd = data.split(",")[2];

				if (username.equals(user) && password.equals(psswd)) {
					userExists = 1;	
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
	public ArrayList<String> getUserAccounts(String userId) throws RemoteException, FileNotFoundException {
		
		ArrayList<String> accounts = new ArrayList<String>();

		try {
			File myObj = new File("accounts.txt");
			Scanner myReader = new Scanner(myObj);
			String[] currentAccount;			

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
				currentAccount = data.split(",");
				if((userId.toString()).equals(currentAccount[0])){
					accounts.add(currentAccount[1]);
				}
			}

			myReader.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return accounts;
	}

	@Override
	public String getAccountBalance(String id) throws RemoteException {
		
		Integer accountFound = 0;
		String balance = "";

		try {
			File myObj = new File("accounts.txt");
			Scanner myReader = new Scanner(myObj);
			String[] currentAccount;
			String[] currentTrans;

			while(myReader.hasNextLine() && accountFound == 0){
				String data = myReader.nextLine();
				currentAccount = data.split(",");
				if((id.toString()).equals(currentAccount[1])){
					balance += data + "/";
					accountFound = 1;
				}
			}			

			myReader.close();	
			Integer firstTrans = 1;		

			myObj = new File("deposits.txt");
			myReader = new Scanner(myObj);

			while(myReader.hasNextLine()){
				String data = myReader.nextLine();
				currentTrans = data.split(",");
				if(id.toString().equals(currentTrans[0])){
					if(firstTrans == 1){
						balance += data;
						firstTrans = 0;
					}
					else {
						balance += "," + data;
					}					
				};				
			}

			balance += "/";			
			myReader.close();
			firstTrans = 1;

			myObj = new File("transferences.txt");
			myReader = new Scanner(myObj);

			while(myReader.hasNextLine()){
				String data = myReader.nextLine();
				currentTrans = data.split(",");
				if(id.toString().equals(currentTrans[0])){
					if(firstTrans == 1){
						balance += data;
						firstTrans = 0;
					}
					else {
						balance += "," + data;
					}	
				};				
			}

			balance += "/";			
			myReader.close();
			firstTrans = 1;

			myObj = new File("withdrawals.txt");
			myReader = new Scanner(myObj);

			while(myReader.hasNextLine()){
				String data = myReader.nextLine();
				currentTrans = data.split(",");
				if(id.toString().equals(currentTrans[0])){
					if(firstTrans == 1){
						balance += data;
						firstTrans = 0;
					}
					else {
						balance += "," + data;
					}	
				};				
			}
			
			myReader.close();
			firstTrans = 1;

		} catch (Exception e) {
			System.out.println(e);			
		}

		return balance;
	}

	@Override
	public Integer validateUserAccount(String userId, String accountId) throws RemoteException {
		
		int accountExists = 0;

		try {
			File myObj = new File("accounts.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine() && accountExists == 0) {
				String data = myReader.nextLine();
				String accountNumber = data.split(",")[1];
				String user = data.split(",")[0];

				if (accountNumber.equals(accountId) && user.equals(userId)) {
					accountExists = 1;	
				}
			}
			myReader.close();
			
			return accountExists;
		} catch (Exception e) {
			System.out.println(e);
		}

		return accountExists;		
	}
	
	@Override
	public Integer thirdPartyAccountExists(String thirdPartyUserId, String thirdPartyAccountId)
			throws RemoteException {
		
		if(validateUserId(thirdPartyUserId) == 0){
			return 0;
		};

		if(validateUserAccount(thirdPartyUserId, thirdPartyAccountId) == 0){
			return 0;
		};

		return 1;
	}

	@Override
	public String getUserAccount(String accountId) throws RemoteException {
		
		String account = "";

		try {
			File myObj = new File("accounts.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine() && account.equals("")) {
				String data = myReader.nextLine();

				String id = data.split(",")[1];

				if (id.equals(accountId)) {
					account = data;
				}
			}
			myReader.close();
						
		} catch (Exception e) {
			System.out.println(e);
		}

		return account;
			
	}

	@Override
	public Integer deposit(String account, String transactionDescription, Double amount) throws RemoteException {
		
		String userAccount = getUserAccount(account);
		Double balance = Double.parseDouble(userAccount.split(",")[2]);
		System.out.println(this.getMaxTransactionId());
		Integer transactionId = this.getMaxTransactionId() + 1;
		balance += amount;

		try (
				FileWriter f = new FileWriter("deposits.txt", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);
			) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date currentDate = new Date();

			String depositInformation = String.format("%s,%s,%s,%s,%s", account, transactionId, amount, formatter.format(currentDate), transactionDescription);
			
			System.out.println(depositInformation);
			p.println(depositInformation);

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		
		return 1;
	}

	@Override
	public int withdrawal (String account, Integer transactionId, String transactionDescription, Double amount) throws RemoteException {
		
		if (transactionId == null) {
			transactionId = this.getMaxTransactionId() + 1;
		}

		try (
				FileWriter f = new FileWriter("withdrawals.txt", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);
			) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date currentDate = new Date();

			String withdrawalInformation = String.format("%s,%s,%s,%s,%s", account, transactionId, amount, formatter.format(currentDate), transactionDescription);
			
			System.out.println(withdrawalInformation);
			p.println(withdrawalInformation);

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		
		return 1;
	}

	@Override
	public int updateCurrentBalance (String userId, String accountId, Double amount) throws RemoteException {
		String accountInformation = "";
		String accountNewInformation = String.format("%s,%s,%s", userId, accountId, amount);

		try {
			File myObj = new File("accounts.txt");
			Scanner myReader = new Scanner(myObj);
			StringBuffer buffer = new StringBuffer();

			while (myReader.hasNextLine() && accountInformation.equals("")) {
				String data = myReader.nextLine();

				String accountNumber = data.split(",")[1];

				buffer.append(data + System.lineSeparator());

				if (accountNumber.equals(accountId)) {
					accountInformation = data;
				}
			}
			myReader.close();

			String accountFileContent = buffer.toString();
			accountFileContent = accountFileContent.replaceAll(accountInformation, accountNewInformation);

			FileWriter f = new FileWriter("accounts.txt", true);
			f.append(accountFileContent);
			f.flush();

		} catch(Exception e) {
			System.out.println(e);
			return 0;
		}

		/*try (
				FileWriter f = new FileWriter("accounts.txt", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);
			) {

			
			
			System.out.println(accountInformation);
			p.println(accountInformation);

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}*/
		
		return 1;
	}	
	

	@Override
	public int getMaxTransactionId() throws RemoteException {
		int transactionDepositId = 1;
		int transactionWithdrawalId = 1;
		int transactionTransferenceId = 1;

		try {
			File myObj = new File("deposits.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				transactionDepositId = Integer.parseInt(data.split(",")[1]);
			}
			myReader.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			File myObj = new File("withdrawals.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				transactionWithdrawalId = Integer.parseInt(data.split(",")[1]);
			}
			myReader.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			File myObj = new File("tranferences.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				transactionTransferenceId = Integer.parseInt(data.split(",")[1]);
			}
			myReader.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

		List<Integer> transactionIds = new ArrayList<Integer>();
		transactionIds.add(transactionDepositId);
		transactionIds.add(transactionWithdrawalId);
		transactionIds.add(transactionTransferenceId);

		return Collections.max(transactionIds);
	}
	
}