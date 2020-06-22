package pojo;

import java.io.Serializable;

public class SinhVien implements Serializable {
	private int mssv;
	private String hoten;
	private String gioitinh;
	private int cmnd;
	private Lop lop;
	private String username;
	private String password;
	
	public SinhVien(int mssv, String hoten, String gioitinh, int cmnd, Lop lop, String username, String password) {
		super();
		this.mssv = mssv;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.cmnd = cmnd;
		this.lop = lop;
		this.username = username;
		this.password = password;
	}
	public SinhVien() {
		super();
	}
	
	public Lop getLop() {
		return lop;
	}
	public void setLop(Lop lop) {
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
