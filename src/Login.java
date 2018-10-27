import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login {
	JFrame frame;
	JPanel panel;
	JLabel idlabel, passlabel;
	JTextField textField, passwordField;
	
	private String userid;
	private String pwd;
    public String username;
    public String email;
    
	
	
	public Login() {
			
		frame = new JFrame("User Information");
		panel = new JPanel();
		idlabel = new JLabel("Enter Username: ");
		passlabel = new JLabel("Enter Password: ");
		JLabel lblLogin = new JLabel("Login Here");
		panel.setLayout(null);
		idlabel.setForeground(Color.WHITE);
		idlabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		idlabel.setBounds(27, 63, 181, 30);
		passlabel.setForeground(Color.WHITE);
		passlabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		passlabel.setBounds(27, 133, 181, 30);
		lblLogin.setBackground(Color.DARK_GRAY);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Ebrima", Font.BOLD, 22));
		lblLogin.setBounds(10, 4, 564, 45);
		textField = new JTextField();
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		textField.setForeground(Color.WHITE);
		textField.setBounds(244, 66, 304, 30);
		textField.setBackground(new Color(108,122,137));
		panel.add(textField);
		textField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(244, 133, 304, 30);
		passwordField.setBackground(new Color(108,122,137));
		panel.add(passwordField);
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBackground(new Color(54,185,255));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBounds(10, 210, 250, 45);
		JButton btnRegister = new JButton("Don't Have An Account ? Register Here ...");
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(254,50,40));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegister.setBounds(270, 210, 304, 45);
		Icon icon = new ImageIcon("game.png");
		JLabel gameimage = new JLabel("");
		gameimage.setHorizontalAlignment(SwingConstants.CENTER);
		gameimage.setIcon(icon);
		gameimage.setBounds(10, 291, 564, 285);
		/*JLabel gameimage = new JLabel();
		gameimage.setBounds(10, 291, 564, 285);
		ImageIcon img = new ImageIcon("game.png");
		 Image im = img.getImage();
		 Image resize = im.getScaledInstance(564, 285, Image.SCALE_FAST);
		 ImageIcon newimage = new ImageIcon(resize);
		 gameimage.setIcon(newimage);*/
		
		
		
		frame.setSize(600, 700);
		panel.setBounds(0,0,600,700);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(panel);
		panel.setBackground(new Color(44,62,80));
		panel.add(idlabel);
		panel.add(passlabel);
		panel.add(lblLogin);
		panel.add(btnRegister);
		panel.add(btnLogin);
		panel.add(gameimage);
		
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    doRegister();
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doLogin();
				
			}
		});

	}
	public static Connection getConnnection() throws SQLException {
		Connection connection = null;
		String dburl = "jdbc:sqlite:C:\\databases\\project.db";
		connection = DriverManager.getConnection(dburl);
		System.out.println("Connection Created ");
		return connection ;
	}
	private void doRegister() {
		Register register = new Register();
		frame.setVisible(false);
		register.frame.setVisible(true);
	}
	
	
	private void doLogin() {
		Connection con = null ;
		ResultSet rs = null ;
		PreparedStatement pstmt = null ;
		userid = textField.getText();
		pwd = passwordField.getText();
		try {
			con = getConnnection() ;
			boolean isAutherised = false ;
			pstmt = con.prepareStatement("SELECT * FROM user WHERE email=? AND password = ?");
			pstmt.setString(1, userid);
			pstmt.setString(2,pwd);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				username = rs.getString("name");
				email = rs.getString("email");
				isAutherised = true;
				frame.setVisible(false);
				GamePanel gamepanel = new GamePanel(email, username);
				//JOptionPane.showMessageDialog(new JFrame(), "Authenticated! You are now logged In", "Game Zone", JOptionPane.OK_OPTION);
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Inavlid Username or Password", "Invalid", JOptionPane.ERROR_MESSAGE);
			}
		//	con.close();
			rs.close();
			pstmt.close();



			
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		
		
	}


	 
	/*public static void main(String[] args)  {
		Login login = new Login();
		
		
		
	}*/

}
