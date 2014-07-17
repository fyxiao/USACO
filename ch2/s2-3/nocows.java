/*
ID:
LANG: JAVA
TASK: nocows
*/

import java.io.*;
import java.util.*;

public class nocows {
	
	public static int count=0;
	public static int N, K;
	public static int[][] ways;
	public static int mod = 9901;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		
		StringTokenizer st=new StringTokenizer(f.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		ways = new int[K+1][N+1];
		
		// initialize ways
		for(int i=1; i<K+1; i++) {
			int test = (int)Math.pow(2, i)-1;
			if(test<N+1) {
				ways[i][test] = 1;
			}
		}
		
		if(N%2==0) {
			System.out.println(0);
			out.write(0 + "\n");
		}
		else {
			// fill ways
			for(int k=2; k<K+1; k++) {
				for(int n=1; n<N+1; n+=2) {
					int sum=0;
					// carry out recursive computations
					
					if(n%2==1) {
						// trees of height exactly k-1, num=i 0 thru N-3
						for(int i=0; i<=n-2; i++) {
							// trees of height 0 thru k-2, num 0 thru N-3
							int amount=0;
							for(int j=0; j<=k-2; j++) {
								amount += ways[j][n-1-i];
							}						
							sum += (ways[k-1][i] * amount)%mod;			
						}
						sum = 2*sum;
						if((n-1)%2==0) {
							for(int i=0; i<n; i++) {
								sum = (sum + ways[k-1][i]*ways[k-1][n-1-i])%mod;
							}
						}
						ways[k][n]=sum%mod;
					}
				}
			}
			System.out.println(ways[K][N]);
			out.write(ways[K][N] + "\n");
		}
		
		for(int k=0; k<K+1; k++) {
			for(int n=0; n<N+1; n++) {
				System.out.print(ways[k][n] + " ");
			}
			System.out.println();
		}
		
		out.close();
		System.exit(0);
	}
	

}
