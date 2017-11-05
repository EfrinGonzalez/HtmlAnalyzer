package com.htmlanalyzer.Utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.htmlanalyzer.model.HTMLLinkElement;

public class WriteToFile implements Runnable{
	
	
	private ConcurrentLinkedQueue<String> queue;
	//final String FILENAME = "C:\\temp\\test.txt";
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private FileOutputStream out;

	public WriteToFile(){
		
	}
	
	public WriteToFile(FileOutputStream out, ConcurrentLinkedQueue<String> queue){
		this.out = out;
		this.queue = queue;
	}


	/*public void writeToLocalFile(String text, String link, String fileName) {

		final String FILENAME = "C:\\temp\\" + fileName;

		

		try {
			fw = new FileWriter(FILENAME, true);
			bw = new BufferedWriter(fw);
			// “link text;url”
			bw.write('"' + text + " ; " + link + '"');
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

	}*/

	@Override
	public void run() {
		synchronized (queue) {
			while (true) {
				if (!queue.isEmpty()) {
					try {
						out.write(queue.poll().getBytes("UTF-8"));
						//bw.write('"' + text + " ; " + link + '"');
						//out.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}}

}
