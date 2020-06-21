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
//		Session session = HibernateUtil.getSessionFactory().openSession();

		MonHoc monHoc = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			line = br.readLine();
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] item = line.split(cvsSplitBy);
//				String malop = lop.getMalop();
//				String hql = "FROM SinhVien SV WHERE SV.lop = \'"+malop+"\'";
//				Query<SinhVien> query = session.createQuery(hql);
//				List<SinhVien> results = query.list();
//				Set<SinhVien> sinhVienSet = new HashSet<SinhVien>();
//				for (SinhVien sv:results) {
//					sinhVienSet.add(sv);
//				}
				monHoc = new MonHoc(item[1], item[2], item[3], lop);
				monHocList.add(monHoc);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
//		session.close();
		return monHocList;
	}
	public static void themSinhVienVaoMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Lop lop = session.load(Lop.class, mh.getLop().getMalop());
		String malop = mh.getLop().getMalop();
		String hql = "FROM SinhVien SV WHERE SV.lop = \'"+malop+"\'";
		Query<SinhVien> query = session.createQuery(hql);
		List<SinhVien> results = query.list();
		MonHoc_Lop mhl= null;
		Transaction transaction = null;
		
		for (SinhVien sv:results) {
			mhl= new MonHoc_Lop();
			mhl.setLop(lop);
			mhl.setMonhoc(mh);
			mhl.setSinhvien(sv);
			try {
				transaction = session.beginTransaction();
				session.save(mhl);
				
				transaction.commit();
				session.clear();
			} catch (HibernateException ex) {
				transaction.rollback();
				System.err.println(ex);
			}
		}
		session.close();
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
	public static List<MonHoc_Lop> xemDanhSachMonHoc_Lop(String mamon) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM MonHoc_Lop MH WHERE MH.monhoc=\'"+mamon+"\'" ;
		Query<MonHoc_Lop> query = session.createQuery(hql);
		List<MonHoc_Lop> results = query.list();
		return results;
	}
	public static void xoaMonHoc_Lop(String mamon,String mssv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.beginTransaction();
		String hql = "DELETE FROM MonHoc_Lop MH WHERE MH.sinhvien = \'"+mssv+"\' AND MH.monhoc=\'"+mamon+"\'" ;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		txn.commit();
	}
	public static void themMonHoc_Lop(String mamon,int mssv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SinhVien sv = session.load(SinhVien.class, mssv);
		Lop lop = sv.getLop();
		MonHoc mh = session.load(MonHoc.class, mamon);
		MonHoc_Lop mhl= null;
		Transaction transaction = null;
		mhl= new MonHoc_Lop();
		mhl.setLop(lop);
		mhl.setMonhoc(mh);
		mhl.setSinhvien(sv);
		transaction = session.beginTransaction();
		session.save(mhl);
		transaction.commit();
		session.close();
	}
	public static void bangDiem() {
		List<String> filenames = new LinkedList<String>();
		final File folder = new File("data/diem");
		SinhVienDAO.listFilesForFolder(folder, filenames);
		
		String filename = null;
		String line = "";
		String cvsSplitBy = ",";
		String[] firstline,mamon;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = null;
		for (int i = 0; i < filenames.size(); i++) {
			filename = filenames.get(i);
			try (BufferedReader br = new BufferedReader(new FileReader("data/diem/" + filename))) {
				line = br.readLine();
				firstline = line.split(cvsSplitBy);
				mamon = firstline[0].split("–");
				line = br.readLine();
				while ((line = br.readLine()) != null) {
					txn = session.beginTransaction();
					String[] item = line.split(cvsSplitBy);
					String hql = "UPDATE MonHoc_Lop set gk = \'"+item[3]+"\', ck = \'"+item[4]+"\', khac = \'"+item[5] + "\' WHERE monhoc = \'" + mamon[1]+"\' AND sinhvien= \'" + item[1]+"\'";
					Query query = session.createQuery(hql);
					int result = query.executeUpdate();
					txn.commit();
					session.clear();
				}
				
				}catch (IOException e) {
					e.printStackTrace();
				}
		}
		session.close();
	}
	public static void suaDiem(String mamon,int mssv,float gk, float ck, float khac) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.beginTransaction();
		String hql = "UPDATE MonHoc_Lop set gk = \'"+gk+"\', ck = \'"+ck+"\', khac = \'"+khac + "\' WHERE monhoc = \'" + mamon+"\' AND sinhvien= \'" + mssv+"\'";
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		txn.commit();
	}
	public static float tinhTong(String mamon, int mssv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM MonHoc_Lop MH WHERE MH.sinhvien = \'"+mssv+"\' AND MH.monhoc=\'"+mamon+"\'" ;
		Query<MonHoc_Lop> query = session.createQuery(hql);
		List<MonHoc_Lop> results = query.list();	
		float tong = 0;
		for(MonHoc_Lop mhl:results)
		{
			tong = (mhl.getGk() + mhl.getCk() + mhl.getKhac())/3;
		}
		return tong;
	}
	public static boolean dauRot(String mamon, int mssv , float tong) {
		if(tong>=5)
			return true;
		else
			return false;
	}
	public static int thongKeSoLuong(String mamon) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM MonHoc_Lop MH WHERE MH.monhoc=\'"+mamon+"\'" ;
		Query<MonHoc_Lop> query = session.createQuery(hql);
		List<MonHoc_Lop> results = query.list();	
		int soluong = 0;
		for(MonHoc_Lop mhl:results)
		{
			if(dauRot(mamon,mhl.getSinhvien().getMssv(),tinhTong(mamon,mhl.getSinhvien().getMssv()))) {
				soluong++;
			}
		}
		return soluong;
	}
	public static float thongKePhanTram(String mamon) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM MonHoc_Lop MH WHERE MH.monhoc=\'"+mamon+"\'" ;
		Query<MonHoc_Lop> query = session.createQuery(hql);
		List<MonHoc_Lop> results = query.list();
		return thongKeSoLuong(mamon) / results.size();
	}

}
