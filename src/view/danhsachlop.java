package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MonHocDAO;
import dao.SinhVienDAO;
import pojo.MonHoc;
import pojo.MonHoc_Lop;
import pojo.SinhVien;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class danhsachlop extends JFrame {
	DefaultTableModel tableModel;
	DefaultTableModel tableModel2;
	private JPanel contentPane;
	private JTextField txtLop;
	private JTable table;
	private JTable table_1;
	private JTextField txtMssv;
	private JTextField txtTen;
	private JTextField txtGT;
	private JTextField txtCmnd;
	
	public void btnBack() {
		file f = new file();
		f.setVisible(true);
		this.setVisible(false);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					danhsachlop frame = new danhsachlop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void showDanhSachSinhVien(String malop) {
		List<SinhVien> list = SinhVienDAO.xemDanhSachSinhVien(malop);
		tableModel.setRowCount(0);
		String cmnd = null,mssv = null,gioitinh=null,hoten=null;
		for(SinhVien sv: list)
		{
			mssv = Integer.toString(sv.getMssv());
			cmnd = Integer.toString(sv.getCmnd());
			hoten = sv.getHoten();
			gioitinh = sv.getGioitinh();
			tableModel.addRow(new Object[] {tableModel.getRowCount()+1,mssv,hoten,gioitinh,cmnd});
		}
	}
	private void showThoiKhoaBieu(String malop) {
		List<MonHoc> list = MonHocDAO.xemDanhSachMonHoc(malop);
		tableModel2.setRowCount(0);
		String mamon = null,phong=null,ten=null;
		for(MonHoc mh: list)
		{
			mamon = mh.getMamon();
			ten = mh.getTen();
			phong = mh.getPhong();
			tableModel2.addRow(new Object[] {tableModel2.getRowCount()+1,mamon,ten,phong});
		}
	}
	/**
	 * Create the frame.
	 */
	public danhsachlop() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 426, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtLop = new JTextField();
		txtLop.setBounds(21, 11, 201, 20);
		panel.add(txtLop);
		txtLop.setColumns(10);
		
		JButton btnNewButton = new JButton("Tim lop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDanhSachSinhVien(txtLop.getText());
				showThoiKhoaBieu(txtLop.getText());
			}
		});
		btnNewButton.setBounds(234, 10, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnQuayLai = new JButton("Quay lai");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBack();
			}
		});
		btnQuayLai.setBounds(330, 10, 89, 23);
		panel.add(btnQuayLai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 87, 426, 178);
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(5, 306, 426, 178);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Ma mon", "Ten mon", "Phong hoc"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("Danh sach lop");
		lblNewLabel.setBounds(15, 62, 151, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thoi khoa bieu");
		lblNewLabel_1.setBounds(5, 281, 287, 14);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Them sinh vien", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(441, 5, 182, 260);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtMssv = new JTextField();
		txtMssv.setBounds(10, 31, 162, 20);
		panel_1.add(txtMssv);
		txtMssv.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("MSSV");
		lblNewLabel_2.setBounds(10, 14, 49, 14);
		panel_1.add(lblNewLabel_2);
		
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(10, 79, 162, 20);
		panel_1.add(txtTen);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ho ten");
		lblNewLabel_2_1.setBounds(10, 62, 49, 14);
		panel_1.add(lblNewLabel_2_1);
		
		txtGT = new JTextField();
		txtGT.setColumns(10);
		txtGT.setBounds(10, 127, 162, 20);
		panel_1.add(txtGT);
		
		JLabel lblNewLabel_2_2 = new JLabel("Gioi tinh");
		lblNewLabel_2_2.setBounds(10, 110, 49, 14);
		panel_1.add(lblNewLabel_2_2);
		
		txtCmnd = new JTextField();
		txtCmnd.setColumns(10);
		txtCmnd.setBounds(10, 175, 162, 20);
		panel_1.add(txtCmnd);
		
		JLabel lblNewLabel_2_3 = new JLabel("CMND");
		lblNewLabel_2_3.setBounds(10, 158, 49, 14);
		panel_1.add(lblNewLabel_2_3);
		
		JButton btnNewButton_1 = new JButton("Them");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVienDAO.themMotSinhVienVaoLop(Integer.parseInt(txtMssv.getText()),txtTen.getText(), txtGT.getText(), Integer.parseInt(txtCmnd.getText()), txtLop.getText());
				showDanhSachSinhVien(txtLop.getText());
				showThoiKhoaBieu(txtLop.getText());
			}
		});
		btnNewButton_1.setBounds(10, 206, 89, 23);
		panel_1.add(btnNewButton_1);
		tableModel = (DefaultTableModel) table.getModel();
		tableModel2 = (DefaultTableModel) table_1.getModel();
	}
}
