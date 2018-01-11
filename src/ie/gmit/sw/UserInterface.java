package ie.gmit.sw;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class UserInterface {
	
	public static void menu() throws IOException{
		
		Scanner sc = new Scanner (System.in);
		
		int shingleSize;
		String file1;
		String file2;
		
		System.out.println("Enter size of shingle");
		shingleSize = sc.nextInt();
		
		System.out.println("Enter name of file one (.txt): ");
		file1 = ("resources/" + sc.next() +" .txt");
		
		System.out.println("Enter name of file two (.txt); ");
		file2 = ("resources/" + sc.next() +" .txt");
		
		BlockingQueue<Shingle> shingle= new LinkedBlockingDeque<>();
		
	}

}
