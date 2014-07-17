/*
ID: 
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ditch {
	public static int N, M;
	public static int[][] cap, residual;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ditch.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        cap = new int[M][M];
        residual = new int[M][M];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(f.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	cap[s-1][e-1] += Integer.parseInt(st.nextToken());
        	residual[s-1][e-1] = cap[s-1][e-1];
        }
        
        // run Ford-Fulkerson algorithm
        int maxflow=0;
        while(true) {
        	int d = pathFind();
        	if(d==0) {
        		break;
        	}
        	else {
        		maxflow+=d;
        	}
        }
        System.out.println(maxflow);
        out.write(maxflow+"\n");
        out.close();
        System.exit(0);
    }
    
    public static int pathFind() {
    	// track shortest path
    	int[] prev = new int[M];
    	for(int i=0; i<M; i++) {
    		prev[i]=-1;
    	}
    	boolean[] visited = new boolean[M];
    	LinkedList<Integer> bfs = new LinkedList<Integer>();
    	bfs.add(0);
    	visited[0]=true;
    	search:
    	while(bfs.size()>0) {
    		int cur = bfs.poll();
    		for(int i=0; i<M; i++) {
    			if(cur!=i && !visited[i] && residual[cur][i]>0) {
    				bfs.add(i);
    				visited[i]=true;
    				prev[i]=cur;
    				if(i==M-1) {
    					break search;
    				}
    			}
    		}
    	}
    	int pathCap = Integer.MAX_VALUE;
    	// find path capacity
    	int track=M-1;
    	while(prev[track]!=-1) {
    		pathCap = Math.min(pathCap, residual[prev[track]][track]);
    		track=prev[track];
    	}
    	// update residual graph
    	track=M-1;
    	while(prev[track]!=-1) {
    		residual[prev[track]][track] -= pathCap;
    		residual[track][prev[track]] += pathCap;
    		track=prev[track];
    	}
    	if(pathCap<Integer.MAX_VALUE) {
    		//System.out.println("returning " + pathCap);
    		return pathCap;
    	}
    	else {
    		return 0;
    	}
    }
    
}
