package pojo;

import java.util.Set;

public class MonHoc {
	private String mamon;
	private String ten;
	private String phong;
	private Lop lop;
	public MonHoc() {
		super();
	}
	public String getMamon() {
		return mamon;
	}
	public MonHoc(String mamon, String ten, String phong, Lop malop) {
		super();
		this.mamon = mamon;
		this.ten = ten;
		this.phong = phong;
		this.lop = malop;
	}
	public void setMamon(String mamon) {
		this.mamon = mamon;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}
	public Lop getLop() {
		return lop;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
	}
}
