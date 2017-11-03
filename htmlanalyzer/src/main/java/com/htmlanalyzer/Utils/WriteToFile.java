package com.htmlanalyzer.Utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.htmlanalyzer.model.HTMLLinkElement;

public class WriteToFile {

	
	public void writeToLocalFile(String text, String link, String fileName){
	
		final String FILENAME = "C:\\temp\\"+fileName;
		
			BufferedWriter bw = null;
			FileWriter fw = null;

			try {				
				fw = new FileWriter(FILENAME, true);
				bw = new BufferedWriter(fw);
				bw.write(text +" : " +link);
				bw.newLine();				

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}
		 	
	}
	
}
