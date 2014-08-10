/*
ID: 
LANG: JAVA
TASK: milk3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class milk3 
{
	private static int[] buckets = new int[3];
	//private static int M;
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
        		"milk3.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i=0; i<3; i++)
        	buckets[i] = Integer.parseInt(st.nextToken());
        //M = buckets[2];
        boolean[][] seen = new boolean[buckets[1]+1][buckets[2]+1];
        
        ArrayDeque<int[]> configs = new ArrayDeque<int[]>();
        configs.add(new int[] {0, 0, buckets[2]});
        ArrayDeque<Integer> left = new ArrayDeque<Integer>();
        
    	int[][] pours = { {0,1}, {0,2}, {1,0}, {1,2}, {2,0}, {2,1} };
        while (configs.size() > 0) {
        	int[] m = configs.poll();
        	seen[m[1]][m[2]] = true;
        	if (m[0] == 0 && !left.contains(m[2]))
        		left.add(m[2]);
        	for (int i=0; i<pours.length; i++) {
        		int[] res = pour(m, pours[i][0], pours[i][1]);
        		if (!seen[res[1]][res[2]])
        			configs.add(res);
        	}
        }
        
        Integer[] res = left.toArray(new Integer[0]);
        Arrays.sort(res);
        for (int i=0; i<res.length-1; i++)
        	out.write(res[i] + " ");
        out.write(res[res.length-1] + "\n");
        out.close();
        
        f.close();
        out.close();
        System.exit(0);
    }
    
    private static int[] pour(int[] m, int src, int dest) {
    	int[] res = new int[3];
    	System.arraycopy(m, 0, res, 0, m.length);
    	while (res[src] > 0 && res[dest] < buckets[dest]) {
    		res[src]--;
    		res[dest]++;
    	}
    	return res;
    }
}
