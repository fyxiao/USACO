/*
ID:
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.*;

public class pprime {
	
	public static ArrayList<Integer> list = new ArrayList<Integer>();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		onedrome(a, b);
		twodrome(a, b);
		threedrome(a, b);
		fourdrome(a, b);
		fivedrome(a, b);
		sixdrome(a, b);
		sevendrome(a, b);
		eightdrome(a, b);
			
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
			out.write(list.get(i) + "\n");
		}
		
		out.close();
		System.exit(0);
	}
	
	public static void onedrome(int a, int b) {
		if(b < 1) { return; }
		for(int i=2; i<10; i++) {
			if(isPrime(i) && i>=a && i<=b) { list.add(i); }
		}
	}
	
	public static void twodrome(int a, int b) {
		if(b < 10) { return; }
		for(int d=1; d<10; d++) {
			int test = 10*d + d;
			if(isPrime(test) && test>=a && test<=b) { list.add(test); }
		}
	}
	
	public static void threedrome(int a, int b) {
		if(b < 100) { return; }
		for(int d=1; d<10; d++) {
			for(int d2=0; d2<10; d2++) {
				int test = 100*d + 10*d2 + d;
				if(isPrime(test) && test>=a && test<=b) { list.add(test); }
			}
		}
	}
	
	public static void fourdrome(int a, int b) {
		if(b < 1000) { return; }
		for(int d=1; d<10; d++) {
			for(int d2=0; d2<10; d2++) {
				int test = 1000*d +100*d2 + 10*d2 + d;
				if(isPrime(test) && test>=a && test<=b) { list.add(test); }
			}
		}
	}
	
	public static void fivedrome(int a, int b) {
		if(b <10000) { return; }
		for(int d=1; d<10; d++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					int test = 10000*d +1000*d2 + 100*d3 + 10*d2 + d;
					if(isPrime(test) && test>=a && test<=b) { list.add(test); }
				}
			}
		}
	}
	
	public static void sixdrome(int a, int b) {
		if(b <100000) { return; }
		for(int d=1; d<10; d++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					int test = 100000*d +10000*d2 + 1000*d3 + 100*d3 + 10*d2 + d;
					if(isPrime(test) && test>=a && test<=b) { list.add(test); }
				}
			}
		}
	}
	
	public static void sevendrome(int a, int b) {
		if(b <1000000) { return; }
		for(int d=1; d<10; d++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					for(int d4=0; d4<10; d4++) {
						int test = 1000000*d +100000*d2 + 10000*d3 + 1000*d4 +100*d3 + 10*d2 + d;
						if(isPrime(test) && test>=a && test<=b) { list.add(test); }
					}
				}
			}
		}
	}
	
	public static void eightdrome(int a, int b) {
		if(b <10000000) { return; }
		for(int d=1; d<10; d++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					for(int d4=0; d4<10; d4++) {
						int test = 10000000*d +1000000*d2 + 100000*d3 + 10000*d4+1000*d4 +100*d3 + 10*d2 + d;
						if(isPrime(test) && test>=a && test<=b) { list.add(test); }
					}
				}
			}
		}
	}
	
	public static boolean isPrime(int n) {
		if(n==1 || n==0) { return false; }
		if(n==2) { return true; }
		for(int i = 2; i<(int)Math.sqrt(n) + 1; i++) {
			if(n%i == 0) { return false; }
		}
		return true;
	}

}
