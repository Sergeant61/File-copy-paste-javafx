package io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileCopyPaste {
	
	public static List<String> fileExists(){
		File file =null;
		List<String> list = new ArrayList<>();
		String[] dizinler = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
				"Q","R","S","T","U","V","W","X","Y","Z"};
		
		
		for(int i=0 ; i < dizinler.length; i ++){
			file = new File(dizinler[i] + ":/");
			if(file.exists()){
				list.add(dizinler[i]);
			}
		}
		
		
		return list;
	}

	public static void copyFile(String sourceFileText, String destFileText, String fileText) throws IOException {

		File destFile = new File(destFileText);
		File sourceFile = new File(sourceFileText + fileText);

		if (!destFile.exists()) {
			destFile.mkdirs();
		}

		destFile = new File(destFileText + fileText);

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());

		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static void cutFile(String sourceFileText, String destFileText, String fileText) throws IOException {

		File destFile = new File(destFileText);
		File sourceFile = new File(sourceFileText + fileText);

		if (!destFile.exists()) {
			destFile.mkdirs();
		}

		destFile = new File(destFileText + fileText);

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());

		} finally {
			if (source != null) {
				if (!sourceFile.delete()) {
					System.out.println("Dosya silme izni bulunmamaktadýr.");
				}
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static File[] FileList(String path) {
		File dosya = new File(path);
		File[] tumDosyalar=null;
		if (dosya.isDirectory()) {
			
			tumDosyalar = dosya.listFiles();
			
			for (int i = 0; i < tumDosyalar.length; i++){ System.out.println(tumDosyalar[i].getName());}
			
		} else {
			System.out.println("Klasör veya dizin deðil.");
		}
		
		return tumDosyalar;

	}
}
