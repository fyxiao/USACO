/*
ID:
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.*;

public class stamps {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		int n, k;
		int[] coins;
		StringTokenizer st = new StringTokenizer(f.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		int[] config = new int[k*10000+1];
		coins = new int[n];
		
		int count = 0;
		while(f.ready()) {
			st = new StringTokenizer(f.readLine());
			while(st.hasMoreTokens()) {
				coins[count] = Integer.parseInt(st.nextToken());
				count++;
			}
		}
		
		// run DP algorithm
		for(int i=1; i<k*10000+1; i++) {
			boolean canMake = false;
			for(int j=0; j<n; j++) {
				// make sure coin value isn't greater than total
				if(i-coins[j]>=0) {
					if(config[i-coins[j]]+1<=k) {
						if(config[i]==0) {
							config[i] = config[i-coins[j]]+1;
						}
						else {
							config[i] = Math.min(config[i], config[i-coins[j]]+1);
						}
						canMake = true;
					}
				}
			}
			// couldn't make i with change
			if(!canMake) {
				System.out.println(i-1);
				out.write((i-1)+"\n");
				break;
			}
		}
		
		out.close();
		System.exit(0);
	}

}
