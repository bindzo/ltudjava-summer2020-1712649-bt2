package pojo;


import java.util.Set;

public class Lop {
	private String malop;
	private Set<SinhVien> sinhviens;
	public Lop() {
		super();
	}

	public Lop(String malop, Set<SinhVien> sinhviens) {
		super();
		this.malop = malop;
		this.sinhviens = sinhviens;
	}

	public Set<SinhVien> getSinhviens() {
		return sinhviens;
	}

	public void setSinhviens(Set<SinhVien> sinhviens) {
		this.sinhviens = sinhviens;
	}

	public String getMalop() {
		return malop;
	}

	public void setMalop(String malop) {
		this.malop = malop;
	}
	
}
