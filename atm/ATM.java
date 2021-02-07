import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class ATM {
  JFrame f;
  Client atmClient;

  ATM(Client client) {
    atmClient = client;
    f = new JFrame();// creating instance of JFrame

    mainMenu();// Drawing main menu view

    f.setSize(600, 700);// 600 width and 700 height
    f.setLayout(null);// using no layout managers
    f.setVisible(true);// making the frame visible
  }



  public void mainMenu() {
    clear();
    // Titles
    JLabel welcomeLabel, menuLabel;
    welcomeLabel = new JLabel("Bienvenido a UCAB - ATM");
    welcomeLabel.setBounds(225, 50, 300, 30);

    menuLabel = new JLabel("Men\u00fa principal");
    menuLabel.setBounds(255, 100, 300, 30);

    // Options
    JButton option1 = new JButton("Apertura de Cuenta");
    option1.setBounds(150, 200, 300, 40);
    option1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("here...");
        requestId();
        System.out.println("end...");
      }
    });

    JButton option2 = new JButton("Realizar transacci\u00f3n");
    option2.setBounds(150, 250, 300, 40);

    // Adding contents to the screen
    f.add(welcomeLabel);
    f.add(menuLabel);
    f.add(option1);
    f.add(option2);

    repaint();
  }

  public void requestId() {
    clear();
    // Titles
    JLabel sectionTitle, actionLabel;
    sectionTitle = new JLabel("Apertura de cuenta");
    sectionTitle.setBounds(240, 50, 300, 30);

    actionLabel = new JLabel("Introduce tu ID");
    actionLabel.setBounds(255, 100, 300, 30);

    JTextField idField;
    idField = new JTextField("");
    idField.setBounds(150, 200, 300, 40);

    JButton next = new JButton("Siguiente");
    next.setBounds(150, 300, 300, 40);
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String userId = idField.getText();
        int response;
        try {
          response = atmClient.validateUserId(userId);
          if (response == 0) {
            newUserForm();
          } else {
            if (response == 1) {
              errorMessage("Tiene abierta m\u00e1s de una cuenta", 170);
            } else {
              if (response == 2) {
                newUserForm();
              }
            }
          }
        } catch (RemoteException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

      }
    });

    JButton goBack = new JButton("Volver");
    goBack.setBounds(150, 350, 300, 40);
    goBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mainMenu();
      }
    });

    f.add(sectionTitle);
    f.add(actionLabel);
    f.add(idField);
    f.add(next);
    f.add(goBack);
    repaint();
  }

  public void newUserForm() {
    clear();
    // Titles
    JLabel sectionTitle, actionLabel, nameLabel, usernameLabel, passwordLabel;
    sectionTitle = new JLabel("Apertura de cuenta");
    sectionTitle.setBounds(240, 50, 300, 30);

    actionLabel = new JLabel("Nuevo usuario");
    actionLabel.setBounds(255, 100, 300, 30);

    nameLabel = new JLabel("Nombre");
    nameLabel.setBounds(50, 150, 225, 30);
    JTextField nameField;
    nameField = new JTextField("");
    nameField.setBounds(50, 175, 225, 40);

    usernameLabel = new JLabel("Usuario");
    usernameLabel.setBounds(325, 150, 225, 30);
    JTextField usernameField;
    usernameField = new JTextField("");
    usernameField.setBounds(325, 175, 225, 40);

    passwordLabel = new JLabel("Contrase\u00f1a");
    passwordLabel.setBounds(50, 225, 225, 30);
    JTextField passwordField;
    passwordField = new JTextField("");
    passwordField.setBounds(50, 250, 225, 40);

    JButton next = new JButton("Siguiente");
    next.setBounds(150, 400, 300, 40);

    JButton goBack = new JButton("Volver");
    goBack.setBounds(150, 450, 300, 40);
    goBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mainMenu();
      }
    });

    // Adding contents to the screen
    f.add(sectionTitle);
    f.add(actionLabel);
    f.add(nameLabel);
    f.add(nameField);
    f.add(usernameLabel);
    f.add(usernameField);
    f.add(passwordLabel);
    f.add(passwordField);
    f.add(next);
    f.add(goBack);

    repaint();

  }

  public void loggingForm() {
    clear();
    // Titles
    JLabel sectionTitle, actionLabel, usernameLabel, passwordLabel;
    sectionTitle = new JLabel("Apertura de cuenta");
    sectionTitle.setBounds(240, 50, 300, 30);

    actionLabel = new JLabel("Autenticaci\u00f3n");
    actionLabel.setBounds(255, 100, 300, 30);

    usernameLabel = new JLabel("Usuario");
    usernameLabel.setBounds(190, 150, 225, 30);
    JTextField usernameField;
    usernameField = new JTextField("");
    usernameField.setBounds(190, 175, 225, 40);

    passwordLabel = new JLabel("Contrase\u00f1a");
    passwordLabel.setBounds(190, 225, 225, 30);
    JTextField passwordField;
    passwordField = new JTextField("");
    passwordField.setBounds(190, 250, 225, 40);

    JButton next = new JButton("Siguiente");
    next.setBounds(150, 400, 300, 40);

    JButton goBack = new JButton("Volver");
    goBack.setBounds(150, 450, 300, 40);
    goBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mainMenu();
      }
    });

    // Adding contents to the screen
    f.add(sectionTitle);
    f.add(actionLabel);
    f.add(usernameLabel);
    f.add(usernameField);
    f.add(passwordLabel);
    f.add(passwordField);
    f.add(next);
    f.add(goBack);

    repaint();

  }

  public void errorMessage(String message, int x) {
    clear();
    // Titles
    JLabel errorLabel, loadingLabel;
    errorLabel = new JLabel(message);
    errorLabel.setBounds(x, 50, 300, 30);

    loadingLabel = new JLabel("Volviendo al men\u00fa principal en 3");
    loadingLabel.setBounds(172, 100, 300, 30);

    // Adding contents to the screen
    f.add(errorLabel);
    f.add(loadingLabel);

    repaint();

    for (int i = 2; i >= 0; i--) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      f.getContentPane().remove(loadingLabel);
      loadingLabel = new JLabel("Volviendo al men\u00fa principal en " + i);
      loadingLabel.setBounds(172, 100, 300, 30);
      f.add(loadingLabel);
      repaint();
    }

    mainMenu();

  }

  public void clear() {
    f.getContentPane().removeAll();
    f.getContentPane().revalidate();
  }

  public void repaint() {
    f.getContentPane().repaint();
  }
}