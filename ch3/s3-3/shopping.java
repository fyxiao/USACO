/*
ID:
TASK: shopping
LANG: JAVA
 */

import java.util.*;
import java.io.*;

public class shopping {
	public static int s, b;
	public static int[] prices=new int[1000]; // prices[i] is normal price of item i
	public static int[][] offers;
	public static int[] offersp;
	public static int[] target=new int[1000]; // target distribution, want target[i] of item i
	public static int[] indices=new int[5]; // indices[i] is the product code for the ith item,
	public static int[][][][][] dp=new int[6][6][6][6][6];
	public static void main(String[] args) throws IOException {
		BufferedReader f=new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		s=Integer.parseInt(f.readLine());
		offers=new int[s][1000];
		offersp=new int[s];
		for(int i=0; i<s; i++) {
			StringTokenizer st=new StringTokenizer(f.readLine());
			int num=Integer.parseInt(st.nextToken());
			for(int j=0; j<num; j++) {
				int code=Integer.parseInt(st.nextToken());
				int quantity=Integer.parseInt(st.nextToken());
				offers[i][code-1]=quantity;
			}
			offersp[i]=Integer.parseInt(st.nextToken());
		}
		b=Integer.parseInt(f.readLine());
		for(int i=0; i<b; i++) {
			StringTokenizer st=new StringTokenizer(f.readLine());
			int code=Integer.parseInt(st.nextToken());
			int quantity=Integer.parseInt(st.nextToken());
			int price=Integer.parseInt(st.nextToken());
			indices[i]=code-1;
			prices[code-1]=price;
			target[code-1]=quantity;
		}
		
		// initialize dp
		for(int i=0; i<=5; i++) {
			for(int j=0; j<=5; j++) {
				for(int k=0; k<=5; k++) {
					for(int l=0; l<=5; l++) {
						for(int m=0; m<=5; m++) {
							dp[i][j][k][l][m]=i*prices[indices[0]]+j*prices[indices[1]]+k*prices[indices[2]]+l*prices[indices[3]]+m*prices[indices[4]];
						}
					}
				}
			}
		}
		
		// run dp
		for(int t=0; t<=25; t++) {
			for(int i=0; i<=5 && i<=t; i++) {
				for(int j=0; j<=5 && i+j<=t; j++) {
					for(int k=0; k<=5 && i+j+k<=t; k++) {
						for(int l=0; l<=5 && i+j+k+l<=t; l++) {
							for(int o=0; o<s; o++) {
								if(canTry(o, i, j, k, l, t-i-j-k-l)) {
									int a, b, c, d, e;
									a=offers[o][indices[0]];
									b=offers[o][indices[1]];
									c=offers[o][indices[2]];
									d=offers[o][indices[3]];
									e=offers[o][indices[4]];
									dp[i][j][k][l][t-i-j-k-l]=Math.min(dp[i][j][k][l][t-i-j-k-l], offersp[o]+dp[i-a][j-b][k-c][l-d][t-i-j-k-l-e]);
								}
							}
						}
					}
				}
			}
		}
		System.out.println(dp[target[indices[0]]][target[indices[1]]][target[indices[2]]][target[indices[3]]][target[indices[4]]]);
		out.write(dp[target[indices[0]]][target[indices[1]]][target[indices[2]]][target[indices[3]]][target[indices[4]]]+"\n");
		out.close();
		System.exit(0);
	}
	
	public static boolean canTry(int offer, int a, int b, int c, int d, int e) {
		if(e>5) { return false; }
		if(a-offers[offer][indices[0]]<0) { return false; }
		if(b-offers[offer][indices[1]]<0) { return false; }
		if(c-offers[offer][indices[2]]<0) { return false; }
		if(d-offers[offer][indices[3]]<0) { return false; }
		if(e-offers[offer][indices[4]]<0) { return false; }
		return true;
	}
}
