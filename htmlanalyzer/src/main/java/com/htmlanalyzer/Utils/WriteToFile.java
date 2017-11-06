package com.htmlanalyzer.Utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.htmlanalyzer.model.HTMLLinkElement;

public class WriteToFile implements Runnable {

	private ConcurrentLinkedQueue<String> queue;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private FileOutputStream out;

	public WriteToFile() {

	}

	public WriteToFile(FileOutputStream out, ConcurrentLinkedQueue<String> queue) {
		this.out = out;
		this.queue = queue;
	}

	@Override
	public void run() {
		synchronized (queue) {
			System.out.println("Into WritingToFile Class");
			while (true) {
				if (!queue.isEmpty()) {
					try {
						//System.out.println("Writing...");
						out.write(queue.poll().getBytes("UTF-8"));
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
		}
	}

}
