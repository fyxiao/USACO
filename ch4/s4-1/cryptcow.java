/*
ID: 
LANG: JAVA
TASK: cryptcow
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class cryptcow {
	public static String original = "Begin the Escape execution at the Break of Dawn";
	public static String message;
	public static boolean pos = false;
	public static final int NHASH = 790001;
	public static boolean[] vis = new boolean[NHASH];
	public static HashSet<Integer> subs = new HashSet<Integer>();
	
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cryptcow.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cryptcow.out")));
        
        message = f.readLine();
        
        boolean pos = true;
        // rule out impossible cases
        for(int i=0; i<original.length(); i++) {
        	if(numOccurs(original, original.charAt(i)) != numOccurs(message, original.charAt(i))) {
        		pos = false;
        		System.out.println("different char frequencies");
        		out.write("0 0\n");
        	}
        }
        if(numOccurs(message, 'C')!=numOccurs(message, 'O') || numOccurs(message, 'C')!=numOccurs(message, 'W')) {
        	pos = false;
        	System.out.println("different number of C/O/W");
        	out.write("0 0\n");
        }
        // calculate hash of subsequences of original
        for(int i=0; i<original.length(); i++) {
        	for(int j=i+2; j<original.length(); j++) {
        		subs.add(original.substring(i, j).hashCode());
        	}
        }
        //System.out.println(test);
        if(!passBasic(message)) {
        	pos = false;
        	out.write("0 0\n");
        }
        // otherwise try to decrypt

        if(pos) {
        	int d = numOccurs(message, 'C');
        	boolean result = decrypt(message, d);
        	if(result) { out.write("1 " + d + "\n"); }
        	else { out.write("0 0\n"); }
        }
        out.close();
        System.exit(0);
    }
    
    public static boolean decrypt(String s, int cnt) {
    	int[] cindex = new int[cnt];
    	int[] oindex = new int[cnt];
    	int[] windex = new int[cnt];
    	if(cnt==0) {
    		if(s.equals(original)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	int cx = 0;
    	int ox = 0;
    	int wx = 0;
    	for(int i=0; i<s.length(); i++) {
    		if(s.charAt(i)=='C') {
    			cindex[cx]=i;
    			cx++;
    		}
    		else if(s.charAt(i)=='O') {
    			oindex[ox]=i;
    			ox++;
    		}
    		else if(s.charAt(i)=='W') {
    			windex[wx]=i;
    			wx++;
    		}
    	}
    	boolean result = false;
    	dfs:
    	for(int i=0; i<cnt; i++) {
    		for(int j=0; j<cnt; j++) {
    			for(int k=0; k<cnt; k++) {
    				int a = cindex[i];
    				int b = oindex[j];
    				int c = windex[k];
    				if(a<b && b<c) {
    					String modAsString = transform(s.toCharArray(), a, b, c);
    					int test = Math.abs(modAsString.hashCode())%NHASH;
    					if(!vis[test] && passBasic(modAsString)) {
    						vis[test]=true;
    						boolean testr = decrypt(modAsString, cnt-1);
    						if(testr) {
    							result=true;
    							break dfs;
    						}
    					}
    				}
    			}
    		}
    	}
    	return result;
    }

    public static boolean passBasic(String s) {
    	for(int i=0; i<s.length(); i++) {
    		if(s.charAt(i)=='C') { break; }
    		if(s.charAt(i)=='O' || s.charAt(i)=='W') {
    			return false;
    		}
    	}
    	for(int i=s.length()-1; i>=0; i--) {
    		if(s.charAt(i)=='W') { break; }
    		if(s.charAt(i)=='C' || s.charAt(i)=='O') {
    			return false;
    		}
    	}
        int tracker=0;
       	for(int i=0; i<s.length(); i++) {
       		if(s.charAt(i) == 'C' || s.charAt(i) == 'O' || s.charAt(i) == 'W') {
       			if(i-tracker>=2 && !subs.contains(s.substring(tracker, i).hashCode())) {
       				return false;
       			}
       			tracker=i+1;
       		}
       	}
       	return true;
    }
    
    public static String transform(char[] src, int c, int o, int w) {
    	int len = src.length-3;
    	char[] tgt = new char[len];
    	int tracker = 0;
    	for(int i=0; i<c; i++) {
    		tgt[tracker++]=src[i];
    	}
    	for(int i=o+1; i<w; i++) {
    		tgt[tracker++]=src[i];
    	}
    	for(int i=c+1; i<o; i++) {
    		tgt[tracker++]=src[i];
    	}
    	for(int i=w+1; i<src.length; i++) {
    		tgt[tracker++]=src[i];
    	}
    	return new String(tgt);
    }
    
    public static int numOccurs(String s, char c) {
    	int count=0;
    	for(int i=0; i<s.length(); i++) {
    		if(s.charAt(i)==c) count++;
    	}
    	return count;
    }
}
