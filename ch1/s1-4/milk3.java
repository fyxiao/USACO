/*
ID:
LANG: JAVA
TASK: milk3
*/

import java.io.*;
import java.util.*;

public class milk3 {
	
	// bucket capacities
	public static int a = 0;
	public static int b = 0;
	public static int c = 0;
	public static boolean[][] seen;
	public static ArrayList<Integer> list = new ArrayList<Integer>();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		seen = new boolean[b+1][c+1];
		
		// test
		//System.out.println("the values of a, b, c are " + a + " " + b + " " + c);
		//pourCA(0, 0, c);		
		
		
		pour(0, 0, c);
		Collections.sort(list);
		for(int i=0; i<list.size()-1; i++) {
			out.write(list.get(i) + " ");
		}
		out.write(list.get(list.size()-1) + "\n");
		out.close();
		System.exit(0);

	}
	
	// main pouring method
	public static void pour(int an, int bn, int cn) {
		pourAB(an, bn, cn);
		pourAC(an, bn, cn);
		pourBA(an, bn, cn);
		pourBC(an, bn, cn);
		pourCA(an, bn, cn);
		pourCB(an, bn, cn);
	}
	
	// different pour functions, 6 total
	public static void pourAB(int an, int bn, int cn) {
		while(an>0 && bn < b) {
			an--;
			bn++;
		}
		if(an == 0 && !(list.contains(cn))) {
			list.add(cn);
		}
		//System.out.println("Poured from A to B (" + an + ", " + bn + ", " + cn);
		if(!seen[bn][cn]) {
			seen[bn][cn] = true;
			pour(an, bn, cn);
		}
	}
	
	public static void pourAC(int an, int bn, int cn) {
		while(an>0 && cn < c) {
			an--;
			cn++;
		}
		if(an == 0 && !(list.contains(cn))) {
			list.add(cn);
		}		
		//System.out.println("Poured from A to C (" + an + ", " + bn + ", " + cn);
		if(!seen[bn][cn]) {
			seen[bn][cn] = true;
			pour(an, bn, cn);
		}	
	}
	
	public static void pourBA(int an, int bn, int cn) {
		while(bn>0 && an < a) {
			bn--;
			an++;
		}

		if(an == 0 && !(list.contains(cn))) {
			list.add(cn);
		}		
		//System.out.println("Poured from B to A (" + an + ", " + bn + ", " + cn);
		if(!seen[bn][cn]) {
			seen[bn][cn] = true;
			pour(an, bn, cn);
		}	
	}
	
	public static void pourBC(int an, int bn, int cn) {
		while(bn>0 && cn < c) {
			bn--;
			cn++;
		}
		if(an == 0 && !(list.contains(cn))) {
			list.add(cn);
		}		
		//System.out.println("Poured from B to C (" + an + ", " + bn + ", " + cn);
		if(!seen[bn][cn]) {
			seen[bn][cn] = true;
			pour(an, bn, cn);
		}	
	}
	
	public static void pourCA(int an, int bn, int cn) {
		while(cn>0 && an < a) {
			cn--;
			an++;
		}
		if(an == 0 && !(list.contains(cn))) {
			list.add(cn);
		}		
		//System.out.println("Poured from C to A (" + an + ", " + bn + ", " + cn);
		if(!seen[bn][cn]) {
			seen[bn][cn] = true;
			pour(an, bn, cn);
		}		
	}
	
	public static void pourCB(int an, int bn, int cn) {
		while(cn>0 && bn < b) {
			cn--;
			bn++;
		}
		if(an == 0 && !(list.contains(cn))) {
			list.add(cn);
		}
		//System.out.println("Poured from C to B (" + an + ", " + bn + ", " + cn);
		if(!seen[bn][cn]) {
			seen[bn][cn] = true;
			pour(an, bn, cn);
		}
	}
	

}
