package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SinhVienDAO;
import pojo.SinhVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void loginGiaoVu() {
		file f = new file();
		f.setVisible(true);
		this.setVisible(false);
	}
	private void loginSV(String mssv) {
		sinhvien f = new sinhvien(mssv);
		f.setVisible(true);
		this.setVisible(false);
	}
	private static boolean isPasswordCorrect(char[] input) {
	    boolean isCorrect = true;
	    char[] correctPassword = { 'g', 'i', 'a', 'o', 'v', 'u' };

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
	public static boolean isPasswordCorrectSV(char[] input,String pass) {
	    boolean isCorrect = true;
	    char[] correctPassword = new char[20];
	    for(int i=0; i<pass.length();i++){  
	    	correctPassword[i] = pass.charAt(i);  
	}   
	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(71, 46, 272, 151);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ten dang nhap");
		lblNewLabel.setBounds(10, 39, 83, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mat khau");
		lblNewLabel_1.setBounds(10, 67, 49, 14);
		panel.add(lblNewLabel_1);
		
		txtUser = new JTextField();
		txtUser.setBounds(103, 36, 159, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnNewButton = new JButton("Dang nhap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 char[] input = txtPass.getPassword();
				 boolean laSV= false;
				 String username = null,password = null;
				 
				 List<SinhVien> list = SinhVienDAO.xemDanhSachTatCaSinhVien();
				 for(SinhVien sv: list) {
					 username = sv.getUsername();
					 password = sv.getPassword();
					 if(txtUser.getText().compareTo(username)==0 && isPasswordCorrectSV(input,password))
					 {
						 laSV = true;
						 break;
					 }
				 }
				 if(txtUser.getText().compareTo("giaovu")==0 && isPasswordCorrect(input))
				{
					 loginGiaoVu();
				}
				 else if(laSV){
					 loginSV(username);
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Sai ten dang nhap hoac mat khau");

				 }
			}
		});
		btnNewButton.setBounds(103, 106, 89, 23);
		panel.add(btnNewButton);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(103, 64, 159, 20);
		panel.add(txtPass);
		
		JButton btnNewButton_1 = new JButton("Doi mat khau");
		btnNewButton_1.setBounds(4, 106, 89, 23);
		panel.add(btnNewButton_1);
	}
}
