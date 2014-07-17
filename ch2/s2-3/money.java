/*
ID:
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.*;

public class money {
	
	public static int v, n;
	public static HashSet coins = new HashSet();
	public static long[] ways;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		v = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		ways = new long[n+1];
		ways[0] = 1;
		
		while(f.ready()) {
			st = new StringTokenizer(f.readLine());
			while(st.hasMoreTokens()) {
				coins.add(Integer.parseInt(st.nextToken()));
			}
		}

		Iterator itr = coins.iterator();
		while(itr.hasNext()) {
			iterate( ((Integer)itr.next()).intValue() );
		}
		
		for(int i=0; i<n+1; i++) {
			System.out.print(ways[i] + " ");
		}
		System.out.println();
		
		System.out.println(ways[n]);
		out.write(ways[n] + "\n");
		
		out.close();
		System.exit(0);
	}
	
	public static void iterate(int i) {
		int inc = i;
		for(int j=0; j<n+1-inc; j++) {
			if(ways[j]!=0) {
				ways[j+inc] += ways[j];
			}
		}
		
	}
	

}
