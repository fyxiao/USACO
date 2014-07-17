/*
ID: 
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.*;

public class comehome {
	
	public static int[][] graph = new int[52][52];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		
		// set default graph distances
		for(int i=0; i<52; i++) {
			for(int j=0; j<52; j++) {
				graph[i][j] = 27*1001; // shortest path can't be greater than this
			}
		}
		
		// set up graph
		int P = Integer.parseInt(f.readLine());
		for(int i=0; i<P; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int compA = getNum(st.nextToken());
			int compB = getNum(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[compA][compB] = Math.min(graph[compA][compB], d);
			graph[compB][compA] = Math.min(graph[compB][compA], d);
		}
		
		for(int i=0; i<52; i++) {
			graph[i][i] = 0;
		}
		
		/*
		for(int i=0; i<52; i++) {
			for(int j=0; j<52; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}*/
		
		
		// run Floyd-Warshall
		for(int k=0; k<52; k++) {
			for(int i=0; i<52; i++) {
				for(int j=0; j<52; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
				}
			}
		}
		
		// find shortest distance
		int min = 27*1001;
		int index = -1;
		for(int i=0; i<25; i++) {
			if(graph[25][i]<min) {
				min = graph[25][i];
				index = i;
			}
			
		}
		
		char letter;
		String a = "A";
		letter = (char)((int)a.charAt(0)+index);
		
		System.out.println(letter + " " + min);
		out.write(letter + " " + min + "\n");
		
		out.close();
		System.exit(0);
	}
	
	public static int getNum(String s) {
		if((int)(s.charAt(0) - 'a')<0) {
			return (int)(s.charAt(0) - 'A');
		}
		else {
			return (int)(s.charAt(0) - 'a') + 26;
		}
	}


}
