package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public  class FileParser implements Runnable {
	
	//variables
	private String file;
	private int shingleSize;
	private int docId;
	private BlockingQueue<Shingle>queue;
	private Deque<String> buffer = new LinkedList<>();
	
	public FileParser(String file, BlockingQueue<Shingle>queue,int shingleSize) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			
			while((line = br.readLine()) != null) {
				String uLine = line.toUpperCase();
				String[] words = uLine.split("");
				addWordsToBuffer(words);
				Shingle s = getNextShingle();
				queue.put(s);
				
			}
			flushBuffer();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//run method
	
	private void addWordsToBuffer(String[] words) {
		for (String s: words) {
			buffer.add(s);
			
		}
	}
	
	private Shingle getNextShingle() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while(counter < shingleSize) {
			if(buffer.peek()!= null) {
				sb.append(buffer.poll());
				counter++;
			}
			
			else {
				counter = shingleSize;
			}
		}
		return null;
	}//shingle method
	
	private void flushBuffer() throws Exception{
		while(buffer.size()>0) {
			Shingle s = getNextShingle();
			if(s!=null) {
				queue.put(s);
			}else {
				queue.put(new Poison(docId,0));
			}
		}
		
	}//Flush buffer method
	
	

}
