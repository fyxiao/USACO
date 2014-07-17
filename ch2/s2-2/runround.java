/*
ID:
LANG: JAVA
TASK: runround
*/

import java.util.*;
import java.io.*;

public class runround {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		long m = Long.parseLong(f.readLine());
		
		m++;
		while(!isValid(m) || !noRepeat(m)) {
			m++;
		}
		System.out.println(m);
		out.write(m + "\n");
		
		out.close();
		System.exit(0);
	}
	
	public static boolean isValid(long num) {
		String s = num+"";
		if(s.indexOf('0')>=0) { return false; }
		int len = s.length();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		int pos=0;
		int step=0;
		for(int i=0; i<len; i++) {
			step = Integer.parseInt(s.charAt(pos)+"");
			pos = (pos+step)%len;
			if(visited.contains(pos)) { return false; }
			else {
				visited.add(pos);
			}
		}
		return true;
	}
	
	public static boolean noRepeat(long num) {
		String s = num+"";
		for(int i=1; i<10; i++) {
			int first = s.indexOf(Character.forDigit(i, 10));
			int last = s.lastIndexOf(Character.forDigit(i, 10));
			if(first>=0) {
				if(last>=0 && last!=first) {
					return false;
				}
			}
		}
		return true;
	}
}
