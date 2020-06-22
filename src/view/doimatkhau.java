package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SinhVienDAO;
import pojo.SinhVien;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class doimatkhau extends JFrame {

	private JPanel contentPane;
	private JTextField txtNew;
	private JTextField txtUser;
	private JPasswordField txtOld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doimatkhau frame = new doimatkhau();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void btnDangXuat() {
		login f = new login();
		f.setVisible(true);
		this.setVisible(false);
	}
	public void doiMK(String mssv,String newpass){
		SinhVienDAO.doiMatKhau(Integer.parseInt(mssv), newpass);
	}
	/**
	 * Create the frame.
	 */
	public doimatkhau() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtNew = new JTextField();
		txtNew.setBounds(125, 111, 216, 20);
		panel.add(txtNew);
		txtNew.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("mat khau cu");
		lblNewLabel.setBounds(10, 83, 115, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("mat khau moi");
		lblNewLabel_1.setBounds(10, 114, 115, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Doi mat khau");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] input = txtOld.getPassword();
				 boolean laSV= false;
				 String username = null,password = null;
				 
				 List<SinhVien> list = SinhVienDAO.xemDanhSachTatCaSinhVien();
				 for(SinhVien sv: list) {
					 username = sv.getUsername();
					 password = sv.getPassword();
					 if(txtUser.getText().compareTo(username)==0 && login.isPasswordCorrectSV(input,password))
					 {
						 laSV = true;
						 break;
					 }
				 }
				 if(laSV)
				 {
					 doiMK(username,txtNew.getText());
					 JOptionPane.showMessageDialog(null, "Doi mat khau thanh cong");
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Sai ten dang nhap hoac mat khau");
				 }
			}
		});
		btnNewButton.setBounds(125, 185, 145, 23);
		panel.add(btnNewButton);
		
		txtUser = new JTextField();
		txtUser.setBounds(125, 49, 216, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel w = new JLabel("Ten Dang Nhap");
		w.setBounds(10, 52, 115, 14);
		panel.add(w);
		
		txtOld = new JPasswordField();
		txtOld.setBounds(125, 80, 216, 20);
		panel.add(txtOld);
		
		JButton btnNewButton_1 = new JButton("Quay lai");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDangXuat();
			}
		});
		btnNewButton_1.setBounds(280, 185, 136, 23);
		panel.add(btnNewButton_1);
	}
}
