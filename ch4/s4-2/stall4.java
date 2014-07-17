/*
ID:
LANG: JAVA
TASK: stall4
*/

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class stall4 {
	public static int N, M, P;
	public static int[][] residual;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        P=N+M+2;
        residual = new int[P][P];
        
        // initialize graph
        for(int i=0; i<N; i++) {
        	residual[0][i+1]=1;
        }
        for(int i=N+1; i<P-1; i++) {
        	residual[i][P-1] = 1;
        }
        for(int i=1; i<N+1; i++) {
        	st = new StringTokenizer(f.readLine());
        	int num = Integer.parseInt(st.nextToken());
        	for(int j=0; j<num; j++) {
        		int e = Integer.parseInt(st.nextToken());
        		residual[i][N+e]++;
        	}
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
    	int[] prev = new int[P];
    	for(int i=0; i<P; i++) {
    		prev[i]=-1;
    	}
    	boolean[] visited = new boolean[P];
    	LinkedList<Integer> bfs = new LinkedList<Integer>();
    	bfs.add(0);
    	visited[0]=true;
    	search:
    	while(bfs.size()>0) {
    		int cur = bfs.poll();
    		for(int i=0; i<P; i++) {
    			if(cur!=i && !visited[i] && residual[cur][i]>0) {
    				bfs.add(i);
    				visited[i]=true;
    				prev[i]=cur;
    				if(i==P-1) {
    					break search;
    				}
    			}
    		}
    	}
    	int pathCap = Integer.MAX_VALUE;
    	// find path capacity
    	int track=P-1;
    	while(prev[track]!=-1) {
    		pathCap = Math.min(pathCap, residual[prev[track]][track]);
    		track=prev[track];
    	}
    	// update residual graph
    	track=P-1;
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
