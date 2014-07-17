/*
ID: 
LANG: JAVA
TASK: job
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class job {
	public static int N, M1, M2, P;
	public static int[] rateA, rateB, doneA, doneB;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("job.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N=Integer.parseInt(st.nextToken());
        M1=Integer.parseInt(st.nextToken());
        M2=Integer.parseInt(st.nextToken());
        P=M1+M2;
        rateA = new int[M1];
        doneA = new int[M1];
        rateB = new int[M2];
        doneB = new int[M2];
        int index=0;
        while(f.ready()) {
        	st=new StringTokenizer(f.readLine());
        	while(st.hasMoreTokens()) {
        		if(index<M1) {
        			rateA[index++]=Integer.parseInt(st.nextToken());
        		}
        		else {
        			rateB[index++-M1]=Integer.parseInt(st.nextToken());
        		}
        	}
        }
        // when A jobs are done
        int[] finA = new int[N];
        int[] finB = new int[N];
        for(int i=0; i<N; i++) {
        	int cur=Integer.MAX_VALUE;
        	int machine = -1;
        	for(int j=0; j<M1; j++) {
        		if(doneA[j]+rateA[j]<cur) {
        			cur = doneA[j]+rateA[j];
        			machine=j;
        		}
        	}
        	doneA[machine]+=rateA[machine];
        	finA[i] = doneA[machine];
        }
        int mid=max(doneA);
        for(int i=0; i<N; i++) {
        	int cur=Integer.MAX_VALUE;
        	int machine = -1;
        	for(int j=0; j<M2; j++) {
        		if(doneB[j]+rateB[j]<cur) {
        			cur = doneB[j]+rateB[j];
        			machine=j;
        		}
        	}
        	doneB[machine] = doneB[machine]+rateB[machine];
        	finB[i]=doneB[machine];
        }
        Arrays.sort(finA);
        Arrays.sort(finB);
        int done = 0;
        for(int i=0; i<N; i++) {
        	done=Math.max(done, finA[i]+finB[N-1-i]);
        }
        System.out.println(mid + " " + done);
        out.write(mid + " " + done + "\n");
        
        out.close();
        System.exit(0);
    }
    
    public static int max(int a[]) {
    	int max=-1;
    	for(int i=0; i<a.length; i++) {
    		max=Math.max(max, a[i]);
    	}
    	return max;
    }
    
    public static void print(int a[]) {
    	String output="";
    	for(int i=0; i<a.length; i++) {
    		output+=a[i] + " ";
    	}
    	System.out.println(output);
    }
}
