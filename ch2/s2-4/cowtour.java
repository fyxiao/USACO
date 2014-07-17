/*
ID:
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.util.*;

public class cowtour {
	
	public static int n;
	public static int[][] loc, graph;
	public static double[][] distances;
	public static int[] components;
	public static double[] diameters;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		
		// read in data
		n = Integer.parseInt(f.readLine());
		loc = new int[n][2];
		graph = new int[n][n];
		distances = new double[n][n];
		
		components = new int[n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			loc[i][0] = Integer.parseInt(st.nextToken());
			loc[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<n; i++) {
			String line = f.readLine();
			for(int j=0; j<n; j++) {
				if(line.charAt(j)=='0') {
					graph[i][j] = 0;
				}
				else {
					graph[i][j] = 1;
				}
			}
		}
		
		// setup distances graph
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(graph[i][j]==1) {
					distances[i][j] = getDis(loc[i][0], loc[i][1], loc[j][0], loc[j][1]);
				}
				else {
					distances[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		for(int i=0; i<n; i++) {
			distances[i][i] = 0;
		}
		
		// flood-fill to find components in graph
		int numComponents = 0;
		for(int i=0; i<n; i++) {
			if(components[i]==0) {
				numComponents++;
				components[i] = -2;
				floodfill(numComponents);
			}
		}
		
		
		
		// run Floyd-Warshall
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					distances[j][k] = Math.min(distances[j][k], distances[j][i] + distances[i][k]);
				}
			}
		}
		
		// setup diameters for each component
		diameters = new double[numComponents];
		for(int i=0; i<numComponents; i++) {
			diameters[i] = getDiam(i+1);
		}
		
		double minDiam = Double.POSITIVE_INFINITY;
		// find shortest diameter
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				// i and j not connected
				if(components[i]!=components[j]) {
					double test = Math.max(diameters[components[i]-1], diameters[components[j]-1]);
					double connect = getDis(loc[i][0], loc[i][1], loc[j][0], loc[j][1]) + getMaxDis(i) + getMaxDis(j);
					minDiam = Math.min(minDiam, Math.max(test, connect));
				}
			}
		}
		System.out.println(minDiam);
		System.out.println(String.format("%.6f", minDiam));
		out.write(String.format("%.6f", minDiam) + "\n");
				
		
		out.close();
		System.exit(0);
	}
	
	/* within a component, return the maximum distance from a point p to any other point in the
	 * same component
	 */
	public static double getMaxDis(int p) {
		double max = 0;
		for(int i=0; i<n; i++) {
			if(components[i]==components[p] && i!=p) {
				max = Math.max(max, distances[p][i]);
			}
		}
		return max;
	}
	
	public static double getDiam(int component) {
		double diam = 0;
		for(int i=0; i<n; i++) {
			if(components[i]==component)
				for(int j=i+1; j<n; j++) {
					if(components[j]==component) {
						diam = Math.max(diam, distances[i][j]);
					}
				}
		}
		return diam;
	}
	
	public static void floodfill(int a) {
		boolean iterate = false;
		for(int i=0; i<n; i++) {
			if(components[i]==-2) {
				components[i] = a;
				for(int j=0; j<n; j++) {
					if((j!=i && graph[i][j]==1) && components[j]==0) {
						components[j] = -2;
						iterate = true;
					}
				}
			}
		}
		if(iterate) {
			floodfill(a);
		}
	}
	
	public static double getDis(int a, int b, int c, int d) {
		return Math.sqrt((a-c)*(a-c) + (b-d)*(b-d));
	}

}
