/*
ID: 
LANG: JAVA
TASK: ariprog
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class ariprog 
{
	private static class ansComp implements Comparator<int[]>
	{
		@Override
		public int compare(int[] a, int[] b) {
			if (a[1] != b[1]) return a[1] - b[1];
			return a[0] - b[0];
		}	
	}
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
        		"ariprog.out")));
        int N = Integer.parseInt(f.readLine());
        int M = Integer.parseInt(f.readLine());
        int MAX = 2*M*M;
        boolean isDS[] = new boolean[MAX+1];
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int p=0; p<=M; p++) {
        	for (int q=p; q<=M; q++) {
        		isDS[p*p+q*q] = true;
        		if (!hs.contains(p*p+q*q)) hs.add(p*p+q*q);
        	}
        }
        int numDS = 0;
        int[] ds = new int[hs.size()];
        for (Integer k : hs)
        	ds[numDS++] = k;
        Arrays.sort(ds);
        ArrayList<int[]> res = new ArrayList<int[]>();
        // iterate through all possible (s1, s2) pairs
        for (int i=0; i<numDS; i++) {
        	for (int j=i+1; j<numDS; j++) {
        		int d = ds[j] - ds[i];
        		boolean valid = true;
        		for (int k=2; k<N; k++) {
        			if (ds[i]+k*d > MAX || (ds[i]+k*d <=MAX && !isDS[ds[i] + k*d])) {
        				valid = false;
        				break;
        			}
        		}
        		if (valid) res.add(new int[]{ds[i], d});
        	}
        }
        if (res.size() == 0) out.write("NONE\n");
        Collections.sort(res, new ansComp());
        for (int i=0; i<res.size(); i++)
        	out.write(res.get(i)[0] + " " + res.get(i)[1] + "\n");
        f.close();
        out.close();
        System.exit(0);
    }
}
