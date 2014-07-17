/*
ID: 
TASK: butter
LANG: JAVA
*/

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class vertexButter {
	int v, d;
	public vertexButter(int num, int dis) {
		v=num;
		d=dis;
	}
}

class VertexButterComparator implements Comparator {
	/* method to compare two fractions in String form */
	public int compare (Object a, Object b) {
		vertexButter v1 = (vertexButter)a;
		vertexButter v2 = (vertexButter)b;
		return v1.d - v2.d;
	}
}

public class butter {
	public static int n, p, c;
	public static int[] startp;
	public static int[][] graph;
	public static int[][] shortd;
	public static final int MAX=100000000;
	public static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		startp=new int[n];
		graph=new int[p][p];
		shortd=new int[n][p];
		adj = (ArrayList<Integer>[])new ArrayList[p];
		for(int i=0; i<p; i++) {
			adj[i]=new ArrayList<Integer>();
		}
		for(int i=0; i<n; i++) {
			startp[i]=Integer.parseInt(f.readLine())-1;
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<p; j++) {
				if(startp[i]==j) {
					shortd[i][j]=0;
				}
				else {
					shortd[i][j]=MAX;
				}
			}
		}
		for(int i=0; i<p; i++) {
			for(int j=0; j<p; j++) {
				if(i!=j) {
					graph[i][j]=-1;
				}
				else {
					graph[i][j]=0;
				}
			}
		}
		for(int i=0; i<c; i++) {
			int a, b, len;
			st = new StringTokenizer(f.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			len = Integer.parseInt(st.nextToken());
			adj[a-1].add(b-1);
			adj[b-1].add(a-1);
			graph[a-1][b-1]=len;
			graph[b-1][a-1]=len;
		}

		for(int i=0; i<n; i++) {
			dijkstra(i);
		}
		
		int min=MAX;
		for(int i=0; i<p; i++) {
			int sum=0;
			for(int j=0; j<n; j++) {
				sum+=shortd[j][i];
			}
			if(sum<min) {
				min=sum;
			}
		}
		
		System.out.println(min);
		out.write(min + "\n");
		
		out.close();
		System.exit(0);
	}
	
	// return sum of distances from vertex v to every vertex in startp
	public static void dijkstra(int start) {
		int[] distances=new int[p];
		boolean[] visited=new boolean[p];
		Comparator<vertexButter> vComp = new VertexButterComparator();
		PriorityQueue<vertexButter> queue = new PriorityQueue<vertexButter>(p, vComp);
		for(int i=0; i<p; i++) {
			if(i!=startp[start]) {
				distances[i]=MAX;
			}
			else {
				distances[i]=0;
			}
			queue.add(new vertexButter(i, distances[i]));
		}
		// run dijkstra
		for(int i=0; i<p; i++) {
			vertexButter cur=queue.poll();
			while(visited[cur.v]) {
				cur=queue.poll();
			}
			visited[cur.v]=true;
			ArrayList<vertexButter> updated=new ArrayList<vertexButter>();
			for(int j=0; j<adj[cur.v].size(); j++) {
				int test=adj[cur.v].get(j);
				if(!visited[test]) {
					int alt=cur.d+graph[cur.v][test];
					if(alt<distances[test]) {
						vertexButter newV=new vertexButter(test, alt);
						distances[test]=alt;
						shortd[start][test]=alt;
						updated.add(newV);
					}
				}
			}
			for(int j=0; j<updated.size(); j++) {
				queue.add(updated.get(j));
			}
		}
	}

}


