/*
ID: 
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

/* comparator for fractions in String form */

class FracComparator implements Comparator {
	/* method to compare two fractions in String form */
	public int compare (Object a, Object b) {
		String f1 = (String)a;
		String f2 = (String)b;
		StringTokenizer st1 = new StringTokenizer(f1, "/");
		StringTokenizer st2 = new StringTokenizer(f2, "/");
		int p1 = Integer.parseInt(st1.nextToken());
		int q1 = Integer.parseInt(st1.nextToken());
		int p2 = Integer.parseInt(st2.nextToken());
		int q2 = Integer.parseInt(st2.nextToken());
		if(p1*q2 > p2*q1) { return 1; }
		else if(p1*q2 < p2*q1) { return -1; }
		else { return 0; }
	}
}

public class frac1 {
	
	private static ArrayList<Integer> primes = new ArrayList<Integer>();
	private static ArrayList<String> fracs = new ArrayList<String>();
	private static int n;
	private static PrintWriter out;
	
	/* main method */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		
		n = Integer.parseInt(f.readLine());
		primes.add(2);
		
		for(int i=3; i<n+1; i++) {
			addPrime(i);
		}

		for(int i=2; i<n+1; i++) {
			for(int j=1; j<i; j++) {
				if(isReduced(j, i)) {
					fracs.add(j + "/" +i);
				}
			}
		}
		out.write("0/1" + "\n");
		Collections.sort(fracs, new FracComparator());
		for(int i=0; i<fracs.size(); i++) {
			out.write(fracs.get(i) + "\n");
		}
		out.write("1/1" + "\n");
		
		
		out.close();
		System.exit(0);
	}
	
	public static void addPrime(int m) {
		for(int i=0; i<primes.size() && primes.get(i) < Math.sqrt(m)+1; i++) {
			if(m%primes.get(i)==0) {
				return;
			}
		}
		primes.add(m);
	}
	
	/* is a fraction reduced */
	public static boolean isReduced(long p, long q) {
		for(int i=0; i<primes.size(); i++) {
			int div = primes.get(i);
			if(p%div == 0 && q%div == 0) { return false; }
		}
		return true;
	}
}
