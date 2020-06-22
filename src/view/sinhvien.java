package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.MonHocDAO;
import pojo.MonHoc_Lop;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sinhvien extends JFrame {
	DefaultTableModel tableModel;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sinhvien frame = new sinhvien("");
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
	private void showDiemSinhVien(String mssv) {
		List<MonHoc_Lop> list = MonHocDAO.xemDiemSinhVien(mssv);
		tableModel.setRowCount(0);
		String tong = null,mm = null,gk=null,ck=null,khac=null;
		for(MonHoc_Lop mhl: list)
		{
			tong = Float.toString(MonHocDAO.tinhTong(mhl.getMonhoc().getMamon(), mhl.getSinhvien().getMssv()));
			mm = mhl.getMonhoc().getMamon();
			mssv = Integer.toString(mhl.getSinhvien().getMssv());
			gk = Float.toString(mhl.getGk());
			ck = Float.toString(mhl.getCk());
			khac = Float.toString(mhl.getKhac());
			tableModel.addRow(new Object[] {tableModel.getRowCount()+1,mm,gk,ck,khac,tong});
		}
	}
	/**
	 * Create the frame.
	 */
	public sinhvien(String mssv) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 426, 202);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"STT", "Ma mon", "GK", "CK", "Khac", "Tong"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Dang xuat");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDangXuat();
			}
		});
		btnNewButton.setBounds(337, 229, 89, 23);
		contentPane.add(btnNewButton);
		tableModel = (DefaultTableModel) table.getModel();
		showDiemSinhVien(mssv);

	}
}
