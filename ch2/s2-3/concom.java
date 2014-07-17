/*
ID: 
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.*;

public class concom {
	
	public static int[][] ownership;
	public static int n;
	public static boolean[][] used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		
		int numEntries = Integer.parseInt(f.readLine());
		int[][] data = new int[numEntries][3];
		
		// get initial data
		n=0;
		for(int k=0; k<numEntries; k++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int i=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());
			int p=Integer.parseInt(st.nextToken());
			data[k][0] = i;
			data[k][1] = j;
			data[k][2] = p;
			if(i>n) { 
				n=i; 
			}
			if(j>n) { 
				n=j; 
			}
		}
		ownership = new int[n][n];
		used = new boolean[n][n];
		for(int k=0; k<numEntries; k++) {
			ownership[data[k][0]-1][data[k][1]-1] = data[k][2];
		}
		for(int i=0; i<n; i++) {
			ownership[i][i] = 100;
		}
		
		boolean cont = true;
		while(cont) {
			cont = iterate();
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(ownership[i][j]>=100 && i!=j) {
					System.out.println((i+1) + " " + (j+1));
					out.write((i+1) + " " + (j+1) + "\n");
				}
			}
		}
		
		
		
		out.close();
		System.exit(0);
	}
	
	public static boolean iterate() {
		boolean didFind = false;
		
		for(int i=0; i<n; i++) {
			// update what Company i owns
			int[] cOwn = new int[n];
			for(int j=0; j<n; j++) {
				int test = ownership[i][j];
				if(test >= 50 && test != 100) {
					test = 100;
					didFind = true;
				}
				cOwn[j] = test;
				if(test==100 && (j!=i && used[i][j]==false)) {
					for(int k=0; k<n; k++) {
						ownership[i][k] = Math.min(100, ownership[i][k] + ownership[j][k]);
						if(ownership[i][k]==100) {
							didFind = true;
						}
					}
					used[i][j] = true;
				}
			}
		}
		
		return didFind;
	}

}
