package dao;

import java.io.*;
import java.util.*;

import pojo.SinhVien;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SinhVienDAO {
	public static void listFilesForFolder(final File folder,List<String> filenames) {
		
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