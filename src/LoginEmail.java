import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginEmail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JLabel lblImage;
	private JButton btnEmail;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginEmail frame = new LoginEmail();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginEmail() {
		setTitle("Email");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 223, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocation(700,300);
		setContentPane(contentPane);
		contentPane.setLayout(null);


		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, ١٨));
		lblEmail.setBounds(10, 95, 65, 44);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(71, 103, 117, 35);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("images\\email2.png"));
		lblImage.setBounds(71, 10, 117, 62);
		contentPane.add(lblImage);

		btnEmail = new JButton("Login");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str= txtEmail.getText();	
				CheckEmail(str);
			}
		});
		txtEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str= txtEmail.getText();	
				CheckEmail(str);
			}
		});
		btnEmail.setIcon(new ImageIcon("images\\Add.png"));
		btnEmail.setFont(new Font("Tahoma", Font.BOLD, ٢٠));
		btnEmail.setBounds(40, 184, 148, 44);
		contentPane.add(btnEmail);
		validate();

	}
	public boolean CheckEmail(String str) {
	    boolean isValid = true;

	    if (str == null || str.isEmpty() || str.contains(" ")) {
	        isValid = false;

	    } else {
	        int CheckAt = str.indexOf('@');
	        int CheckDot = str.indexOf('.', CheckAt);

	        if (CheckAt <= 0 || CheckDot <= CheckAt + 1 || CheckDot >= str.length() - 1) {
	            isValid = false;
	        } else if (str.indexOf('@') != str.lastIndexOf('@') || str.contains("@@") || str.contains("..")) {
	            isValid = false;
	        } else if (str.startsWith("@") || str.startsWith(".") || str.endsWith("@") || str.endsWith(".")) {
	            isValid = false;
	        } else {
	            String firstPart = str.substring(0, CheckAt);
	            String secondPart = str.substring(CheckAt + 1, CheckDot);
	            String finallyPart = str.substring(CheckDot + 1);

	            if (firstPart.isEmpty() || secondPart.isEmpty() || finallyPart.isEmpty()) {
	                isValid = false;
	            }
	        }
	    }

	    if (!isValid) {
	    	txtEmail.setText("");
	        JOptionPane.showMessageDialog(LoginEmail.this, "please enter isValid email", "InValid Email", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    else {
	    new TheCalculator().setVisible(true);
	    LoginEmail.this.setVisible(false);
	    return true;
	    }	
	    
	
	}

}
