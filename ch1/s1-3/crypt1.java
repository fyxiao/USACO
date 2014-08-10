/*
ID: 
LANG: JAVA
TASK: crypt1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashSet;
import java.util.StringTokenizer;

public class crypt1 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("crypt1.out")));

        int N = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] set = new int[N];
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i=0; i<N; i++) {
        	set[i] = Integer.parseInt(st.nextToken());
        	hs.add(set[i]);
        }
        
        int res = 0;
        for (int i=0; i<N; i++) {
        	for (int j=0; j<N; j++) {
        		for (int k=0; k<N; k++) {
        			for (int l=0; l<N; l++) {
        				for (int m=0; m<N; m++) {
        					int a = set[l] * (set[i]+10*set[j]+100*set[k]);
        					int b = set[m] * (set[i]+10*set[j]+100*set[k]);
        					if (a < 1000 && b < 1000 && isValid(a, hs) && isValid(b, hs)) {
        						if (a+b*10 < 10000 && isValid(a+b*10, hs))
        							res++;
        					}
        				}
        			}
        		}
        	}
        }
        
        out.write(res + "\n");
        f.close();
        out.close();
        System.exit(0);
    }
    
    private static boolean isValid(int k, HashSet<Integer> hs) 
    {
    	while (k > 0) {
    		if (!hs.contains(k%10))
    			return false;
    		k /= 10;
    	}
    	return true;
    }
}
