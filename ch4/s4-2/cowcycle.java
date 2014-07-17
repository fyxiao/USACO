/*
ID: 
LANG: JAVA
TASK: cowcycle
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cowcycle {
	public static int F, R, f1, f2, r1, r2;
	public static int[] front, rear, bfront, brear;
	public static double minvar;
	public static String[] hash;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowcycle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcycle.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        F = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        f1 = Integer.parseInt(st.nextToken());
        f2 = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        
        front = new int[F];
        rear = new int[R];
        bfront = new int[F];
        brear = new int[R];
        minvar = Double.MAX_VALUE;
        hash = new String[50003];
        
        // get all possible front arrangements
        buildF(0);
        
        out.write(getRep(bfront)+"\n");
        out.write(getRep(brear)+"\n");
        out.close();
        System.exit(0);
    }
    
    public static boolean checked(String str) {
    	int code = Math.abs(str.hashCode());
    	int pos = code % hash.length;
        while(hash[pos]!=null)
            if(str.equals(hash[pos]))   return true;
            else if(pos==hash.length-1) pos=0;
            else pos++;
        hash[pos]=str;
        return false;
    }
    
    static boolean contains(String str) {
        int num = str.hashCode();
        if(num<0) num = -num;
        int p = num % hash.length;
        while(hash[p]!=null) {
            if(str.equals(hash[p])) {
            	return true;
            }
            else if(p==hash.length-1) {
            	p=0;
            }
            else {
            	p++;
            }
        }
        hash[p]=str;
        return false;
   }
        
    public static void buildF(int p) {
    	if(p==F) {
    		StringBuffer str = new StringBuffer();
            for(int i=1;i<F;i++) {
                str.append((int)(100*(double)front[i]/front[0]));
            }
    		if(contains(str.toString())) {
    			return;
    		}
    		buildR(0);
    	}
    	else {
    		for(int i = (p==0 ? f1:front[p-1]+1); i <= f2-(F-p)+1; i++) {
    			front[p]=i;
    			buildF(p+1);
    		}
    	}
    }

    public static void buildR(int p) {
    	if(p==R) {
    		if(3*front[0]*rear[0] <= front[F-1]*rear[R-1]) {
    			double test=computeVar(front, rear);
    			if(test<minvar) {
    				minvar = test;
    				copyArray(front, bfront);
    				copyArray(rear, brear);
    			}
    		}
    	}
    	else {
    		for(int i = (p==0 ? r1:rear[p-1]+1); i<=r2-(R-p)+1; i++) {
    			rear[p]=i;
    			buildR(p+1);
    		}
    	}
    }
    
    public static String getRep(int[] a) {
    	String output="";
    	for(int i=0; i<a.length-1; i++) {
    		output+=a[i]+ " ";
    	}
    	output+=a[a.length-1];
    	return output;
    }
    
    public static double computeVar(int[] f, int[] r) {
    	double[] arr = new double[f.length * r.length];
		for(int i=0; i<F; i++) {
			for(int j=0; j<R; j++) {
				arr[i*R+j] = (double)f[i]/r[j];
			}
		}
		Arrays.sort(arr);
    	int n=arr.length-1;
    	double[] dif = new double[n];
    	double sum=0;
    	for(int i=1; i<arr.length; i++) {
    		dif[i-1]=arr[i]-arr[i-1];
    		sum+=dif[i-1];
    	}
    	double mean=sum/n;
    	double var=0;
    	for(int i=0; i<n; i++) {
    		var+=(dif[i]-mean)*(dif[i]-mean);
    	}
    	var = var/(f.length*r.length);
    	return var;
    }

    public static void copyArray(int[] src, int[] target) {
    	for(int i=0; i<src.length; i++) {
    		target[i]=src[i];
    	}
    }
    
}
