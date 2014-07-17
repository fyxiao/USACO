/*
ID:
LANG: JAVA
TASK: ttwo
*/

import java.io.*;
import java.util.*;

public class ttwo {

	public static String[][] graph = new String[10][10];
	public static int cx=0, cy=0, fx=0, fy=0;
	public static int cdir=0, fdir=0;
	public static ArrayList<String> states = new ArrayList<String>();
	public static boolean canCatch = true;


	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

		for(int i=0; i<10; i++) {
			String line = f.readLine();
			for(int j=0; j<10; j++) {
				graph[i][j] = line.charAt(j) + "";
				if(graph[i][j].equals("C")) {
					cx=i;
					cy=j;
				}
				if(graph[i][j].equals("F")) {
					fx=i;
					fy=j;
				}
			}
		}

		String startState = cx + "/" + cy + "/" + fx + "/" + fy + "/" + cdir + "/" + fdir;
		states.add(startState);
		move();
		if(canCatch) {
			System.out.println(states.size());
			out.write(states.size() + "\n");
		}
		else {
			System.out.println(0);
			out.write(0 + "\n");
		}

		/*
		for(int i=0; i<states.size(); i++) {
			System.out.println(states.get(i));
		}*/

		out.close();
		System.exit(0);
	}

	public static void move() {
		// move cows
		if(cdir==0) {
			if(cx==0 || graph[cx-1][cy].equals("*")) {
				cdir++;
			}
			else {
				cx--;
			}
		}
		else if(cdir==1) {
			if(cy==9 || graph[cx][cy+1].equals("*")) {
				cdir++;
			}
			else {
				cy++;
			}
		}
		else if(cdir==2) {
			if(cx==9 || graph[cx+1][cy].equals("*")) {
				cdir++;
			}
			else {
				cx++;
			}
		}
		else {
			if(cy==0 || graph[cx][cy-1].equals("*")) {
				cdir = 0;
			}
			else {
				cy--;
			}
		}

		// move farmer
		if(fdir==0) {
			if(fx==0 || graph[fx-1][fy].equals("*")) {
				fdir++;
			}
			else {
				fx--;
			}
		}
		else if(fdir==1) {
			if(fy==9 || graph[fx][fy+1].equals("*")) {
				fdir++;
			}
			else {
				fy++;
			}
		}
		else if(fdir==2) {
			if(fx==9 || graph[fx+1][fy].equals("*")) {
				fdir++;
			}
			else {
				fx++;
			}
		}
		else if(fdir==3) {
			if(fy==0 || graph[fx][fy-1].equals("*")) {
				fdir = 0;
			}
			else {
				fy--;
			}
		}
		String state = cx + "/" + cy + "/" + fx + "/" + fy + "/" + cdir + "/" + fdir;

		if(cx==fx && cy == fy) {
			return;
		}

		if(states.contains(state)) {
			canCatch = false;
			return;
		}
		states.add(state);
		move();
	}

}
