package baitap2;

import java.io.*;
import java.util.*;

/**
 * StudentDao class
 * 
 * @author viettuts.vn
 */
//public class SinhVienDAO{

//    private static final String STUDENT_FILE_NAME = "student.txt";
// 
//    /**
//     * save list 
//
// to file
//     * 
//     * @param studentList: list student to save
//     */
//    public void write(List<SinhVien> studentList) {
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
//        try {
//            fos = new FileOutputStream(new File(STUDENT_FILE_NAME));
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(studentList);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            closeStream(fos);
//            closeStream(oos);
//        }
//    }
// 
//    /**
//     * read list student from file
//     * 
//     * @return list student
//     */
//    public List<SinhVien> read() {
//        List<SinhVien> studentList = new ArrayList<>();
//        FileInputStream fis = null;
//        ObjectInputStream ois = null;
//        try {
//            fis = new FileInputStream(new File(STUDENT_FILE_NAME));
//            ois = new ObjectInputStream(fis);
//            studentList = (List<SinhVien>) ois.readObject();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            closeStream(fis);
//            closeStream(ois);
//        }
//        return studentList;
//    }
// 
//    /**
//     * close input stream
//     * 
//     * @param is: input stream
//     */
//    private void closeStream(InputStream is) {
//        if (is != null) {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
// 
//    /**
//     * close output stream
//     * 
//     * @param os: output stream
//     */
//    private void closeStream(OutputStream os) {
//        if (os != null) {
//            try {
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SinhVienDAO {
	public static void listFilesForFolder(final File folder,	List<String> filenames) {
		
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry,filenames);
	        } else {
	            if(fileEntry.getName().contains(".csv"))
	                filenames.add(fileEntry.getName());
	        }
	    }
	}
	public static void main(String[] args) {
		List<String> filenames = new LinkedList<String>();
		final File folder = new File("data/lop");
		listFilesForFolder(folder,filenames);
		List<SinhVien> sinhVienList = null;
		for(int i=0;i<filenames.size();i++)
		{
			System.out.println(filenames.get(i));
			sinhVienList = read("data/lop/"+ filenames.get(i));
			for(int j=0;j<sinhVienList.size();j++)
			{
				System.out.println(sinhVienList.get(j).getMssv());
			}
		}
		
		
		
		

	}
	
	public static List<SinhVien> read(String csvFile) {
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
				sinhVien = new SinhVien(Integer.parseInt(item[1]),item[2],item[3],Integer.parseInt(item[4]),lop,null,null);
				sinhVienList.add(sinhVien);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sinhVienList;
	}

}