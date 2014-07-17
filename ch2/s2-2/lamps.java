/*
ID: 
LANG: JAVA
TASK: lamps
*/

import java.util.*;
import java.io.*;

public class lamps {
	
	public static ArrayList<Integer> lampsOn = new ArrayList<Integer>();
	public static ArrayList<Integer> lampsOff = new ArrayList<Integer>();
	public static ArrayList<String> good = new ArrayList<String>();
	private static int n, c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		
		n=Integer.parseInt(f.readLine());
		c=Integer.parseInt(f.readLine());

		// copy data
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		int cur = Integer.parseInt(st1.nextToken());
		while(cur != -1) {
			lampsOn.add(cur);
			if(st1.hasMoreTokens()) {
				cur = Integer.parseInt(st1.nextToken());
			}
		}
		// copy data
		StringTokenizer st2 = new StringTokenizer(f.readLine());
		cur = Integer.parseInt(st2.nextToken());
		while(cur != -1) {
			lampsOff.add(cur);
			if(st2.hasMoreTokens()) {
				cur = Integer.parseInt(st2.nextToken());
			}
			else {
				break;
			}
		}
		
		/*
		for(int i=0; i<lampsOn.size(); i++) {
			System.out.println(lampsOn.get(i));
		}
		for(int i=0; i<lampsOff.size(); i++) {
			System.out.println(lampsOff.get(i));
		}*/
		
		// test possible values
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				for(int k=0; k<2; k++) {
					for(int l=0; l<2; l++) {
						if(i+j+k+l<=c) {
							String start = "";
							for(int m=0; m<n; m++) {
								start+="1";
							}
							if(i==1) { start=switch1(start); }
							if(j==1) { start=switch2(start); }
							if(k==1) { start=switch3(start); }
							if(l==1) { start=switch4(start); }
							if((c-i-j-k-l)%2==0 && isValid(start)) {
								good.add(start);
							}
						}
					}
				}
			}
		}
		
		if(good.size()==0) {
			System.out.println("IMPOSSIBLE");
			out.write("IMPOSSIBLE\n");
		}
		else {
			Collections.sort(good);
			for(int i=0; i<good.size(); i++) {
				System.out.println(good.get(i));
				out.write(good.get(i)+"\n");
			}
		}
		
		out.close();
		System.exit(0);
	}
	
	public static boolean isValid(String s) {
		int numOn = lampsOn.size();
		int numOff = lampsOff.size();
		
		for(int i=0; i<numOn; i++) {
			if(s.charAt(lampsOn.get(i)-1)!='1') {
				return false;
			}
		}
		for(int i=0; i<numOff; i++) {
			if(s.charAt(lampsOff.get(i)-1)!='0') {
				return false;
			}
		}
		return true;
	}
	
	public static String switch1(String s) {

		String test = "";
		for(int j=0; j<n; j++) {
			if(s.charAt(j)=='0') {
				test+='1';
			}
			else {
				test+='0';
			}
		}
		return test;
	}
	
	public static String switch2(String s) {
		String test = "";
		for(int j=0; j<n; j++) {
			if((j+1)%2==1) {
				if(s.charAt(j)=='0') {
					test+='1';
				}
				else {
					test+='0';
				}
			}
			else {
				test+=s.charAt(j);
			}
		}
		return test;
	}
	
	public static String switch3(String s) {
		String test = "";
		for(int j=0; j<n; j++) {
			if((j+1)%2==0) {
				if(s.charAt(j)=='0') {
					test+='1';
				}
				else {
					test+='0';
				}
			}
			else {
				test+=s.charAt(j);
			}
		}
		return test;
	}
	
	public static String switch4(String s) {
		String test = "";
		for(int j=0; j<n; j++) {
			if((j+1)%3==1) {
				if(s.charAt(j)=='0') {
					test+='1';
				}
				else {
					test+='0';
				}
			}
			else {
				test+=s.charAt(j);
			}
		}
		return test;
	}


}
