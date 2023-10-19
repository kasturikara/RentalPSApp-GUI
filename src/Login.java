import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
  private JPanel loginPanel;
  private JTextField inputAdminLogin;
  private JPasswordField inputPassLogin;
  private JButton loginBtn;
  private JButton clearBtn;
  private JLabel loginLabel;
  private JLabel inputAdminLabel;
  private JLabel inputPassLabel;

  public Login() {
    setContentPane(loginPanel);
    setTitle("APLIKASI RENTAL PS");
    setSize(300,200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setFont(new Font("Poppins", Font.PLAIN, 14));
    //loginbtn
    loginBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String namaAdmin = inputAdminLogin.getText();
        char[] pass = inputPassLogin.getPassword();
        if (namaAdmin.isEmpty() || pass.length == 0) {
          JOptionPane.showMessageDialog(null, "Isi data dengan lengkap!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }else {
          MainFrame menu = new MainFrame();
          menu.setVisible(true);
          Login.this.dispose();
          JOptionPane.showMessageDialog(null, "Selamat datang " + inputAdminLogin.getText() + " ^-^", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    //clearbtn
    clearBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        inputAdminLogin.setText("");
        inputPassLogin.setText("");
      }
    });
  }

  public static void main(String[] args) {

    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Login().setVisible(true);
      }
    });
  }
}

