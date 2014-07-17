/*
ID:
LANG: JAVA
TASK: rockers
*/

import java.io.*;
import java.util.*;

public class rockers {
	public static int n, t, m;
	public static int[] songs;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		n=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		songs=new int[n+1];
		st=new StringTokenizer(f.readLine());
		for(int i=0; i<n; i++) {
			songs[i+1]=Integer.parseInt(st.nextToken());
		}
		// run dp
		int leftover=m; // leftover space in last box filled
		int max=numSongs(1, t, 1);
		System.out.println(max);
		out.write(max+"\n");
		out.close();
		System.exit(0);
	}
	
	public static int numSongs(int song, int cap, int numCDs) {
		if(song>n || numCDs>m) {
			return 0;
		}
		else {
			int test1=0;
			int test2=0;
			int test3=0;
			/* Case where you don't use song. */
			test1=numSongs(song+1, cap, numCDs);
			/* Case where you fit song on current CD */
			if(songs[song]<=cap) {
				test2=1+numSongs(song+1, cap-songs[song], numCDs);
			}
			/* Case where you put song on next CD */
			else {
				if(numCDs<m && songs[song]<=t) {
					test3=1+numSongs(song+1, t-songs[song], numCDs+1);
				}
			}
			return Math.max(Math.max(test1, test2), test3);
		}
	}

}
