package pojo;

import java.io.Serializable;

public class MonHoc_Lop implements Serializable {
	private int stt;
	private MonHoc monhoc;
	private SinhVien sinhvien;
	private Lop lop;
	private float gk,ck,khac;
	public float getGk() {
		return gk;
	}
	public void setGk(float gk) {
		this.gk = gk;
	}
	public float getCk() {
		return ck;
	}
	public void setCk(float ck) {
		this.ck = ck;
	}
	public float getKhac() {
		return khac;
	}
	public void setKhac(float khac) {
		this.khac = khac;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public MonHoc_Lop(MonHoc monhoc, SinhVien sinhvien, Lop lop) {
		super();
		this.monhoc = monhoc;
		this.sinhvien = sinhvien;
		this.lop = lop;
	}

	public MonHoc_Lop() {
		super();
	}

	public MonHoc getMonhoc() {
		return monhoc;
	}

	public void setMonhoc(MonHoc monhoc) {
		this.monhoc = monhoc;
	}

	public SinhVien getSinhvien() {
		return sinhvien;
	}

	public void setSinhvien(SinhVien sinhvien) {
		this.sinhvien = sinhvien;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

}
