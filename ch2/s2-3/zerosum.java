/*
ID:
LANG: JAVA
TASK: zerosum
*/

import java.io.*;
import java.util.*;

public class zerosum {
	public static int n;
	public static int[] numbers;
	public static ArrayList<String> list = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		
		n = Integer.parseInt(f.readLine());
		numbers = new int[n];
		for(int i=0; i<n; i++) {
			numbers[i] = i+1;
		}
		
		//initialize
		list.add("/");
		list.add("+");
		list.add("-");
		iterate(1);
			
		int num = list.size();
		for(int i=0; i<num; i++) {
			String test = list.get(i);
			String output = numbers[0] + "";
			for(int j=0; j<n-1; j++) {
				output = output + " " + test.charAt(j) + " " + numbers[j+1];
			}
			output=output.replace(" / ", "");
			StringTokenizer st = new StringTokenizer(output);
			int sum = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				if(st.nextToken().equals("+")) {
					sum = sum + Integer.parseInt(st.nextToken());
				}
				else {
					sum = sum - Integer.parseInt(st.nextToken());
				}
			}
			if(sum==0) {
				String outwrite = numbers[0] + "";
				for(int j=0; j<n-1; j++) {
					outwrite = outwrite + test.charAt(j) + numbers[j+1];
				}
				outwrite=outwrite.replace("/", " ");
				out.write(outwrite + "\n");
			}
		}
		
		out.close();
		System.exit(0);
	}
	
	public static void iterate(int level) {
		if(level==n-1) { return; }
		else {
			int num = list.size();
			for(int i=0; i<num; i++) {
				String cur = list.get(0);
				list.remove(0);
				String a = cur + "/";
				String b = cur + "+";
				String c = cur + "-";
				list.add(a);
				list.add(b);
				list.add(c);
			}
			level++;
			iterate(level);
		}
	}

}
