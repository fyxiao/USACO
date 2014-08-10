/*
ID: 
LANG: JAVA
TASK: packrec
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class packrec {
	private static int MIN = 4*50*4*50 + 1;
	private static ArrayList<Integer> wids = new ArrayList<Integer>(); // widths corresponding to enclosing rectangles
	
	private static class Rect 
	{
		int w, h;
		public Rect(int width, int height) 
		{
			w = width;
			h = height;
		}
	}
	
	private static void rotate(Rect r) 
	{
		int temp;
		temp = r.w; r.w = r.h; r.h = temp;
	}
	
	private static void swap(int[] a, int i, int j)
	{
		int temp;
		temp = a[i]; a[i] = a[j]; a[j] = temp;
		return;
	}
	
	private static void genPerms(ArrayDeque<int[]> perms, int[] perm)
	{
		genPerms2(perms, perm, 4);
	}
	
	private static void genPerms2(ArrayDeque<int[]> perms, int[] perm, int k)
	{
		if (k==1) {
			int[] res = new int[4];
			System.arraycopy(perm, 0, res, 0, 4);
			perms.add(res);
			return;
		}
		for (int i=0; i<k; i++) {
			swap(perm, i, k-1);
			genPerms2(perms, perm, k-1);
			swap(perm, i, k-1);
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"packrec.out")));
		StringTokenizer st;
		// rectangle
		Rect[] rects = new Rect[4];
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(f.readLine());
			rects[i] = new Rect(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		
		// iterate over all permutations
		ArrayDeque<int[]> perms = new ArrayDeque<int[]>();
		int[] perm = new int[] {0, 1, 2, 3};
		genPerms(perms, perm);
		
		while (perms.size() > 0) {
			int[] order = perms.poll();
			// iterate through all rotations
			for (int i=0; i<=0xf; i++) {
				Rect[] testRects = new Rect[4];
				for (int j=0; j<4; j++) {
					testRects[j] = new Rect(rects[order[j]].w, rects[order[j]].h);
					if ((i & (1 << j)) > 0) rotate(testRects[j]);
				}
				// test out all arrangements
				case1(testRects[0], testRects[1], testRects[2], testRects[3]);
				case2(testRects[0], testRects[1], testRects[2], testRects[3]);
				case3(testRects[0], testRects[1], testRects[2], testRects[3]);
				case4(testRects[0], testRects[1], testRects[2], testRects[3]);
				case5(testRects[0], testRects[1], testRects[2], testRects[3]);
			}
		}
											
		Collections.sort(wids);
		out.write(MIN+"\n");
		for(int i=0; i<wids.size(); i++)
			out.write(wids.get(i) + " " + MIN/wids.get(i) + "\n");	
		
		f.close();
		out.close();
		System.exit(0);
	}
	
	// r1, r2, r3, r4 all vertical
	private static void case1(Rect r1, Rect r2, Rect r3, Rect r4) {
		int w= r1.w + r2.w + r3.w + r4.w;
		int h = Math.max(Math.max(r1.h, r2.h), Math.max(r3.h, r4.h));
		if(w * h < MIN) { 
			MIN = w * h;
			wids.clear();
			wids.add(Math.min(w, h));
		}
		else if (w*h == MIN && !wids.contains(Math.min(w, h)))
			wids.add(Math.min(w, h));
	}
	
	// r1, r2, r3 vertical on top of r4
	private static void case2(Rect r1, Rect r2, Rect r3, Rect r4) {
		int w = Math.max(r1.w + r2.w + r3.w, r4.w);
		int h = Math.max(Math.max(r1.h, r2.h), r3.h) + r4.h;
		if(w*h < MIN) { 
			MIN = w*h;
			wids.clear();
			wids.add(Math.min(w, h));
		}
		else if(w*h == MIN && !wids.contains(Math.min(w, h)))
			wids.add(Math.min(w, h));
	}
	
	// r1, r2 vertical on top of r3, r4 vertical
	private static void case3(Rect r1, Rect r2, Rect r3, Rect r4) {
		int w = Math.max(r1.w + r2.w, r3.w) + r4.w;
		int h = Math.max(Math.max(r1.h, r2.h) + r3.h, r4.h);
		if(w * h < MIN) { 
			MIN = w * h;
			wids.clear();
			wids.add(Math.min(w, h));
		}
		else if(w * h == MIN && !wids.contains(Math.min(w, h)))
			wids.add(Math.min(w, h));
	}
	
	// r1, r2 stacked vertically, r3 and r4 stacked on top
	private static void case4(Rect r1, Rect r2, Rect r3, Rect r4) {
		int w = r1.w + r2.w + Math.max(r3.w, r4.w);
		int h = Math.max(Math.max(r1.h, r2.h), r3.h+r4.h);
		if(w * h < MIN) { 
			MIN = w * h;
			wids.clear();
			wids.add(Math.min(w, h));
		}
		else if(w * h == MIN && !wids.contains(Math.min(w, h)))
			wids.add(Math.min(w, h));
	}
	
	// r1, r2 stacked vertically, r3 and r4 stacked vertically
	private static void case5(Rect r1, Rect r2, Rect r3, Rect r4) {
		int w = r1.w + r3.w;
		int h = Math.max(r1.h + r2.h, r3.h + r4.h);
		// if 2 and 3 touch
		if(r1.h < r3.h)
			w = Math.max(w, r2.w + r3.w);
		// if 2 and 4 touch
		if(r1.h < r3.h+r4.h && r1.h + r2.h > r3.h)
			w = Math.max(w, r2.w + r4.w);
		// if 1 and 4 touch
		if(r1.h > r3.h)
			w = Math.max(w, r1.w + r4.w);
		// 2 or 4 sits by itself
		w = Math.max(w, Math.max(r2.w, r4.w));
		if(w * h < MIN) { 
			MIN = w * h;
			wids.clear();
			wids.add(Math.min(w, h));
		}
		else if(w * h == MIN && !wids.contains(Math.min(w, h)))
			wids.add(Math.min(w, h));
	}
}
