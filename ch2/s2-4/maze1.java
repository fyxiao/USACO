/*
ID:
LANG: JAVA
TASK: maze1
*/

import java.io.*;
import java.util.*;

public class maze1 {
	
	public static int w, h, n;
	public static ArrayList<Integer>[] graph;
	public static ArrayList<Integer> list = new ArrayList<Integer>();
	public static int max = 0;
	public static boolean[] seen;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		n = w*h;
		graph = (ArrayList<Integer>[])new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		seen = new boolean[n+1];
		
		
		// fill in graph information
		for(int i=0; i<2*h+1; i++) {
			String line = f.readLine();
			//System.out.println(line);
			// wall line
			if(i%2==0) {
				for(int j=0; j<w; j++) {
					// walled
					if(line.charAt(2*j+1)=='-') {
					}
					// not walled
					else {
						int compA = (i/2-1)*w+j+1;
						int compB = (i/2)*w+j+1;
						if(i==0) { compA = 0; }
						if(i==2*h) { compB = 0; }
						graph[compA].add(compB);
						graph[compB].add(compA);
					}
				}
			}
			// column line
			else {
				if(line.length()<2*w+1) {
					int compA = (i+1)/2*w;
					graph[compA].add(0);
					graph[0].add(compA);
				}
				for(int j=0; j<(line.length()+1)/2; j++) {
					// walled
					
					if(line.charAt(2*j)=='|') {
					}
					// not walled
					else {
						int compA = (i-1)/2*w+j;
						int compB = (i-1)/2*w+j+1;
						if(j==0) { compA = 0; }
						if(j==w) { compB = 0; }
						graph[compA].add(compB);
						graph[compB].add(compA);
					}
				}
				for(int j=(line.length()+1)/2; j<w; j++) {
					int compA = (i-1)/2*w + j;
					int compB = (i-1)/2*w + j+1;
					if(j==0) { compA = 0; }
					if(j==w-1) { compB = 0; }
					graph[compA].add(compB);
					graph[compB].add(compA);
				}
			}
		}
		
		list.add(0);
		seen[0] = true;
		
		boolean cont = true;
		int count = 0;
		while(cont) {
			cont = iterate(count);
			count+=1;
		}
		System.out.println(max+1);
		out.write(max+1+"\n");
		
		out.close();
		System.exit(0);
	}
	
	public static boolean iterate(int level) {
		if(level > max) {
			max = level;
		}
		
		int size = list.size();
		for(int i=0; i<size; i++) {
			int cur = list.get(0);
			list.remove(0);
			for(int k=0; k<n+1; k++) {
				if((graph[cur].contains(k) && k!= cur) && seen[k]==false) {
					seen[k] = true;
					list.add(k);
				}
			}
		}
		
		for(int i=0; i<n+1; i++) {
			if(seen[i]==false) {
				return true;
			}
		}
		return false;
	}

}
