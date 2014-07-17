/*
ID:
LANG: JAVA
TASK: fence9
*/


import java.io.*;
import java.util.*;

public class fence9 {
	public static double n, m, p;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		n=Double.parseDouble(st.nextToken());
		m=Double.parseDouble(st.nextToken());
		p=Double.parseDouble(st.nextToken());

		// Use Pick's Formula: a=i+b/2-1
		double a=p*m/2;
		// first side
		double b=p+1;
		// second side
		b+=gcd((int)n, (int)m);
		// third side
		b+=gcd((int)Math.abs(n-p), (int)m)-1;
		int count=(int)(a+1-b/2);
		System.out.println(count);
		out.write(count+"\n");
		out.close();
		System.exit(0);
	}
	
	// assume a>=b
	public static int gcd(int a, int b) {
		while(b!=0) {
			int temp=a-(a/b)*b;
			a=b;
			b=temp;
		}
		return a;
	}

}
