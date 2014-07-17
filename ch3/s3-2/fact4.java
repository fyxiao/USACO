/*
ID:
LANG: JAVA
TASK: fact4
*/

import java.io.*;
import java.util.*;

public class fact4 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		
		int n = Integer.parseInt(f.readLine());
		int num = 1;
		int n2 = 0, n5 = 0;
		for(int i=1; i<=n; i++) {
			int cur = i;
			while(cur%2==0) {
				cur = cur/2;
				n2++;
			}
			while(cur%5==0) {
				cur = cur/5;
				n5++;
			}
			num = (num*cur)%10;
		}
		for(int i=0; i<n2-n5; i++) {
			num = (num*2)%10;
		}
		System.out.println(num);
		out.write(num + "\n");
		out.close();
		System.exit(0);
	}

}
