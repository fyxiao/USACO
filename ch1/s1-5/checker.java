/*
ID: 
LANG: JAVA
TASK: checker
*/

import java.io.*;
import java.util.*;

public class checker {

	/**
	 * @param args
	 */
	
	public static int[][] marked;
	private static int n;
	private static int[] columns;
	private static int[] locs;
	private static ArrayList<int[]> relist = new ArrayList<int[]>();
	private static int count = 0;
	private static boolean[] ddiag, udiag;
	private static PrintWriter out;
	private static int printCount = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("checker.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
		n = Integer.parseInt(f.readLine());

		marked = new int[n][n];
		columns = new int[n];
		locs = new int[n];
		ddiag = new boolean[2*n-1];
		udiag = new boolean[2*n-1];
		for(int i=0; i<2*n-1; i++) {
			ddiag[i] = true;
			udiag[i] = true;
		}
	
        long start = System.currentTimeMillis();
		placequeen(0); // start process
		
		if(printCount <3) {
			int size = relist.size();
			for(int i=0; i<Math.min(size, 3-printCount); i++) {
				for(int j=0; j<n-1; j++) {
					out.write(relist.get(size-1-i)[j] + " ");
				}
				out.write(relist.get(size-1-i)[n-1] + "\n");
			}
		}
		
		out.write(count + "\n");
        System.out.println("took: " + (System.currentTimeMillis()-start));
		
        f.close();
		out.close();
		System.exit(0);
	}
	
	public static void placequeen(int row) {
		if(row == n) {
			count++;
			if(locs[0] < n/2) {
				count++;
				int[] config = new int[n];
				for(int i=0; i<n; i++) {
					config[i] = n-locs[i];
				}
				relist.add(config);

			}
			if(printCount < 3) {
				for(int i=0; i<n-1; i++) {
					out.write(locs[i]+1 + " ");
				}
				out.write(locs[n-1]+1 + "\n");
				printCount++;
			}
		}
		if(row == 0) {
			for(int i=0; i<Math.ceil((double)n/2); i++) {				
				marked[row][i] = 1;
				ddiag[row-i+n-1] = false;
				udiag[2*n-2-row-i] = false;
				columns[i] = 1;
				locs[row] = i;
				placequeen(row + 1);
				marked[row][i] = 0;
				ddiag[row-i+n-1] = true;
				udiag[2*n-2 -row -i] = true;
				columns[i] = 0;
				locs[row] = 0;
			}
		}
		else {
			for(int i=0; i<n; i++) {
				if(columns[i] == 0 && ddiag[row-i+n-1] && udiag[2*n-2-row-i]) {
					marked[row][i] = 1;
					ddiag[row-i+n-1] = false;
					udiag[2*n-2-row-i] = false;
					columns[i] = 1;
					locs[row]=i;
					placequeen(row + 1);
					marked[row][i] = 0;
					ddiag[row-i+n-1] = true;
					udiag[2*n-2-row-i] = true;
					columns[i] = 0;
					locs[row] = 0;
				}
			}
		}
	}
}
