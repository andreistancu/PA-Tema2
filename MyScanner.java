
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a
 */

public class MyScanner {
   BufferedReader br;
   StringTokenizer st;

   public MyScanner(String filename) {
	   
	   try {
		   br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
	   } catch (FileNotFoundException ex) {
		   Logger.getLogger(MyScanner.class.getName()).log(Level.SEVERE, null, ex);
	   }
   }

	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException ex) {
				Logger.getLogger(MyScanner.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return st.nextToken();
   }

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		 return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public String nextLine(){
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException ex) {
			Logger.getLogger(MyScanner.class.getName()).log(Level.SEVERE, null, ex);
		}
      return str;
   }
}
