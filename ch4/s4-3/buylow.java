/*
ID: 
LANG: JAVA
TASK: buylow 
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class buylow {
	static int N;
	static int[] prices, seqlen;
	static BigInteger[] seqnum;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("buylow.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
        N = Integer.parseInt(f.readLine());
        prices = new int[N];
        seqlen = new int[N];
        seqnum = new BigInteger[N];
        int p=0;
        while(f.ready()) {
        	StringTokenizer st = new StringTokenizer(f.readLine());
        	while(st.hasMoreTokens()) {
        		prices[p++] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // work backwards
        for(int i=N-1; i>=0; i--) {
        	int max=1;
        	seqnum[i]=new BigInteger("1");
        	ArrayList<Integer> seen = new ArrayList<Integer>();
        	for(int j=i+1; j<N; j++) {
        		if(prices[i]>prices[j] && seqlen[j]+1>max) {
        			max = seqlen[j]+1;
        			seqnum[i] = seqnum[j];
        			seen.clear();
        			seen.add(prices[j]);
        		}
        		else if(prices[i]>prices[j] && seqlen[j]+1==max) {
        			if(!seen.contains(prices[j])) {
        				seqnum[i] = seqnum[i].add(seqnum[j]);
        				seen.add(prices[j]);
        			}
        		}
        	}
        	seqlen[i] = max;
        }
        
        // get answer
        int max = 0;
        BigInteger num = new BigInteger("0");
        for(int i=0; i<N; i++) {
        	max = Math.max(max, seqlen[i]);
        }
        ArrayList<Integer> seen = new ArrayList<Integer>();
        for(int i=0; i<N; i++) {
        	if(seqlen[i]==max && !seen.contains(prices[i])) {
        		num = num.add(new BigInteger(seqnum[i]+""));
        		seen.add(prices[i]);
        	}
        }
        System.out.println(max + " " + num.toString());
        out.write(max + " " + num.toString() + "\n");
        
        out.close();
        System.exit(0);
    }
}
