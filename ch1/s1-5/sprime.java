/*
ID:
LANG: JAVA
TASK: sprime
 */

import java.util.*;
import java.io.*;

public class sprime {
	
	public static ArrayList<Integer> list = new ArrayList<Integer>();
	public static int[] prime = {2, 3, 5, 7};

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		generate(n);
		
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
			out.write(list.get(i) + "\n");
		}
		
		out.close();
		System.exit(0);
	}

	public static boolean isPrime(int n) {
		if(n==1 || n==0) { return false; }
		if(n==2) { return true; }
		for(int i = 2; i<(int)Math.sqrt(n) + 1; i++) {
			if(n%i == 0) { return false; }
		}
		return true;
	}
	
	public static void generate(int n) {
		if(n==1) { oneprime(); }
		if(n==2) { twoprime(); }
		if(n==3) { threeprime(); }
		if(n==4) { fourprime(); }
		if(n==5) { fiveprime(); }
		if(n==6) { sixprime(); }
		if(n==7) { sevenprime(); }
		if(n==8) { eightprime(); }
	}

	public static void oneprime() {
		for(int i=0; i<4; i++) {
			list.add(prime[i]);
		}
	}
	
	public static void twoprime() {
		for(int d1=0; d1<10; d1++) {
			for(int d2=0; d2<4; d2++) {
				int test = d1 + 10*prime[d2];
				if(isPrime(test)) {
					list.add(test);
				}
			}
		}
	}
	
	public static void threeprime() {
		for(int d1=0; d1<10; d1++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					int test = d1 + 10*d2 + 100*prime[d3];
					int test2 = (test - d1)/10;
					if(isPrime(test) && isPrime(test2)) {
						list.add(test);
					}
				}
			}
		}
	}
	
	public static void fourprime() {
		for(int d1=0; d1<10; d1++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					for(int d4=0; d4<4; d4++) {
						int test = d1 + 10*d2 + 100*d3 + 1000*prime[d4];
						int test2 = (test - d1)/10;
						int test3 = (test2 - d2)/10;
						if(isPrime(test) && isPrime(test2) && isPrime(test3)) {
							list.add(test);
						}
					}
				}
			}
		}
	}
	
	public static void fiveprime() {
		for(int d1=0; d1<10; d1++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					for(int d4=0; d4<10; d4++) {
						for(int d5=0; d5<4; d5++) {
							int test = d1 + 10*d2 + 100*d3 + 1000*d4 + 10000*prime[d5];
							int test2 = (test - d1)/10;
							int test3 = (test2 - d2)/10;
							int test4 = (test3-d3)/10;
							if(isPrime(test) && isPrime(test2) && isPrime(test3) && isPrime(test4)) {
								list.add(test);
							}
						}
					}
				}
			}
		}
	}
	
	public static void sixprime() {
		for(int d1=0; d1<10; d1++) {
			for(int d2=0; d2<10; d2++) {
				for(int d3=0; d3<10; d3++) {
					for(int d4=0; d4<10; d4++) {
						for(int d5=0; d5<10; d5++) {
							for(int d6=0; d6<4; d6++) {
								int test = d1 + 10*d2 + 100*d3 + 1000*d4 + 10000*d5 + 100000*prime[d6];
								int test2 = (test - d1)/10;
								int test3 = (test2 - d2)/10;
								int test4 = (test3-d3)/10;
								int test5 = (test4 -d4)/10;
								if(isPrime(test) && isPrime(test2) && isPrime(test3) && isPrime(test4) && isPrime(test5)) {
									list.add(test);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void sevenprime() {
		ArrayList<Integer> sprimes = new ArrayList<Integer>();
		sprimes.add(2);
		sprimes.add(3);
		sprimes.add(5);
		sprimes.add(7);
		
		
		for(int i=1; i<7; i++) {
			int curcap = sprimes.size();
			for(int j=0; j<curcap; j++) {
				for(int d1=0; d1<10; d1++) {
					int test = sprimes.get(j) * 10 + d1;
					if(isPrime(test)) { 
						sprimes.add(test);
					}
				}
			}
			for(int j=0; j<curcap; j++) {
				sprimes.remove(0);
			}
		}
		
		for(int i=0; i<sprimes.size(); i++) {
			list.add(sprimes.get(i));
		}
		
	}
	
	public static void eightprime() {
		ArrayList<Integer> sprimes = new ArrayList<Integer>();
		sprimes.add(2);
		sprimes.add(3);
		sprimes.add(5);
		sprimes.add(7);
		
		
		for(int i=1; i<8; i++) {
			int curcap = sprimes.size();
			for(int j=0; j<curcap; j++) {
				for(int d1=0; d1<10; d1++) {
					int test = sprimes.get(j) * 10 + d1;
					if(isPrime(test)) { 
						sprimes.add(test);
					}
				}
			}
			for(int j=0; j<curcap; j++) {
				sprimes.remove(0);
			}
		}
		
		for(int i=0; i<sprimes.size(); i++) {
			list.add(sprimes.get(i));
		}
		
	}
	
}
