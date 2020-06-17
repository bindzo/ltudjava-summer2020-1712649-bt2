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

import pojo.Lop;
import pojo.MonHoc;
import pojo.SinhVien;
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
				themMonHoc(mh);
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
				System.out.println(monHoc);
				monHocList.add(monHoc);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return monHocList;
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
}
