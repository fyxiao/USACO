/*
ID:
LANG: JAVA
TASK: packrec
*/

import java.io.*;
import java.util.*;

class Rect {
	int length;
	int width;
	public Rect(int len, int wid) {
		length = len;
		width = wid;
	}
}

public class packrec {
	
	public static int cur = 4*50*4*50;
	private static ArrayList<Integer> wids = new ArrayList<Integer>(); // widths corresponding to enclosing rectangles
	
	private static void rotate(Rect r) {
		int temp = r.length;
		r.length = r.width;
		r.width = temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
		
		StringTokenizer st;
		// rectangle
		Rect[] rects = new Rect[4];
		int[] olen = new int[4];
		int[] owid = new int[4];
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			rects[i] = new Rect(a, b);
			olen[i] = a;
			owid[i] = b;
			
		}
		
		// iterate over all permutations
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(j != i) {
					for(int k=0; k<4; k++) {
						if(k != i && k!= j) {
							for(int l=0; l<4; l++) {
								if(l!=i && l!=j && l!=k ) {

									// iterate over all rotations
									for(int a=0; a<2; a++) {
										for(int b=0; b<2; b++) {
											for(int c=0; c<2; c++) {
												for(int d=0; d<2; d++) {
									
													// restore to original
													for(int m=0; m<4; m++) {
														rects[m].length = olen[m];
														rects[m].width = owid[m];
													}
													
													// rotate
													if(a == 1) { rotate(rects[i]); }
													if(b == 1) { rotate(rects[j]); }
													if(c == 1) { rotate(rects[k]); }
													if(d == 1) { rotate(rects[l]); }
													
													// test all arrangements
													case1(rects[i], rects[j], rects[k], rects[l]);
													case2(rects[i], rects[j], rects[k], rects[l]);
													case3(rects[i], rects[j], rects[k], rects[l]);
													case4(rects[i], rects[j], rects[k], rects[l]);
													case5(rects[i], rects[j], rects[k], rects[l]);
												}
										
											}
										}
									}
									// close rotation cycling
								}
							}
						}
					}
				}
			}
		}

		out.write(cur + "\n");
		Collections.sort(wids);
		for(int i=0; i<wids.size(); i++) {
			out.write(wids.get(i) + " " + cur/wids.get(i) + "\n");
		}
		
		out.close();
		System.exit(0);
	}
	
	// r1, r2, r3, r4 all vertical
	public static void case1(Rect r1, Rect r2, Rect r3, Rect r4) {
		int length = Math.max(Math.max(r1.length, r2.length), Math.max(r3.length, r4.length));
		int width = r1.width + r2.width + r3.width + r4.width;
		int temp = Math.min(length, width);
		if(length * width < cur) { 
			cur = length * width;
			wids.clear();
			wids.add(temp);
			//System.out.println(cur + " " + temp + " case1");
		}
		if(length * width == cur && !wids.contains(temp)) {
			wids.add(temp);
		}
	}
	
	// r1, r2, r3 vertical on top of r4
	public static void case2(Rect r1, Rect r2, Rect r3, Rect r4) {
		int length = Math.max(Math.max(r1.length, r2.length), r3.length) + r4.length;
		int width = Math.max(r1.width + r2.width + r3.width, r4.width);
		int temp = Math.min(length, width);
		if(length * width < cur) { 
			cur = length * width;
			wids.clear();
			wids.add(temp);
			//System.out.println(cur + " " + temp + " case2");
		}
		if(length * width == cur && !wids.contains(temp)) {
			wids.add(temp);
		}
	}
	
	// r1, r2 vertical on top of r3, r4 vertical
	public static void case3(Rect r1, Rect r2, Rect r3, Rect r4) {
		int length = Math.max(Math.max(r1.length, r2.length) + r3.length, r4.length);
		int width = Math.max(r1.width + r2.width, r3.width) + r4.width;
		int temp = Math.min(length, width);
		if(length * width < cur) { 
			cur = length * width;
			wids.clear();
			wids.add(temp);
			//System.out.println(cur + " " + temp + " case3");
		}
		if(length * width == cur && !wids.contains(temp)) {
			wids.add(temp);
		}
	}
	
	// r1, r2 stacked vertically, r3 and r4 stacked on top
	public static void case4(Rect r1, Rect r2, Rect r3, Rect r4) {
		int length = Math.max(Math.max(r1.length, r2.length), r3.length+r4.length);
		int width = r1.width + r2.width + Math.max(r3.width, r4.width);
		int temp = Math.min(length, width);
		
		/*
		if(r1.length == 20 && r2.length == 19 && r3.length == 4 && r4.length == 17) {
			System.out.println("found it");
		}*/
		
		if(length * width < cur) { 
			cur = length * width;
			wids.clear();
			wids.add(temp);
			//System.out.println(cur + " " + temp + " case4");
		}
		if(length * width == cur && !wids.contains(temp)) {
			wids.add(temp);
		}
	}
	
	// r1, r2 stacked vertically, r3 and r4 stacked vertically
	
	/* 2, 4
	 * 1, 3
	 */
	public static void case5(Rect r1, Rect r2, Rect r3, Rect r4) {
		int length = Math.max(r1.length + r2.length, r3.length + r4.length);
		int width = r1.width + r3.width;
		
		// if 2 and 3 touch
		if(r1.length < r3.length) {
			width = Math.max(width, r2.width + r3.width);
		}
		// if 2 and 4 touch
		if(r1.length + r2.length > r3.length) {
			width = Math.max(width, r2.width + r4.width);
		}
		// if 1 and 4 touch
		if(r1.length > r3.length) {
			width = Math.max(width, r1.width + r4.width);
		}
		// 2 or 4 sits by itself
		width = Math.max(width, r2.width);
		width = Math.max(width, r4.width);
		
		int temp = Math.min(length, width);
		if(length * width < cur) { 
			cur = length * width;
			wids.clear();
			wids.add(temp);
			//System.out.println(cur + " " + temp + " case5");
		}
		if(length * width == cur && !wids.contains(temp)) {
			wids.add(temp);
		}
	}
	

}
