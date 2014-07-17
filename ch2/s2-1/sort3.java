/*
ID:
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.*;

public class sort3 {

	private static int[] seq;
	private static int[] sorted;
	private static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		
		n = Integer.parseInt(f.readLine());
		seq = new int[n];
		sorted = new int[n];
		
		int numOne = 0;
		int numTwo = 0;
		int numThree = 0;
		int cpA, cpB;
		
		for(int i=0; i<n; i++) {
			seq[i] = Integer.parseInt(f.readLine());
			if(seq[i]==1) { numOne++; }
			if(seq[i]==2) { numTwo++; }
			if(seq[i]==3) { numThree++; }
		}
		
		cpA = numOne;
		cpB = numOne + numTwo;
		
		// fill out sorted array
		for(int i=0; i<numOne; i++) {
			sorted[i] = 1;
		}
		for(int i=cpA; i<cpB; i++) {
			sorted[i] = 2;
		}
		for(int i=cpB; i<n; i++) {
			sorted[i] = 3;
		}
		
		int eOne=0; // number of 1's that must be moved
		int oneTwo = 0; // number of 1's in the 2's slot
		int twoOne = 0; // number of 2's in the 1's slot
		int threeTwo = 0; // number of 3's in the 2's slot
		// correct the 1's
		for(int i=0; i<cpA; i++) {
			if(seq[i] == 2) {
				twoOne++;
			}
		}
		for(int i=cpA; i<cpB; i++) {
			if(seq[i] == 1) {
				eOne++;
				oneTwo++;
			}
			if(seq[i] == 3) {
				threeTwo++;
			}
		}
		for(int i=cpB; i<n; i++) {
			if(seq[i] == 1) {
				eOne++;
			}
		}
		
		int swap = eOne + threeTwo + Math.max(0, oneTwo - twoOne);
		System.out.println(swap);
		out.write(swap + "\n");

		out.close();
		System.exit(0);
	}
	
	
	
	
	
	public static boolean isSorted(int[] test) {
		for(int i=0; i<n; i++) {
			if(test[i] != sorted[i]) {
				return false;
			}
		}
		return true;
	}
}
