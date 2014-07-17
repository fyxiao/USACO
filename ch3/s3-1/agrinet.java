/*
ID: 
LANG: JAVA
TASK: agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {
	public static int n;
	public static int[][] graph;
	public static ArrayList<Integer> mst = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		
		n = Integer.parseInt(f.readLine());
		graph = new int[n][n];
		for(int i=0; i<n; i++) {
			String line = "";
			int num = 0;
			while(num < n) {
				String tempLine = f.readLine();
				line += " " + tempLine;
				StringTokenizer testst = new StringTokenizer(tempLine);
				num += testst.countTokens();
			}
			StringTokenizer st = new StringTokenizer(line);
			for(int j=0; j<n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// run Prim's algorithm
		mst.add(0);
		int sum = 0;
		// for each step (n-1) of them
		for(int i=0; i<n-1; i++) {
			int len = mst.size();
			int min = 100001;
			int newV = -1;
			for(int j=0; j<len; j++) {
				for(int k=0; k<n; k++) {
					if(!mst.contains(k)) {
						if(graph[mst.get(j)][k]<min) {
							min = graph[mst.get(j)][k];
							newV = k;
						}
					}
				}
			}
			sum += min;
			mst.add(newV);
		}
		
		System.out.println(sum);
		out.write(sum + "\n");
		
		
		out.close();
		System.exit(0);
	}

}
