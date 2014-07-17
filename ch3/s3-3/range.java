/*
ID:
TASK: range
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class range {
	public static int n;
	public static int[][] field;
	public static int[][] largest;
	public static int[] counts;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		
		n=Integer.parseInt(f.readLine());
		field=new int[n][n];
		largest=new int[n][n];
		counts=new int[n+1];
		for(int i=0; i<n; i++) {
			String s=f.readLine();
			for(int j=0; j<n; j++) {
				field[i][j]=Integer.parseInt(s.charAt(j)+"");
				largest[i][j]=field[i][j];
			}
		}
		
		for(int i=1; i<n; i++) {
			for(int j=1; j<n; j++) {
				if(largest[i][j]==1) {
					int d=largest[i-1][j-1];
					int newd=0;
					for(int k=1; k<=d; k++) {
						if(field[i-k][j]!=1 || field[i][j-k]!=1) {
							break;
						}
						else {
							newd++;
						}
					}
					largest[i][j]=newd+1;
					if(largest[i][j]>=2) {
						for(int k=2; k<=largest[i][j]; k++) {
							counts[k]+=1;
						}
					}
				}
			}
		}
		/*
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(largest[i][j]+" ");
			}
			System.out.println();
		}*/
		
		// count them
		for(int i=2; i<n+1; i++) {
			if(counts[i]>0) {
				System.out.println(i + " " + counts[i]);
				out.write(i + " " + counts[i] + "\n");
			}
		}
		
		out.close();
		System.exit(0);
	}
}
