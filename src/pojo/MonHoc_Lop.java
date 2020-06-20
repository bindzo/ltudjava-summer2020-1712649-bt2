package pojo;

import java.io.Serializable;

public class MonHoc_Lop implements Serializable {
	private int stt;
	private MonHoc monhoc;
	private SinhVien sinhvien;
	private Lop lop;
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
