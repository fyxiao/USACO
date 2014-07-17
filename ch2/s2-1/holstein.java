/*
ID: 
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.*;

public class holstein {
	
	private static int v, g;
	private static int[] needs;
	private static int[][] feeds;
	private static boolean found = false;
	private static ArrayList<int[]> list = new ArrayList<int[]>();
	private static ArrayList<int[]> feedsUsed = new ArrayList<int[]>();
	private static int number = 0; // # feeds used
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		
		v = Integer.parseInt(f.readLine());
		needs = new int[v];
		StringTokenizer st = new StringTokenizer(f.readLine());
		// vitamin needs
		for(int i=0; i<v; i++) {
			needs[i] = Integer.parseInt(st.nextToken());
		}
		g = Integer.parseInt(f.readLine());
		feeds = new int[g][v];
		// fill out feeds
		for(int i=0; i<g; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=0; j<v; j++) {
				feeds[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] start = new int[v];
		int[] startFeed = new int[g];
		list.add(start);
		feedsUsed.add(startFeed);
		feedC(0);

		System.out.println(number+1);
		int[] output = feedsUsed.get(feedsUsed.size()-1);
		for(int i=0; i<number; i++) {
			System.out.print(output[i]);
		}
		System.out.println(output[number]);
		
		out.write(number+1 + " ");
		for(int i=0; i<number; i++) {
			out.write(output[i] + " ");
		}
		out.write(output[number] + "\n");
		
		
		out.close();
		System.exit(0);
	}
	
	
	public static void feedC(int level) {
		if(level == g || found == true) {
			return;
		}
		int n = list.size();
		for(int i=0; i<n; i++) {
			int[] oldState = list.get(0);
			list.remove(0);
			int[] used = feedsUsed.get(0);
			feedsUsed.remove(0);
			
			int offset = level;
			if(level >= 1) {
				offset = used[level-1];
			}
			/*
			for(int x=0; x<g-1; x++) {
				System.out.print(used[x] + " ");
			}
			System.out.println(used[g-1]);*/
			//System.out.println("level is " + level);
			
			for(int j=offset; j<g; j++) {
					int[] newState = new int[v];

					for(int k=0; k<v; k++) {
						newState[k] = oldState[k] + feeds[j][k];
					}
					list.add(newState);
					
					int[] newUsed = new int[g];
					for(int k=0; k<level+1; k++) {
						newUsed[k] = used[k];
					}
					newUsed[level] = j+1;
					
					feedsUsed.add(newUsed);
					
					
					if(isFull(newState)) {
						for(int m=0; m<v-1; m++) {
							System.out.print(newState[m] + " ");
						}
						System.out.println(newState[v-1]);
						found = true;
						return;
					}

			}
		}
		level++;
		number++;
		feedC(level);
	}
	
	public static boolean isFull(int[] test) {
		for(int i=0; i<v; i++) {
			if(needs[i]>test[i]) {
				return false;
			}
		}
		return true;
	}
}
