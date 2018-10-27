import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import brickbreaker.Brick;

public class Register {
	JFrame frame;
	String user;
	JPanel panel;
	JTextField userNameField, userIdField;
	JPasswordField passwordField,confirmPasswordField;
	Icon icon = new ImageIcon("key.png");

	
	public Register() {
			
		frame = new JFrame("Sign Up");
		panel = new JPanel();
		userNameField = new JTextField();
        userIdField = new JTextField();
        passwordField = new JPasswordField();
		frame.setSize(600, 700);
		panel.setBounds(0,0,600,700);
		panel.setLayout(null);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		panel.setBackground(new Color(44,62,80));
		JLabel lblUserName = new JLabel(" User Name  :           ");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblUserName.setBounds(27, 63, 207, 30);
		JLabel lblUserid = new JLabel(" Enter User ID  :       ");
		lblUserid.setForeground(Color.WHITE);
		lblUserid.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblUserid.setBounds(27, 133, 194, 30);
		JLabel lblPassword = new JLabel("Enter Password :       ");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblPassword.setBounds(27, 203, 194, 30);
		JLabel lblConfirmPassword = new JLabel(" Confirm Password :       ");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblConfirmPassword.setBounds(27, 273, 194, 30);
		JLabel lblRegister = new JLabel("Register Here");
		lblRegister.setBackground(Color.WHITE);
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setFont(new Font("Ebrima", Font.BOLD, 22));
		lblRegister.setBounds(10, 4, 564, 45);
		userNameField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		userNameField.setForeground(Color.WHITE);
		userNameField.setBackground(new Color(108,122,137));
		userNameField.setBounds(244, 66, 304, 30);
		userNameField.setColumns(10);
		userIdField.setForeground(Color.WHITE);
		userIdField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		userIdField.setBackground(new Color(108,122,137));
		userIdField.setBounds(244, 133, 304, 30);
		userIdField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		passwordField.setBackground(new Color(108,122,137));
		passwordField.setBounds(244, 200, 304, 30);
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setForeground(Color.WHITE);
		confirmPasswordField.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		confirmPasswordField.setBackground(new Color(108,122,137));
		confirmPasswordField.setBounds(244, 267, 304, 30);
		JButton btnRegister = new JButton("Register Here");
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegister.setBackground(new Color(54,185,255));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBounds(10, 332, 263, 45);
		JButton btnLogin = new JButton("Login Or Play As Guest ");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(254,50,40));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(283, 332, 291, 45);
		Icon registerImage = new ImageIcon("registerImage.png");
		JLabel registerImagelbl = new JLabel("");
		registerImagelbl.setBounds(10, 406, 564, 233);
		registerImagelbl.setIcon(registerImage);

		
		frame.add(panel);
		panel.add(lblUserName);
		panel.add(lblUserid);
		panel.add(lblPassword);
		panel.add(lblConfirmPassword);
		panel.add(lblRegister);
		panel.add(userNameField);
		panel.add(passwordField);
		panel.add(userIdField);
		panel.add(confirmPasswordField);
		panel.add(btnRegister);
		panel.add(btnLogin);
		panel.add(registerImagelbl);
		frame.setVisible(true);
		
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
	
	
	
	public static void main(String[] args) {
		Register rg = new Register();
		
	}
    
	private void doRegister() {
		String userName = userNameField.getText();
		String userId = userIdField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();

		System.out.println("userId "+userId);
		System.out.println("userName "+userName);
		System.out.println("password "+password);
		System.out.println("confirm password "+confirmPassword);

		Connection con = null ;
		int records = 0 ;
		PreparedStatement pstmt = null ;
		PreparedStatement pstmt1 = null ;
		ResultSet rs =  null;
		

		try {
			con = Login.getConnnection() ;
			// DB NAME - game , USER TABLE NAME - USER_MST
			if(con != null) {
				System.out.println("con is "+con);	

				if(!userName.isEmpty() && !userId.isEmpty()) {
					if(!password.isEmpty() && !confirmPassword.isEmpty()) {
						if(!userId.equals(userName) && !userId.equals(password)) {
							if(!password.isEmpty() && password.equals(confirmPassword)) {
								pstmt1 = con.prepareStatement("SELECT * FROM user WHERE email=?");
								pstmt1.setString(1, userId);
								rs = pstmt1.executeQuery();
								if (rs.next()) {
									JOptionPane.showMessageDialog(new JFrame(),"Sorry Username Already Taken " , "Game Zone", JOptionPane.ERROR_MESSAGE);
								}
								else {
								pstmt = con.prepareStatement("INSERT INTO user(name, email, password,snakescore,brickscore,spacescore,flappyscore) VALUES(?, ?, ?, ?,?, ?, ?)");
								pstmt.setString(1, userName);
								pstmt.setString(2,userId);
								pstmt.setString(3,password);
								pstmt.setInt(4, 0);
								pstmt.setInt(5, 0);
								pstmt.setInt(6, 0);
								pstmt.setInt(7, 0);
								records = pstmt.executeUpdate() ;
								System.out.println("User Registered Successfully");
								GamePanel gamepanel = new GamePanel(userId, userName);
								frame.setVisible(false);
							}
							}
							else {
								JOptionPane.showMessageDialog(new JFrame(),"Password And ConfirmPassword Should be Same " , "Game Zone", JOptionPane.ERROR_MESSAGE);
							}
						}
						else if(userId.equals(userName) && !userId.equals(password)) {
							JOptionPane.showMessageDialog(new JFrame(),"UserId and userName can't be Same" , "Game Zone", JOptionPane.ERROR_MESSAGE);
						}
						else if(!userId.equals(userName) && userId.equals(password)) {
							JOptionPane.showMessageDialog(new JFrame(), "UserId and Passord can't be Same" , "Game Zone", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(),"UserId , UserName And Password Can't be Same " , "Game Zone", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(password.isEmpty() && !confirmPassword.isEmpty()) {
						JOptionPane.showMessageDialog(new JFrame(),"Enter Password" , "Game Zone", JOptionPane.ERROR_MESSAGE);
					}
					else if(!password.isEmpty() && confirmPassword.isEmpty()) {
						JOptionPane.showMessageDialog(new JFrame(),"Enter ConfirmPassword" , "Game Zone", JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(),"All Fields Are Manditory" , "Game Zone", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(userName.isEmpty() && !userId.isEmpty()){
					JOptionPane.showMessageDialog(new JFrame(),"Enter UserName" , "Game Zone", JOptionPane.ERROR_MESSAGE);
				}
				else if(!userName.isEmpty() && userId.isEmpty()){
					JOptionPane.showMessageDialog(new JFrame(),"Enter UserId" , "Game Zone", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(),"Enter UserId And userName " , "Game Zone", JOptionPane.ERROR_MESSAGE);
				}

				if(records > 0) {
					System.out.println("Record inserted ");
				}

				
			}
		}
		catch (SQLException e) {
			System.out.println("exception e"+e);
		}

	}
	private void doLogin() {
		Login loginScreen = new Login();
		loginScreen.frame.setVisible(true);
		frame.setVisible(false);
	}
}
