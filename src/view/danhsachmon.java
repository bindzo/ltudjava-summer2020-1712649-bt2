package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.MonHocDAO;
import dao.SinhVienDAO;
import pojo.MonHoc_Lop;
import pojo.SinhVien;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class danhsachmon extends JFrame {
	DefaultTableModel tableModel;
	private JPanel contentPane;
	private JTextField txtMon;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					danhsachmon frame = new danhsachmon();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void showDanhSachSinhVien(String mamon) {
		List<MonHoc_Lop> list = MonHocDAO.xemDanhSachMonHoc_Lop(mamon);
		tableModel.setRowCount(0);
		String cmnd = null,mssv = null,gioitinh=null,hoten=null;
		for(MonHoc_Lop mhl: list)
		{
			mssv = Integer.toString(mhl.getSinhvien().getMssv());
			cmnd = Integer.toString(mhl.getSinhvien().getCmnd());
			hoten = mhl.getSinhvien().getHoten();
			gioitinh = mhl.getSinhvien().getGioitinh();
			tableModel.addRow(new Object[] {tableModel.getRowCount()+1,mssv,hoten,gioitinh,cmnd});
		}
	}
	/**
	 * Create the frame.
	 */
	public danhsachmon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Nhap ma mon hoc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 11, 406, 47);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtMon = new JTextField();
		txtMon.setToolTipText("");
		txtMon.setBounds(10, 16, 259, 20);
		panel.add(txtMon);
		txtMon.setColumns(10);
		
		JButton btnNewButton = new JButton("Tim");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDanhSachSinhVien(txtMon.getText());
			}
		});
		btnNewButton.setBounds(279, 15, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 69, 406, 172);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"STT", "MSSV", "Ho ten", "Gioi tinh", "CMND"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		scrollPane.setViewportView(table);
		tableModel = (DefaultTableModel) table.getModel();

	}
}
