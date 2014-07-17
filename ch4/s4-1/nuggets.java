/*
ID:
LANG: JAVA
TASK: nuggets
*/

import java.io.*;
import java.util.*;

public class nuggets {
	public static int n, best, cgcd;
	public static int imp=0;
	public static int[] caps;
	public static int max=65024;
	public static boolean[] dp=new boolean[max+1];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
		n=Integer.parseInt(f.readLine());
		caps=new int[n];
		// initialize
		dp[0]=true;
		for(int i=0; i<n; i++) {
			caps[i]=Integer.parseInt(f.readLine());
			dp[caps[i]]=true;
		}
		cgcd=caps[0];
		for(int i=1; i<n; i++) {
			cgcd=gcd(cgcd, caps[i]);
		}
		// calculate
		best=0;
		if(cgcd==1) {
			for(int i=1; i<=max; i++) {
				boolean canMake=false;
				cur:
					for(int j=0; j<n; j++) {
						int step=i-caps[j];
						if(step>=0 && dp[step]==true) {
							dp[i]=true;
							canMake=true;
							break cur;
						}
					}
				if(!canMake) { best=i; }
			}
		}
		System.out.println(best);
		out.write(best+"\n");

		out.close();
		System.exit(0);
	}
	
	// return (a, b), assume a>=b
	public static int gcd(int a, int b) {
		if(b==0) { return a; }
		else { return gcd(b, a-(a/b)*b); }
	}
}
