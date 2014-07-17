/*
ID: 
LANG: JAVA
TASK: kimbits
 */

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class kimbits {
	public static int n, l;
	public static long k;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Long.parseLong(st.nextToken());
		
		String bitseq = "";
		for(int i=0; i<n; i++) {
			if(binomsum(n-1-i, Math.min(l, n-1-i)) < k) {
				bitseq += "1";
				k -= binomsum(n-1-i, l);
				l--;
			}
			else {
				bitseq += "0";
			}
		}
		System.out.println(bitseq);
		out.write(bitseq + "\n");

		
		out.close();
		System.exit(0);
	}
	
	/* return the \sum_{i=0}^{k} \binom{n}{i} */
	public static int binomsum(int n, int k) {
		int sum = 0;
		for(int i=0; i<=k; i++) {
			sum += choose(n, i).intValue();
		}
		return sum;
	}
	
	public static BigInteger choose(int x, int y) {
	    if (y < 0 || y > x) {
	    	return BigInteger.ZERO;
	    }
	    if (y == 0 || y == x) {
	    	return BigInteger.ONE;
	    }

	    BigInteger answer = BigInteger.ONE;
	    for (int i = x - y + 1; i <= x; i++) {
	        answer = answer.multiply(BigInteger.valueOf(i));
	    }
	    for (int j = 1; j <= y; j++) {
	        answer = answer.divide(BigInteger.valueOf(j));
	    }
	    return answer;

	}
}
