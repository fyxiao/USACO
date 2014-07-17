/*
ID:
LANG: JAVA
TASK: ratios
*/

import java.io.*;
import java.util.*;

class Feed {
	int[] feeds = new int[3];
	int f1, f2, f3;
	public Feed(int[] a, int n1, int n2, int n3) {
		for(int i=0; i<3; i++) {
			feeds[i] = a[i];
		}
		f1=n1;
		f2=n2;
		f3=n3;
	}
}

public class ratios {
	public static int[] target = new int[3];
	public static int[][] given = new int[3][3];
	public static double targetr1, targetr2;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=0; i<3; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}
		targetr1=((double)target[0])/target[1];
		targetr2=((double)target[1])/target[2];
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=0; j<3; j++) {
				given[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ArrayList<Double> r1s = new ArrayList<Double>();
		ArrayList<Double> r2s = new ArrayList<Double>();
		r1s.add((double)given[0][0]/given[0][1]);
		r1s.add((double)given[1][0]/given[1][1]);
		r1s.add((double)given[2][0]/given[2][1]);
		r2s.add((double)given[0][1]/given[0][2]);
		r2s.add((double)given[1][1]/given[1][2]);
		r2s.add((double)given[2][1]/given[2][2]);
		
		Feed fOne=new Feed(given[0], 1, 0, 0);
		Feed fTwo=new Feed(given[1], 0, 1, 0);
		Feed fThree=new Feed(given[2], 0, 0, 1);
		
		/*
		int[] test = {21, 28, 35};
		Feed testfeed=new Feed(test, 0, -1, 0);
		System.out.println(isSame(testfeed));*/
		
		ArrayList<Feed> combs = new ArrayList<Feed>();
		combs.add(fOne);
		combs.add(fTwo);
		combs.add(fThree);
		boolean found=false;
		int level=0;
		search:
		while(!found) {
			int size = combs.size();
			level++;
			for(int i=0; i<size; i++) {
				Feed cur = combs.get(0);
				combs.remove(0);
				if(isSame(cur)) {
					/*System.out.println("found it");
					for(int j=0; j<3; j++) {
						System.out.print(cur.feeds[j] + " ");
					}*/
					System.out.println(cur.f1 + " " + cur.f2 + " " + cur.f3 + " " + cur.feeds[0]/target[0]);
					out.write(cur.f1 + " " + cur.f2 + " " + cur.f3 + " " + cur.feeds[0]/target[0] + "\n");
					found=true;
					break search;
				}
				else {
					//System.out.println(level);
					int[] feedsA = new int[3];
					for(int j=0; j<3; j++) {
						feedsA[j]=cur.feeds[j] + given[0][j];
					}
					int[] feedsB = new int[3];
					for(int j=0; j<3; j++) {
						feedsB[j]=cur.feeds[j] + given[1][j];
					}
					int[] feedsC = new int[3];
					for(int j=0; j<3; j++) {
						feedsC[j]=cur.feeds[j] + given[2][j];
					}
					Feed fA=new Feed(feedsA, cur.f1+1, cur.f2, cur.f3);
					Feed fB=new Feed(feedsB, cur.f1, cur.f2+1, cur.f3);
					Feed fC=new Feed(feedsC, cur.f1, cur.f2, cur.f3+1);
					double r1a=(double)feedsA[0]/feedsA[1];
					double r2a=(double)feedsA[1]/feedsA[2];
					double r1b=(double)feedsB[0]/feedsB[1];
					double r2b=(double)feedsB[1]/feedsB[2];
					double r1c=(double)feedsC[0]/feedsC[1];
					double r2c=(double)feedsC[1]/feedsC[2];
					if(!(r1s.indexOf(r1a)==r2s.indexOf(r2a))) {
						combs.add(fA);
						r1s.add(r1a);
						r2s.add(r2a);
					}
					if(r1s.indexOf(r1a)==-1 && r2s.indexOf(r2a)==-1) {
						combs.add(fA);
						r1s.add(r1a);
						r2s.add(r2a);
					}
					if(!(r1s.indexOf(r1b)==r2s.indexOf(r2b))) {
						combs.add(fB);
						r1s.add(r1b);
						r2s.add(r2b);
					}
					if(r1s.indexOf(r1b)==-1 && r2s.indexOf(r2b)==-1) {
						combs.add(fB);
						r1s.add(r1b);
						r2s.add(r2b);
					}
					if(!(r1s.indexOf(r1c)==r2s.indexOf(r2c))) {
						combs.add(fC);
						r1s.add(r1c);
						r2s.add(r2c);
					}
					if(r1s.indexOf(r1c)==-1 && r2s.indexOf(r2c)==-1) {
						combs.add(fC);
						r1s.add(r1c);
						r2s.add(r2c);
					}
				}
			}
		}
		
		out.close();
		System.exit(0);
	}
	
	public static boolean isSame(Feed curFeed) {
		boolean isSame=true;
		double r1 = ((double)curFeed.feeds[0])/curFeed.feeds[1];
		double r2 = ((double)curFeed.feeds[1])/curFeed.feeds[2];
		if(r1!=targetr1) {
			isSame=false;
		}
		if(r2!=targetr2) {
			isSame=false;
		}
		return isSame;
	}

}
