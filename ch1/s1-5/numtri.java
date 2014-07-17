/*
ID:
LANG: JAVA
TASK: numtri
*/

import java.io.*;
import java.util.*;

public class numtri {

	public static void main(String[] args) throws IOException {
		
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
	
		int n = Integer.parseInt(f.readLine());
		int[][] triangle = new int[n][n];
		int[][] path = new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for(int j=0; j<i+1; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		path[0][0] = triangle[0][0];
		for(int i=1; i<n; i++) {
			for(int j=0; j<i+1; j++) {
				if(j==0) { path[i][j] = path[i-1][j] + triangle[i][j]; }
				else if(j==i) { path[i][j] = path[i-1][j-1] + triangle[i][j]; }
				else { path[i][j] = Math.max(path[i-1][j-1], path[i-1][j]) + triangle[i][j]; }
			}
		}
		
		int max = 0;
		for(int i=0; i<n; i++) {
			if(path[n-1][i] > max) { max = path[n-1][i]; }
		}
		out.write(max + "\n");
		out.close();
		System.exit(0);
	}
	
}
