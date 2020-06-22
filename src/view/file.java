package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.MonHocDAO;
import dao.SinhVienDAO;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class file extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					file frame = new file();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void btnAction(java.awt.event.ActionEvent evt) {
		giaovu gv = new giaovu();
		gv.setVisible(true);
		this.setVisible(false);
	}
	private void btnDSLop(java.awt.event.ActionEvent evt) {
		danhsachlop ds = new danhsachlop();
		ds.setVisible(true);
		this.setVisible(false);
	}
	private void btnDSMon(java.awt.event.ActionEvent evt) {
		danhsachmon ds = new danhsachmon();
		ds.setVisible(true);
		this.setVisible(false);
	}
	public void btnDangXuat() {
		login f = new login();
		f.setVisible(true);
		this.setVisible(false);
	}
	/**
	 * Create the frame.
	 */
	public file() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 23, 338, 299);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Import Danh sach lop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVienDAO.fromCSVToDatabase_SinhVien();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.TRAILING);
		btnNewButton.setBounds(10, 5, 147, 56);
		panel.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Import Thoi khoa bieu");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonHocDAO.fromCSVToDatabase_MonHoc();
				JOptionPane.showMessageDialog(null, "Da Import thoi khoa bieu");

			}
		});
		btnNewButton_3.setBounds(10, 72, 147, 56);
		panel.add(btnNewButton_3);
		
		JButton btnImportBangDiem = new JButton("Import bang diem");
		btnImportBangDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonHocDAO.bangDiem();
				JOptionPane.showMessageDialog(null, "Da Import bang diem");
			}
		});
		btnImportBangDiem.setBounds(181, 5, 147, 56);
		panel.add(btnImportBangDiem);
		
		JButton btnNewButton_3_1 = new JButton("Quan ly diem");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAction(e);
			}
		});
		btnNewButton_3_1.setBounds(181, 72, 147, 56);
		panel.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Danh sach lop");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDSLop(e);
			}
		});
		btnNewButton_3_2.setBounds(10, 139, 147, 56);
		panel.add(btnNewButton_3_2);
		
		JButton btnNewButton_3_1_1 = new JButton("Danh sach tham gia");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDSMon(e);
			}
		});
		btnNewButton_3_1_1.setBounds(181, 139, 147, 56);
		panel.add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_1_1 = new JButton("Dang xuat");
		btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDangXuat();
			}
		});
		btnNewButton_3_1_1_1.setBounds(181, 208, 147, 56);
		panel.add(btnNewButton_3_1_1_1);
	}

}
