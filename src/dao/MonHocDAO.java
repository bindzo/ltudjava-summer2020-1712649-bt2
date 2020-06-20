package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojo.Lop;
import pojo.MonHoc;
import pojo.SinhVien;
import pojo.MonHoc_Lop;
import util.HibernateUtil;

public class MonHocDAO {
	public static void fromCSVToDatabase_MonHoc() {
		int count = 0;
		List<String> filenames = new LinkedList<String>();
		final File folder = new File("data/monhoc");
		SinhVienDAO.listFilesForFolder(folder, filenames);
		Set<MonHoc> monHocList = null;
		boolean kq = true;
		Lop lop = null;
		String filename = null;
		for (int i = 0; i < filenames.size(); i++) {
			filename = filenames.get(i);
			Session session = HibernateUtil.getSessionFactory().openSession();
			lop = session.load(Lop.class, filename.substring(0,filename.length()-4));
			session.close();
			monHocList = readFromCSV_MonHoc("data/monhoc/" + filename,lop);
			for(MonHoc mh:monHocList)
			{
//				themMonHoc(mh);
				themSinhVienVaoMonHoc(mh);
				System.out.println(mh.getTen());
			}
		}
	}
	public static Set<MonHoc> readFromCSV_MonHoc(String csvFile,Lop lop) {
		Set<MonHoc> monHocList = new HashSet<MonHoc>();
		
		MonHoc monHoc = null;
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			line = br.readLine();
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] item = line.split(cvsSplitBy);
				monHoc = new MonHoc(item[1], item[2], item[3], lop);
				monHocList.add(monHoc);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return monHocList;
	}
	public static void themSinhVienVaoMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Lop lop = session.load(Lop.class, mh.getLop().getMalop());
		String malop = mh.getLop().getMalop();
		String hql = "FROM SinhVien SV WHERE SV.lop = \'"+malop+"\'";
		Query<SinhVien> query = session.createQuery(hql);
		List<SinhVien> results = query.list();
		MonHoc_Lop mhl= new MonHoc_Lop();
		Transaction transaction = null;
		for (SinhVien sv:results) {
			mhl.setLop(lop);
			mhl.setMonhoc(mh);
			mhl.setSinhvien(sv);
			try {
				transaction = session.beginTransaction();
				session.save(mhl);
				transaction.commit();
			} catch (HibernateException ex) {
				transaction.rollback();
				System.err.println(ex);
			} finally {
				session.close();
			}
		}
	}
	public static boolean themMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(mh);
			transaction.commit();
		} catch (HibernateException ex) {
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}
	public static void xemDanhSachMonHoc(String malop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM MonHoc MH WHERE MH.lop = \'"+malop+"\'";
		Query<MonHoc> query = session.createQuery(hql);
		List<MonHoc> results = query.list();
		for (MonHoc mh:results) {
			System.out.println(mh.getTen());

		}
	}
}
