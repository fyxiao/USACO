/*
ID:
LANG: JAVA
TASK: race3
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class race3 {
	static int n, MAX;
	static int[][] graph, path;
	static ArrayList<Integer> unv, split;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("race3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));
        ArrayList<String> input = new ArrayList<String>();
        while(f.ready()) {
        	input.add(f.readLine());
        }
        MAX = Integer.MAX_VALUE;
        n = input.size()-1;
        graph = new int[n][n];
        for(int i=0; i<n; i++) {
        	StringTokenizer st = new StringTokenizer(input.get(i));
        	while(st.hasMoreTokens()) {
        		int k = Integer.parseInt(st.nextToken());
        		if(k!=-2) graph[i][k] = 1;
        	}
        }
        
        /* try to find unavoidable points by iterating through each point,
         * and removing all its outgoing edges, see if still a path
         */
        unv = new ArrayList<Integer>();
        split = new ArrayList<Integer>();
        for(int v = 1; v<n-1; v++) { 
        	// remove all outgoing edges from vertex v
        	path = new int[n][n];
        	for(int i=0; i<n; i++) {
        		if(i!=v) {
        			path[v][i] = MAX;
        		}
        	}
        	
        	for(int i=0; i<n; i++) {
        		if(i!=v) {
        			for(int j=0; j<n; j++) {
        				if(graph[i][j]==1) path[i][j] = 1;
        				else if(i==j) path[i][j] = 0;
        				else path[i][j] = MAX;
        			}
        		}
        	}
        	// run Floyd-Warshall
        	for(int k=0; k<n; k++) {
        		for(int i=0; i<n; i++) {
        			for(int j=0; j<n; j++) {
        				if(path[i][k]!=MAX && path[k][j]!=MAX) {
        					path[i][j] = Math.min(path[i][j], path[i][k]+path[k][j]);
        				}
        			}
        		}
        	}
        	// check if it's an unavoidable point, if it is, check if it's a splitting point
        	if(path[0][n-1]==MAX) {
        		unv.add(v);
        		boolean isSplit = true;
        		ArrayList<Integer> cutA = new ArrayList<Integer>();
        		ArrayList<Integer> cutB = new ArrayList<Integer>();
        		// partition vertices into the two cuts
        		for(int i=0; i<n; i++) {
        			if(i!=v) {
        				if(path[0][i]!=MAX) {
        					cutA.add(i);
        				}
        				else {	
        					cutB.add(i);
        				}
        			}
        		}
        		cutB.add(v);
        		// find if other connecting edges
        		search:
        		for(int i=0; i<cutB.size(); i++) {
        			for(int j=0; j<cutA.size(); j++) {
        				if(graph[cutB.get(i)][cutA.get(j)]==1) {
        					isSplit = false;
        					break search;
        				}
        			}
        		}
        		if(isSplit) {
        			split.add(v);
        		}
        	}
        }
        
        for(int i=0; i<unv.size(); i++) {
        	System.out.println(unv.get(i));
        }
        for(int i=0; i<split.size(); i++) {
        	System.out.println(split.get(i));
        }
        
        String output = unv.size()+"";
        for(int i=0; i<unv.size(); i++) {
        	output += " " + unv.get(i);
        }
        out.write(output + "\n");
        output = split.size()+"";
        for(int i=0; i<split.size(); i++) {
        	output += " " + split.get(i);
        }
        out.write(output + "\n");
        
        out.close();
        System.exit(0);
    }
}
