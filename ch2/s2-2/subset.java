/*
ID: 
LANG: JAVA
TASK: subset
*/

import java.io.*;

public class subset {
	public static int n;
	public static long[] set;
	public static long[][] used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		
		n = Integer.parseInt(f.readLine());		
		int tot = n*(n+1)/2; // if total sum is odd, then it's impossible
		
		if(tot%2==1) { 
			out.write(0 + "\n");
			System.out.println("abort");
		}
		else {
			int sum = tot/2;
			System.out.println(sum);
			set = new long[tot+1];
			used = new long[tot+1][n+1];
			
			set[0] = 1; // initialize, helps make code simpler
			//System.out.println("sum is " + sum);
			for(int i=1; i<n+1; i++) {
				for(int j=0; j<tot+1 && (j+i<tot+1); j++) {
					if(set[j]!=0) {
						set[j+i]=set[j+i]+set[j]-used[j][i];
						used[j+i][i]+=set[j]-used[j][i];
					}
				}
				
				/*
				for(int k=0; k<tot+1; k++) {
					System.out.print(set[k] + " ");
				}
				System.out.println();
				
				
				for(int k=0; k<n+1; k++) {
					for(int l=0; l<tot+1; l++) {
						System.out.print(used[l][k] + " ");
					}
					System.out.println();
				}*/
			}
			System.out.println(set[sum]/2);
			out.write(set[sum]/2 + "\n");
			
		}
		
		out.close();
		System.exit(0);
	}
}
