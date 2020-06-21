package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MonHocDAO;
import pojo.MonHoc_Lop;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class screen extends JFrame {
	DefaultTableModel tableModel;
	private JPanel contentPane;
	private JTextField txtMamon;
	private JTextField txtMssv;
	private JTextField txtGK;
	private JTextField txtCK;
	private JTextField txtKhac;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					screen frame = new screen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Quan ly mon hoc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 11, 426, 185);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label1 = new JLabel("Nhap ma mon");
		label1.setBounds(10, 14, 85, 14);
		panel.add(label1);
		
		txtMamon = new JTextField();
		txtMamon.setBounds(105, 11, 311, 20);
		panel.add(txtMamon);
		txtMamon.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nhap mssv");
		lblNewLabel_2.setBounds(10, 39, 85, 14);
		panel.add(lblNewLabel_2);
		
		txtMssv = new JTextField();
		txtMssv.setColumns(10);
		txtMssv.setBounds(105, 39, 311, 20);
		panel.add(txtMssv);
		
		JLabel lblNhapDiemGk = new JLabel("Nhap diem GK");
		lblNhapDiemGk.setBounds(10, 67, 85, 14);
		panel.add(lblNhapDiemGk);
		
		txtGK = new JTextField();
		txtGK.setColumns(10);
		txtGK.setBounds(105, 67, 196, 20);
		panel.add(txtGK);
		
		JLabel lblNhapDiemCk = new JLabel("Nhap diem CK");
		lblNhapDiemCk.setBounds(10, 95, 85, 14);
		panel.add(lblNhapDiemCk);
		
		txtCK = new JTextField();
		txtCK.setColumns(10);
		txtCK.setBounds(105, 95, 196, 20);
		panel.add(txtCK);
		
		JLabel lblNhapDiemKhac = new JLabel("Nhap diem khac");
		lblNhapDiemKhac.setBounds(10, 123, 85, 14);
		panel.add(lblNhapDiemKhac);
		
		txtKhac = new JTextField();
		txtKhac.setColumns(10);
		txtKhac.setBounds(105, 126, 196, 20);
		panel.add(txtKhac);
		
		JButton btnThemSinhVien = new JButton("Them");
		btnThemSinhVien.setBounds(129, 157, 89, 23);
		panel.add(btnThemSinhVien);
		
		JButton btnXoa = new JButton("Xoa");
		btnXoa.setBounds(228, 157, 89, 23);
		panel.add(btnXoa);
		
		JButton btnSuaDiem = new JButton("Sua diem");
		btnSuaDiem.setBounds(327, 157, 89, 23);
		panel.add(btnSuaDiem);
		
		JButton btnNewButton = new JButton("Xem danh sach");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showMonHoc_Lop(txtMamon.getText());
			}
		});
		btnNewButton.setBounds(10, 157, 109, 23);
		panel.add(btnNewButton);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 207, 421, 133);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Ma mon", "MSSV", "Ho ten", "GK", "CK", "Khac", "Tong"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(5, 207, 421, 131);
		scrollPane.setViewportView(table);
	}
	public screen() {
		Init();
		tableModel = (DefaultTableModel) table.getModel();
		
	}
	private void showMonHoc_Lop(String mamon) {
		List<MonHoc_Lop> list = MonHocDAO.xemDanhSachMonHoc_Lop(mamon);
		tableModel.setRowCount(0);
		String tong = null,mm = null,mssv = null,gk=null,ck=null,khac=null,hoten=null;
		for(MonHoc_Lop mhl: list)
		{
			tong = Float.toString(MonHocDAO.tinhTong(mhl.getMonhoc().getMamon(), mhl.getSinhvien().getMssv()));
			mm = mhl.getMonhoc().getMamon();
			mssv = Integer.toString(mhl.getSinhvien().getMssv());
			gk = Float.toString(mhl.getGk());
			ck = Float.toString(mhl.getCk());
			khac = Float.toString(mhl.getKhac());
			hoten = mhl.getSinhvien().getHoten();
			tableModel.addRow(new Object[] {tableModel.getRowCount()+1,mm,mssv,hoten,gk,ck,khac,tong});
		}
	}
}
