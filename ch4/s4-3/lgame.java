/*
ID:
LANG: JAVA
TASK: lgame
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class DictComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		String s1 = (String)o1;
		String s2 = (String)o2;
		String[] t1 = s1.split(" ");
		String[] t2 = s2.split(" ");
		int cmp = t1[0].compareTo(t2[0]);
		if(cmp!=0) return cmp;
		else {
			return t1[1].compareTo(t2[1]);
		}
	}
}

public class lgame {
	static String letters;
	static int[] vals = {2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};
	static int[] comb;
	static int n, max;
	public static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String>[] hash = (ArrayList<String>[]) new ArrayList[57003];
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lgame.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));
        letters = f.readLine();
        n = letters.length();
        f = new BufferedReader(new FileReader("lgame.dict"));
        while(f.ready()) {
        	String word = f.readLine();
        	if(!word.equals(".")) {
        		int pos = hash(word);
        		if(hash[pos]==null) {
        			hash[pos] = new ArrayList<String>();
        		}
        		hash[pos].add(word);
        	}
        }
        // add empty string
        String empty = "";
        int pos = hash(empty);
        hash[pos] = new ArrayList<String>();
        hash[pos].add(empty);
        
        max = 0;
        recur(0, "", "");
        
        out.write(max+"\n");
        for(int i=0; i<list.size(); i++) {
        	out.write(list.get(i)+"\n");
        }
        
        out.close();
        System.exit(0);
    }
    
    
    public static void recur(int i, String s1, String s2) {
    	// if we have a finished string, test it
    	if(i==n) {
    		int testPos1 = hash(s1);
    		int testPos2 = hash(s2);
    		int testVal = getVal(s1)+getVal(s2);
    		if(hash[testPos1]!=null && hash[testPos2]!=null) {
    			for(int j=0; j<hash[testPos1].size(); j++) {
    				String match1 = hash[testPos1].get(j);
    				if(isP(s1, match1)) {
    					for(int k=0; k<hash[testPos2].size(); k++) {
    						String match2 = hash[testPos2].get(k);
    						if(isP(s2, match2)) {
    							if(testVal == max) {
    								String output = "";
    								if(match1.length()==0) {
    									output = match2;
    								}
    								else if(match2.length()==0) {
    									output = match1;
    								}
    								else {
    									int cmp = match1.compareTo(match2);
    									if(cmp <0) {
    										output = match1+" "+match2;
    									}
    									else {
    										output = match2+" "+match1;
    									}
    								}
    								if(!list.contains(output)) {
    									list.add(output);
    								}
    							}
    							else if(testVal > max) {
    								max = testVal;
    								list.clear();
    								String output = "";
    								if(match1.length()==0) {
    									output = match2;
    								}
    								else if(match2.length()==0) {
    									output = match1;
    								}
    								else {
    									int cmp = match1.compareTo(match2);
    									if(cmp <0) {
    										output = match1+" "+match2;
    									}
    									else {
    										output = match2+" "+match1;
    									}
    								}
    								if(!list.contains(output)) {
    									list.add(output);
    								}
    							}
    						}
    					}
    					
    				}
    			}
    		}
    	}
    	else {
    		recur(i+1, s1+letters.charAt(i), s2);
    		recur(i+1, s1, s2+letters.charAt(i));
    		recur(i+1, s1, s2);
    	}
    }
    
    public static int getVal(String str) {
    	int len = str.length();
    	int val = 0;
    	for(int i=0; i<len; i++) {
    		val += vals[str.charAt(i)-'a'];
    	}
    	return val;
    }
    
    public static boolean isP(String str1, String str2) {
    	char[] a = str1.toCharArray();
    	char[] b = str2.toCharArray();
    	Arrays.sort(a);
    	Arrays.sort(b);
    	return Arrays.equals(a, b);
    }
    
    public static int hash(String str) {
    	int hashCode = 0;
    	int[] cnts = new int[27];
    	int len = str.length();
    	for(int i=0; i<len; i++) {
    		cnts[str.charAt(i)-'a']+=1;
    	}
    	for(int i=0; i<27; i++) {
    		hashCode += (int)Math.pow(cnts[i], 26-i);
    	}
    	int pos = Math.abs(hashCode)%hash.length;
    	return pos;
    }
}
