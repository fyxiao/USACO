/*
ID: 
LANG: JAVA
TASK: inflate
*/

import java.io.*;
import java.util.*;

public class inflate {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		
		ArrayList<String> games = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(f.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] points = new int[n][2]; // sorted version
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(f.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		int best[] = new int[m+1];
		// use dp to find optimal solution
		for(int i=1; i<m+1; i++) {
			boolean update = false;
			for(int j=0; j<n; j++) {
				int p = points[j][0];
				int t = points[j][1];
				if(i>=t) { // look at the best points using time i-t
					int test = best[i-t] + p;
					if(test>best[i]) {
						update = true;
						best[i] = test;
					}
				}
			}
			if(!update) {
				best[i] = best[i-1];
			}
		}
		
		System.out.println(best[m]);
		out.write(best[m] + "\n");
		out.close();
		System.exit(0);
	}

}
