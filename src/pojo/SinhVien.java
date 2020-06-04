package pojo;

public class SinhVien {
	private int mssv;
	private String hoten;
	private String gioitinh;
	private int cmnd;
	private String lop;
	private String username;
	private String password;
	
	public SinhVien(int mssv, String hoten, String gioitinh, int cmnd, String lop, String usename, String password) {
		super();
		this.mssv = mssv;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.cmnd = cmnd;
		this.lop = lop;
		this.username = usename;
		this.password = password;
	}
	public SinhVien() {
		super();
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMssv() {
		return mssv;
	}
	public void setMssv(int mssv) {
		this.mssv = mssv;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public int getCmnd() {
		return cmnd;
	}
	public void setCmnd(int cmnd) {
		this.cmnd = cmnd;
	}
	
}
