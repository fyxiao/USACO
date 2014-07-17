/*
ID:
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.*;

public class prefix {
	
	public static ArrayList<String> prefix = new ArrayList<String>();
	public static boolean[] attained;
	public static String S;
	public static int len;
	public static int n=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		
		StringTokenizer st;
		while(f.ready()) {
			String test = f.readLine();
			if(test.equals(".")) { break; }
			st = new StringTokenizer(test);
			while(st.hasMoreTokens()) {
				prefix.add(st.nextToken());
				n++;
			}
		}
		
		// build the sequence
		StringBuffer seq = new StringBuffer("");
		while(f.ready()) {
			seq.append(f.readLine());
		}
		len = seq.length();
		S = seq.toString();
		attained = new boolean[len+1];
		attained[0] = true;
		
		// run calculation
		iterate();
		
		// get and print result
		int max=0;

		for(int i=1; i<len+1; i++) {
			if(attained[i]) {
				max = i;
			}
		}
		System.out.println(max);
		out.write(max + "\n");
		
		out.close();
		System.exit(0);
	}
	
	public static void iterate() {
		boolean contIter = false;

		for(int i=0; i<len+1; i++) {
			for(int j=0; j<n; j++) {
				if(attained[i]) {
					if(S.substring(i).startsWith(prefix.get(j))) {
						if(!attained[i+prefix.get(j).length()]) {
							contIter = true;
							attained[i+prefix.get(j).length()] = true;
						}
					}
				}
			}
		}
		

		if(contIter) {
			iterate();
		}
		
	}
}
