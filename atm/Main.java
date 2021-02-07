class Main {
  public static void main(String[] args) {
    System.out.println("Starting client...");
    Client client = new Client();
		ATM atm = new ATM(client);
  }
}