/*
ID:
TASK: game1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class game1 {
	public static int n;
	public static int[] list;
	public static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		n=Integer.parseInt(f.readLine());
		list=new int[n];
		dp=new int[n][n];
		int count=0;
		int tot=0;
		// read in data
		while(count<n) {
			StringTokenizer st=new StringTokenizer(f.readLine());
			while(st.hasMoreTokens()) {
				int k=Integer.parseInt(st.nextToken());
				list[count]=k;
				count++;
				tot+=k;
			}
		}
		// initialize
		for(int i=0; i+1<n; i++) {
			dp[i][i+1]=Math.max(list[i], list[i+1]);
		}
		// run dp
		for(int d=2; d<n; d++) {
			for(int i=0; i+d<n; i++) {
				dp[i][i+d]=sum(i, i+d)-Math.min(dp[i+1][i+d], dp[i][i+d-1]);
			}
		}
		int first=dp[0][n-1];
		int second=tot-first;
		System.out.println(first + " " + second);
		out.write(first + " " + second + "\n");
		out.close();
		System.exit(0);
	}
	public static int sum(int l, int r) {
		int sum=0;
		for(int i=l; i<=r; i++) {
			sum+=list[i];
		}
		return sum;
	}
}
