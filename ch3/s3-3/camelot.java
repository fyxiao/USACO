/*
ID:
LANG: JAVA
TASK: camelot
*/

import java.io.*;
import java.util.*;

class vertex1 {
	int x, y;
	public vertex1(int a, int b) {
		x=a;
		y=b;

	}
}

public class camelot {
	public static int r, c, num, kx, ky, MAX=900*900;
	public static int shortest=MAX;
	public static String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static int[][] distances;
	public static int[][] kingds;
	public static int[] knightds;
	public static int[][] knightlocs;
	public static int []mx={-2,-2,-1,-1,1,1,2,2};
	public static int []my={-1,1,-2,2,-2,2,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		ArrayList<String> locs = new ArrayList<String>();
		while(f.ready()) {
			st=new StringTokenizer(f.readLine());
			while(st.hasMoreTokens()) {
				int xcoor=alphabet.indexOf(st.nextToken());
				int ycoor=Integer.parseInt(st.nextToken())-1;
				locs.add(xcoor+"/"+ycoor);
			}
		}
		num=locs.size()-1;
		distances=new int[r][c];
		kingds=new int[r][c];
		knightlocs=new int[num][2];
		knightds=new int[num];
		String king=locs.get(0);
		st=new StringTokenizer(king, "/");
		kx=Integer.parseInt(st.nextToken());
		ky=Integer.parseInt(st.nextToken());
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				kingds[i][j]=SDKg(i, j);
			}
		}
		// store knight locations
		for(int i=0; i<num; i++) {
			String knight=locs.get(i+1);
			st=new StringTokenizer(knight, "/");
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			knightlocs[i][0]=x;
			knightlocs[i][1]=y;
		}

		
		// find shortest distance
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				int test=0;
				for(int k=0; k<num; k++) {
					SDKt(knightlocs[k][0], knightlocs[k][1]);
					test+=distances[i][j];
				}
				test+=kingds[i][j];
				shortest=Math.min(shortest, test);
				
				// allow for king to be picked up
				for(int l=-2; l<=2; l++) {
					for(int m=-2; m<=2; m++) {
						int testx=kx+l;
						int testy=ky+m;
						if(testx>=0 && testx<r) {
							if(testy>=0 && testy<c) {
								
							}
						}
					}
				}
				
			}
		}
		
		System.out.println(shortest);
		out.write(shortest+"\n");
		out.close();
		System.exit(0);
	}
	
	// return sum of distances from vertex v to every vertex in startp
	public static void SDKt(int x, int y) {
		boolean visited[][]=new boolean[r][c];
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				distances[i][j]=MAX;
			}
		}
		ArrayList<vertex1> spots=new ArrayList<vertex1>();
		vertex1 cur=new vertex1(x, y);
		spots.add(cur);
		int count=0;
		boolean newSpots=true;
		while(newSpots) {
			newSpots=false;
			int size=spots.size();
			for(int i=0; i<size; i++) {
				cur=spots.remove(0);
				visited[cur.x][cur.y]=true;
				distances[cur.x][cur.y]=count;
				for(int j=0; j<8; j++) {
					int testx=cur.x+mx[j];
					int testy=cur.y+my[j];
					if(testx>=0 && testx<r) {
						if(testy>=0 && testy<c) {
							if(!visited[testx][testy]) {
								newSpots=true;
								cur=new vertex1(testx, testy);
								spots.add(cur);
							}
						}
					}
				}
			}
			count++;
		}
	}
	
	public static int SDKg(int x, int y) {
		return Math.max(Math.abs(kx-x), Math.abs(ky-y));
	}
}
