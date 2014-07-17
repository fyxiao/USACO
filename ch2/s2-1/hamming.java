/*
ID:
LANG: JAVA
TASK: hamming
*/

import java.io.*;
import java.util.*;

public class hamming {
	
	private static int n, b, d, max;
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static boolean found = false;
	private static PrintWriter out;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		max = (int)Math.pow(2.0, (double)b);
		
		iterate(0);
		
		out.close();
		System.exit(0);
	}
	
	public static void iterate(int level) {
		if(level==n || found==true) {
			return; 
		}
		int num = list.size();
		int offset = -1;
		if(level>=1) {
			offset = list.get(num-1);
		}
		
		int cur = level;
		
		for(int i=offset+1; i<max; i++) {
			if(isValid(i)) {
				list.add(i);
				if(level==n-1) {
					found = true;
					System.out.println("at level " + level);
					for(int j=0; j<n-1; j++) {
						System.out.print(list.get(j) + " ");
					}
					System.out.println(list.get(n-1));
					
					// write output
					int j=0;
					while(j<n) {
						if((j+1)%10==0) {
							out.write(list.get(j) + "\n");
						}
						else if(j==n-1) {
							out.write(list.get(j) + "\n");
						}
						else {
							out.write(list.get(j) + " ");
						}
						j++;
					}
					
					
					
					return;
				}
				iterate(cur+1);
				list.remove(num);
			}
		}
	}
	
	public static boolean isValid(int test) {
		int num = list.size();
		if(num == 0) { return true; }
		for(int i=0; i<num; i++) {
			if(hDis(list.get(i), test) < d) {
				return false;
			}
		}
		return true;
	}
	
	public static int hDis(int a, int b) {
		int x = a^b;
		int dif=0;
		while(x>0) {
			dif += x%2;
			x = x/2;
		}
		return dif;
	}
	

}
