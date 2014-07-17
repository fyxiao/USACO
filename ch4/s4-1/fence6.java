/*
ID: 
LANG: JAVA
TASK: fence6
*/

import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

class vertex {
	int v, d;
	public vertex(int num, int dis) {
		v = num;
		d = dis;
	}
}

class VertexComparator implements Comparator {
    public int compare (Object a, Object b) {
        vertex v1 = (vertex)a;
        vertex v2 = (vertex)b;
        return v1.d-v2.d;
    }
 }

public class fence6 {
   public static int n, v;
   public static int MAX = Integer.MAX_VALUE;
   public static int[] lens;
   public static int[][] graph;
   public static String[] nodes;
   public static void main(String[] args) throws IOException {
       BufferedReader f = new BufferedReader(new FileReader("fence6.in"));
       PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
       n = Integer.parseInt(f.readLine());
       lens = new int[n];
       ArrayList<String> vertices = new ArrayList<String>();
       for(int i=0; i<n; i++) {
           StringTokenizer st = new StringTokenizer(f.readLine());
           int s = Integer.parseInt(st.nextToken())-1;
           int len = Integer.parseInt(st.nextToken());
           lens[s]=len;
           int n1 = Integer.parseInt(st.nextToken());
           int n2 = Integer.parseInt(st.nextToken());
           st = new StringTokenizer(f.readLine());
           int[] vertexA = new int[n1+1];
           int[] vertexB = new int[n2+1];
           vertexA[0] = s;
           vertexB[0] = s;
           for(int j=0; j<n1; j++) {
               vertexA[j+1] = Integer.parseInt(st.nextToken())-1;
           }
           st = new StringTokenizer(f.readLine());
           for(int j=0; j<n2; j++) {
               vertexB[j+1] = Integer.parseInt(st.nextToken())-1;
           }
           Arrays.sort(vertexA);
           Arrays.sort(vertexB);
           StringBuilder testA = new StringBuilder();
           StringBuilder testB = new StringBuilder();
           for(int j=0; j<n1+1; j++) {
               testA.append(vertexA[j]).append(","); 
           }
           for(int j=0; j<n2+1; j++) {
               testB.append(vertexB[j]).append(","); 
           }
           if(!vertices.contains(testA.toString())) {
               vertices.add(testA.toString());
           }
           if(!vertices.contains(testB.toString())) {
               vertices.add(testB.toString());
           }
       }
     
       v = vertices.size();
       nodes = new String[v];
       graph = new int[v][v];
       //System.out.println("number of vertices is " + v);
       for(int i=0; i<v; i++) {
           //System.out.println(vertices.get(i));
           nodes[i] = vertices.get(i);
       }
       // initialize graph
       for(int i=0; i<v; i++) {
           for(int j=0; j<v; j++) {
               graph[i][j] = MAX;
           }
       }
       for(int i=0; i<v; i++) {
           for(int j=i+1; j<v; j++) {
               int d = Integer.MAX_VALUE;
               String[] iTokens = nodes[i].split(",");
               String[] jTokens = nodes[j].split(",");
               for(int k=0; k<iTokens.length; k++) {
                   for(int l=0; l<jTokens.length; l++) {
                       if(iTokens[k].equals(jTokens[l])) {
                           graph[i][j] = Math.min(d, lens[Integer.parseInt(iTokens[k])]);
                           graph[j][i] = graph[i][j];
                       }
                   }
               }              
           }
       }
      
       /*
       for(int i=0; i<v; i++) {
           for(int j=0; j<v; j++) {
               System.out.print(graph[i][j] + " ");
           }
           System.out.println();
       }*/
       
       int shortest = MAX;
       // run dijkstra's
       for(int i=0; i<v; i++) {
    	   for(int j=i+1; j<v; j++) {
    		   if(graph[i][j]!=MAX) {
    			   // test neighbor, run dijkstra on graph where edge i-j is removed
    			   int test = graph[i][j];
    			   graph[i][j]=MAX;
    			   graph[j][i]=MAX;
    			   int extra = dijkstra(i, j);
    			   if(extra!=MAX) {
    				   test+=extra;
    				   shortest = Math.min(shortest, test);
    			   }
    			   graph[i][j]=test;
    			   graph[j][i]=test;
    		   }
    	   }
       }
       System.out.println(shortest);
       out.write(shortest + "\n");
       out.close();
       System.exit(0);
   }
   
   public static int dijkstra(int source, int dest) {
	    int[] distances=new int[v];
	    boolean[] reached = new boolean[v];
	    Comparator<vertex> comparator = new VertexComparator();
	    PriorityQueue<vertex> queue = new PriorityQueue<vertex>(v, comparator);
	    ArrayList<vertex> temp = new ArrayList<vertex>();
	    for(int i=0; i<v; i++) {
	        if(i==source) {
	            distances[i]=0;
	        }
	        else {
	            distances[i]=MAX;
	        }
	        queue.add(new vertex(i, distances[i]));
	    }
	    // run dijkstra
	    for(int i=0; i<v; i++) {
	        vertex cur=queue.poll();
	        while(reached[cur.v]) {
	        	cur = queue.poll();
	        }
	        int vnum = cur.v;
	        int dnum = cur.d;
	        if(cur.v == dest) { return cur.d; }
	        reached[cur.v] = true;
	        distances[cur.v] = cur.d;
	        Iterator itr = queue.iterator();
	        temp.clear();
	        while(itr.hasNext()) {
	            vertex test = (vertex)(itr.next());
	            int tvnum = test.v;
	            int tdnum = test.d;
	            if(!reached[test.v] && graph[cur.v][test.v]!=MAX) {
	                int d = cur.d+graph[cur.v][test.v];
	                if(d<test.d) {
	                	vertex updated = new vertex(test.v, d);
	                	temp.add(updated);
	                }
	            }
	        }
	        for(int j=0; j<temp.size(); j++) {
	        	queue.add(temp.get(j));
	        }
	    }
	    return distances[dest];
   }
   
}