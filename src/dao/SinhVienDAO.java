package dao;

import java.io.*;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		fromCSVToDatabase_lop();
	}
	public static void fromCSVToDatabase_lop() {
		int count = 0;
		List<String> filenames = new LinkedList<String>();
		final File folder = new File("data/lop");
		listFilesForFolder(folder, filenames);
		List<SinhVien> sinhVienList = null;
		boolean kq = true;
		for (int i = 0; i < filenames.size(); i++) {
			sinhVienList = readFromCSV("data/lop/" + filenames.get(i));
			for (int j = 0; j < sinhVienList.size(); j++) {
				kq = SinhVienDAO.themSinhVien(sinhVienList.get(j));
				if(kq)
				{
					count++;
				}
			}
			System.out.println("Thêm thành công "+ count + " sinh viên của lớp "+ filenames.get(i));
			count = 0;
		}
		
		
	}
	public static List<SinhVien> readFromCSV(String csvFile) {
		List<SinhVien> sinhVienList = new ArrayList<>();
		SinhVien sinhVien = null;
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			line = br.readLine();
			String lop = line.split(cvsSplitBy)[0];
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

}