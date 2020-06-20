package dao;

import java.io.*;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.Lop;
import pojo.SinhVien;
import util.HibernateUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SinhVienDAO {
	public static void listFilesForFolder(final File folder, List<String> filenames) {

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, filenames);
			} else {
				if (fileEntry.getName().contains(".csv"))
					filenames.add(fileEntry.getName());
			}
		}
	}
	
	public static void main(String[] args) {
//		themMotSinhVienVaoLop(1742006, "Trần Trung X", "Nan", 987612345, "17HCB");
//		fromCSVToDatabase_SinhVien();
//		MonHocDAO.fromCSVToDatabase_MonHoc();
//		xemDanhSachSinhVien("18HCB");
//		MonHocDAO.xemDanhSachMonHoc("18HCB");
		MonHocDAO.themMonHoc_Lop("CTT011",1742001);
		System.out.println("DONE");
	}
	@SuppressWarnings("unchecked")
	public static void fromCSVToDatabase_SinhVien() {
		int count = 0;
		List<String> filenames = new LinkedList<String>();
		final File folder = new File("data/lop");
		listFilesForFolder(folder, filenames);
		Set<SinhVien> sinhVienList = null;
		boolean kq = true;
		Lop sinhVienSet = null;
		String filename = null;
		for (int i = 0; i < filenames.size(); i++) {
			filename = filenames.get(i);
			sinhVienSet = new Lop();
			sinhVienList = readFromCSV("data/lop/" + filename,sinhVienSet);
			sinhVienSet.setSinhviens(sinhVienList);
			sinhVienSet.setMalop(filename.substring(0,filename.length()-4));
//			for (int j = 0; j < sinhVienList.size(); j++) {	
//				kq = SinhVienDAO.themSinhVien(sinhVienList.get(j));
//				if(kq)
//				{
//					count++;
//				}
//			}
//			System.out.println("Thêm thành công "+ count + " sinh viên của lớp "+ filenames.get(i));
//			count = 0;
			SinhVienDAO.themLop(sinhVienSet);
		}
		
		
	}
	public static Set<SinhVien> readFromCSV(String csvFile, Lop lop) {
		Set<SinhVien> sinhVienList = new HashSet<SinhVien>();
		SinhVien sinhVien = null;
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			line = br.readLine();
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] item = line.split(cvsSplitBy);
				sinhVien = new SinhVien(Integer.parseInt(item[1]), item[2], item[3], Integer.parseInt(item[4]), lop,
						null, null);
				sinhVienList.add(sinhVien);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sinhVienList;
	}
	public static void xemDanhSachSinhVien(String malop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM SinhVien SV WHERE SV.lop = \'"+malop+"\'";
		Query<SinhVien> query = session.createQuery(hql);
		List<SinhVien> results = query.list();
		for (SinhVien sv:results) {
			System.out.println(sv.getMssv());

		}
	}
	public static SinhVien layThongTinSinhVien(int mssv) {
		SinhVien sv = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			sv = (SinhVien) session.get(SinhVien.class, mssv);
		} catch (HibernateException ex) {
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}
	public static boolean themMotSinhVienVaoLop(int mssv,String hoten,String gioitinh,int cmnd,String malop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Lop lop = session.load(Lop.class, malop);
		session.close();
		SinhVien svmoi = new SinhVien(mssv,hoten,gioitinh,cmnd,null,null,null);
		svmoi.setLop(lop);
		return themSinhVien(svmoi);
		
	}
	public static boolean themSinhVien(SinhVien sv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (SinhVienDAO.layThongTinSinhVien(sv.getMssv()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(sv);
			transaction.commit();
		} catch (HibernateException ex) {
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
	public static boolean themLop(Lop lop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(lop);
			transaction.commit();
		} catch (HibernateException ex) {
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
}