/*
ID: 
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.*;

public class humble {
	
	public static long[] humbles;
	public static long[] smallest;
	public static int k;
	public static long curhumble = 1;
	public static int[] primes;
	public static int[] last;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		primes = new int[k];
		smallest = new long[k];
		humbles = new long[n+1];
		last = new int[k];
		
		st = new StringTokenizer(f.readLine());
		for(int i=0; i<k; i++) {
			primes[i] = Integer.parseInt(st.nextToken());
			smallest[i] = primes[i];
		}
		
		humbles[0] = 1;
		
		for(int i=1; i<n+1; i++) {
			int index = getNextIndex();
			curhumble = smallest[index];
			humbles[i] = curhumble;
			update(index, i+1);
			// comment
		}
		
		System.out.println(humbles[n]);
		out.write(humbles[n] + "\n");
		out.close();
		System.exit(0);
	}
	
	public static int getNextIndex() {
		int sindex = 0;
		long newhumble = -1;
		for(int j=0; j<k; j++) {
			long cursmall = smallest[j];
			if(j==0) {
				newhumble = cursmall;
			}
			if(cursmall < newhumble) {
				newhumble = cursmall;
				sindex = j;
			}
		}
		return sindex;
	}
	
	public static void update(int index, int num) {
		long newp = -1;
		for(int j=last[index]; j<num; j++) {
			newp = humbles[j] * primes[index];
			if((!contains(newp)) && newp>curhumble) {
				last[index] = j+1;
				break;
			}
		}
		smallest[index] = newp;
	}
	
	public static boolean contains(long newp) {
		boolean result = false;
		for(int i=0; i<k; i++) {
			if(smallest[i]==newp) {
				result = true;
				break;
			}
		}
		return result;
	}

}
