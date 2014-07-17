/*
ID:
LANG: JAVA
TASK: preface
*/

import java.io.*;

public class preface {
	public static int[] used = new int[7];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int n = Integer.parseInt(f.readLine());
		String[] numerals = {"I", "V", "X", "L", "C", "D", "M"};
		
		for(int i=0; i<=n; i++) {
			rep(i);
		}
		
		for(int i=0; i<7; i++) {
			if(used[i]>0) {
				out.write(numerals[i] + " " + used[i] +"\n");
				System.out.println(numerals[i] + " " + used[i]);
			}
		}
		
		out.close();
		System.exit(0);
	}
	
	public static void rep(int n) {
		//String s = "";
		int thousands = n/1000;
		int hundreds = (n-thousands*1000)/100;
		int tens = (n - thousands*1000 - hundreds*100)/10;
		int ones = n%10;
		
		for(int i=0; i<thousands; i++) {
			//s=s+"M";
			used[6]++;
		}
		if(hundreds==9) {
			//s = s+"CM";
			used[6]++;
			used[4]++;
		}
		else if(hundreds>=5){
			//s=s+"D";
			used[5]++;
			for(int i=0; i<hundreds-5; i++) {
				//s=s+"C";
				used[4]++;
			}
		}
		else if(hundreds==4) {
			//s=s+"CD";
			used[5]++;
			used[4]++;
		}
		else {
			for(int i=0; i<hundreds; i++) {
				//s=s+"C";
				used[4]++;
			}
		}
		if(tens==9) {
			//s=s+"XC";
			used[4]++;
			used[2]++;
		}
		else if(tens>=5){
			//s=s+"L";
			used[3]++;
			for(int i=0; i<tens-5; i++) {
				//s=s+"X";
				used[2]++;
			}
		}
		else if(tens==4) {
			//s=s+"XL";
			used[3]++;
			used[2]++;
		}
		else {
			for(int i=0; i<tens; i++) {
				//s=s+"X";
				used[2]++;
			}
		}
		if(ones==9) {
			//s=s+"IX";
			used[2]++;
			used[0]++;
		}
		else if(ones>=5){
			//s=s+"V";
			used[1]++;
			for(int i=0; i<ones-5; i++) {
				//s=s+"I";
				used[0]++;
			}
		}
		else if(ones==4) {
			//s=s+"IV";
			used[1]++;
			used[0]++;
		}
		else {
			for(int i=0; i<ones; i++) {
				//s=s+"I";
				used[0]++;
			}
		}
	}
}
